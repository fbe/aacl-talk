package name.felixbecker.aacl.demo01;

public class OwnDelegatingClassLoader extends ClassLoader {
	
	// The Java < 9 Way
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println("Find without module name!");
		final byte[] bytes = SyntheticClassGenerator.generateClass(name);
		return defineClass(name, bytes, 0, bytes.length);
	}
	
	// The Java 9+ Way
	@Override
	protected Class<?> findClass(String moduleName, String name) {
		System.out.println("Find class (with module name " + moduleName + "!");
		final byte[] bytes = SyntheticClassGenerator.generateClass(name);
		return defineClass(name, bytes, 0, bytes.length);
	}
}
