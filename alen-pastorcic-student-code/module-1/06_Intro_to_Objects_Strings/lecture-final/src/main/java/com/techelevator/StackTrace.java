package com.techelevator;

public class StackTrace {
    public static void main(String[] args) {
        methodA();
    }

    private static void methodA() {
        methodB();
    }

    private static void methodB() {
        methodC();
    }

    private static void methodC() {
        int result = Integer.parseInt("foo");// 1/0;
    }
}
