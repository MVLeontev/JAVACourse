package ru.vtb.course.lesson2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class ObjectInvocationHandler<T> implements InvocationHandler {
    private T currentObject;
    private final HashMap<Method, Object> methodStore = new HashMap();

    public ObjectInvocationHandler(T currentObject) {
        this.currentObject =currentObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object objectResult;
        // т.к. аннотация задана на реализации, нужно получить этот метод, а не интерфейс
        Method currentMethod;
        try {
            currentMethod = currentObject.getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (Exception e) {
            return method.invoke(currentObject, args);
        }
        //Если пришел сеттер, очистим историю запусков
        if (currentMethod.isAnnotationPresent(Setter.class)) {
            methodStore.clear();
        }

        if (currentMethod.isAnnotationPresent(Cache.class)){            //если пришел кашированный метод
            if (methodStore.containsKey(currentMethod)) {                      // если этот метод уже запускался - есть в хешмап -
                objectResult = methodStore.get(currentMethod);                 // то будем возвращать результат его прошлого запуска
                //System.out.println("Метод кэширован");
            } else {                                                    // а если не запускался - нет в хешмап -
                objectResult = method.invoke(currentObject, args);      // то выполним метод
                methodStore.put(currentMethod, objectResult);                  // и занесем в хешмап
            }
            return objectResult;
        }

        return method.invoke(currentObject, args);                      // если метод не кешируется, то просто выполним
    }
}
