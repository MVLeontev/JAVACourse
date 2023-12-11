package ru.vtb.course.lesson3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests {
    private  Fraction fr1;
    private Fractionable num;
//--------
    interface Able {
        void setNum();
        double doubleValue();
    }
    class A implements Able {
        private int num;
        private int denum;

        public A(int num, int denum) {
            this.num = num;
            this.denum = denum;
        }
        @Override
        public void setNum() {
            this.num = num;
        }

        public void setDenum(int denum) {
            this.denum = denum;
        }

        @Override
        @Cache(1000)
        public double doubleValue() {
            return (double) num/denum;
        }
    }

//--------

    @BeforeEach
    public void initTest() {
        fr1 = new Fraction(2, 4);
        num = Utils.cache(fr1);
    }


    @Test
    @DisplayName("Кеширование простое после создания объекта")
    public void testCache() {
        num.doubleValue();



    }


}
