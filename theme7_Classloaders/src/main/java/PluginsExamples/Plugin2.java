package PluginsExamples;

import interfaces.Plugin;

public class Plugin2 implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("Hello from Plugin2");
    }
}
