package name.felixbecker.aacl.hibernate.contextcl.main;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    final static Path pluginPath = Paths.get(System.getProperty("user.home"),
            ".m2",
            "repository",
            "name",
            "felixbecker",
            "aacl",
            "aacl-0x-hibernate-contextcl-plugin",
            "1.0.0-SNAPSHOT",
            "aacl-0x-hibernate-contextcl-plugin-1.0.0-SNAPSHOT.jar");

    // This is the application server, we have hibernate
    public static void main(String... args) throws Exception {

        final var pluginClassLoader = new URLClassLoader(new URL[]{ pluginPath.toUri().toURL()}, Main.class.getClassLoader());
        Class<?> pluginClass = pluginClassLoader.loadClass("name.felixbecker.aacl.hibernate.contextcl.plugin.entities.MyFancyPlugin");
        Thread.currentThread().setContextClassLoader(pluginClassLoader);
        final var plugin = pluginClass.getConstructor().newInstance();
        pluginClass.getDeclaredMethod("start").invoke(plugin);
    }
}
