package name.felixbecker.aacl.demo01;

public class OwnDelegatingClassLoader extends ClassLoader {
	
	// The Java < 9 Way
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		final byte[] bytes = SyntheticClassGenerator.generateClass(name);
		return defineClass(name, bytes, 0, bytes.length);
	}
	
	// The Java 9+ Way
	@Override
	protected Class<?> findClass(String moduleName, String name) {
		final byte[] bytes = SyntheticClassGenerator.generateClass(name);
		return defineClass(name, bytes, 0, bytes.length);
	}
}
