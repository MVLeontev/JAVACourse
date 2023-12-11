package ru.vtb.course.lesson3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectInvocationHandlerAdv<T> implements InvocationHandler {
    private T currentObject;
    private ConcurrentHashMap<KeyObjAndMeth, ResultAndTimeout> methStore = new ConcurrentHashMap<>();
    Timer timer = new Timer("GarbageCleaner", true);
    public ObjectInvocationHandlerAdv(T currentObject) {

        this.currentObject = currentObject;

        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        methStore.values().removeIf( value -> !((ResultAndTimeout) value).isActual() );
                    }
                }, 500, 1000);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object objectResult;
        Method currentMethod;
        try {
            currentMethod =currentObject.getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (Exception e) {
            return method.invoke(currentObject, args);
        }
        // пришел кэшированный метод. Поищем в кэше такое состояние объекта, вернем результат и обновим таймаут
        if (currentMethod.isAnnotationPresent(Cache.class)) {
            int lifeTimeAnnotation =  currentMethod.getAnnotation(Cache.class).value();
            ResultAndTimeout resultAndTimeout = methStore.get(new KeyObjAndMeth(currentObject.toString(), currentMethod));
            if (resultAndTimeout != null) {
                objectResult = resultAndTimeout.result;
                System.out.println("попали в кэш");
            } else {
                objectResult = method.invoke(currentObject, args);
            }
            methStore.put(
                    new KeyObjAndMeth(currentObject.toString(), currentMethod),
                    new ResultAndTimeout(objectResult, lifeTimeAnnotation)
            );
            return objectResult;
        }

        return method.invoke(currentObject, args);
    }

    // Класс для хранения состояния объекта и вызванного метода, Key для мапы
    private static class KeyObjAndMeth {
        String keyToString;
        Method keyMethod;

        public KeyObjAndMeth(String keyToString, Method keyMethod) {
            this.keyToString = keyToString;
            this.keyMethod = keyMethod;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyObjAndMeth that = (KeyObjAndMeth) o;
            return Objects.equals(keyToString, that.keyToString) && Objects.equals(keyMethod, that.keyMethod);
        }

        @Override
        public int hashCode() {
            return Objects.hash(keyToString, keyMethod);
        }
    }

    //Класс для хранения результата выполнения метода и времени жизни кэша, Value для мапы
    private static class ResultAndTimeout {
        Object result;
        Long timeout;

        public ResultAndTimeout(Object result, Integer lifeTime) {
            this.result = result;
            if (lifeTime != null) {
                this.timeout = System.currentTimeMillis()+lifeTime;
            }
        }

        public boolean isActual() {
            return (this.timeout == null || this.timeout >= System.currentTimeMillis());
        }
    }



}
