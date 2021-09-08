package interfaces;

public interface Plugin {
    //method doesn't matter
    default void doUsefull() {
        System.out.println("Hello from " + this.getClass().getCanonicalName());
    }
}
