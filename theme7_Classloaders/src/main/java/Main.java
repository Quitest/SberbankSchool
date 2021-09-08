import interfaces.Plugin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PluginManager pluginManager = new PluginManager("theme7_Classloaders\\pluginRootDirectory");
        Scanner scanner = new Scanner(System.in);
        String s = "";
        int counter = 1;
        do {
            System.out.println("Loop iteration: " + counter++);
            if (s.equals("r")) {
                pluginManager.reloadAllPlugins();
            } else {
                pluginManager.loadAllPlugins();
            }
            pluginManager.getPluginMap().values()
                    .forEach(Plugin::doUsefull);
            System.out.println("[r]eload plugins; [q]uit; Anything another - update list\nYour choice:");
            s = scanner.nextLine();
        } while (!"q".equalsIgnoreCase(s));
    }

}
