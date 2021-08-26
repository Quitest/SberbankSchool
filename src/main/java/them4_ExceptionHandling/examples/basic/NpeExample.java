package ru.sbrf.javacourse.exeption.example.basic;

/**
 * Vehicle engine.
 */
class Engine {

    /**
     * Starts engine.
     */
    void start() {
        System.out.println("Engine started");
    }

}

/**
 * Vehicle that <i>can</i> have engines.
 */
class Vehicle {

    private Engine engine;

    /**
     * Constructs vehicle object without engine.
     */
    Vehicle() {
    }

    /**
     * Constructs Vehicle object with engine.
     *
     * @param engine vehicle engine
     * @throws NullPointerException if the specified engine is null
     */
    Vehicle(Engine engine) {
        if (engine == null) {
            throw new NullPointerException();
        }

        this.engine = engine;
    }

    /**
     * Starts this vehicle engine.
     */
    void startEngine() {
        engine.start();
    }

}

public class NpeExample {

    public static void main(String[] args) {
        Vehicle bike = new Vehicle();

        Engine engine = new Engine();
        Vehicle car = new Vehicle(engine);

        car.startEngine();
        bike.startEngine();

        System.out.println("All engines started.");
    }

}
