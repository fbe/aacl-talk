package name.felixbecker.aacl.ownclassloader.model;

public class EntityWithStaticInitializer {
    
    static {
        System.out.println("I am the static initializer of EntityWithStaticInitializer!");
    }
}
