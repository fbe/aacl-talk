package name.felixbecker.aacl.demo01.classloader;

import name.felixbecker.aacl.demo01.classgenerator.BrokenClassGenerator;
import name.felixbecker.aacl.demo01.classgenerator.SyntheticClassGenerator;

public class OwnDelegatingClassLoader extends ClassLoader {
	
	// The Java < 9 Way
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		final byte[] bytes = SyntheticClassGenerator.generateClass(name);
		return defineClass(name, bytes, 0, bytes.length);
	}

	// The Java 9+ Way

	public Class<?> loadBrokenClass(String name, BrokenClassGenerator.DamageType damageType) throws ClassNotFoundException {
		System.err.println("Loading broken class " + name + " with damage type " + damageType);
		final byte[] bytes = BrokenClassGenerator.generateBrokenClass(name, damageType);
		return defineClass(name, bytes, 0, bytes.length);
	}
}
