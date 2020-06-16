package org.moy.common.io;


public class ExceptionMain {

    public static void main(String[] args) throws Exception {

        doSomething();
    }

    private static void doSomething() throws Exception {

        try {
            doFirst();
        } catch (Throwable e) {
            throw new RuntimeException("doSomething", e);
        }

    }

    private static void doFirst() throws Exception {

        try {
            doSecond();
        } catch (Throwable e) {
            throw new Exception("doFirst", e);
        }

    }

    private static void doSecond() {
        throw new ArithmeticException("doSecond");
    }
}
