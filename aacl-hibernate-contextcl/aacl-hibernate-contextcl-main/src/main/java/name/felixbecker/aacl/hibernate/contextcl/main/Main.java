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
        var jarLocation = pluginPath.toUri().toURL();
        var myCl = Main.class.getClassLoader();
        var cl = new URLClassLoader(new URL[]{jarLocation}, myCl);

        var pluginClazz = cl.loadClass(pluginClassName);

        System.out.println(pluginClazz);

        var instance = pluginClazz.getConstructor().newInstance();
        System.out.println(instance);

        Thread.currentThread().setContextClassLoader(cl);
        pluginClazz.getDeclaredMethod("start").invoke(instance);

    }
}
