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
            "aacl-hibernate-contextcl-plugin",
            "1.0.0-SNAPSHOT",
            "aacl-hibernate-contextcl-plugin-1.0.0-SNAPSHOT.jar");
    final static String pluginClassName = "name.felixbecker.aacl.hibernate.contextcl.plugin.entities.MyFancyPlugin";

    // This is the application server, we have hibernate
    public static void main(String... args) throws Exception {
        final var pluginClassLoader = new URLClassLoader(new URL[]{pluginPath.toUri().toURL()});
        Class<?> pluginClass = pluginClassLoader.loadClass(pluginClassName);
        Thread.currentThread().setContextClassLoader(pluginClassLoader);
        final var pluginInstance = pluginClass.getConstructor().newInstance();
        pluginClass.getDeclaredMethod("start").invoke(pluginInstance);
    }
}
