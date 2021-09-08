package packagePlugin3;

import interfaces.Plugin;

public class Plugin3Another implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("Hello from " + this.getClass().getCanonicalName());
    }
}
