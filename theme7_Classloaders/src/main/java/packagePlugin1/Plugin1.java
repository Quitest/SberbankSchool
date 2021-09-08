package packagePlugin1;

import interfaces.Plugin;

public class Plugin1 implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("Hello from Plugin1");
    }
}
