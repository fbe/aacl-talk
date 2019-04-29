package name.felixbecker.aacl.ownclassloader.mains.verifydemos;

public class TestEntity {

    private static TestEntity INSTANCE = null;

    static {
        System.out.println("I am the static initalizer of class TestEntity");
        INSTANCE = new TestEntity();
    }
}
