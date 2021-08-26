package ru.sbrf.javacourse.exeption.example.basic;

public class StackOverflowErrorExample {

    private static void doSomething() {
        doSomething();
    }

    public static void main(String[] args) {
        doSomething();
    }

}
