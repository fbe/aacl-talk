package name.felixbecker.aacl.delegationmodelbreaking.mains;

import name.felixbecker.aacl.delegationmodelbreaking.classloader.LaterDelegatingURLClassLoader;
import name.felixbecker.aacl.delegationmodelbreaking.model.TestClass;

import java.net.URL;
import java.util.stream.Stream;

public class InitializationDemo {

	public static void main(String... args) throws Exception {

		final String testClassFqdn = TestClass.class.getCanonicalName();

		System.err.println("Class forName initialize false");
		var cl1 = Class.forName(testClassFqdn, false, createClassLoader());

		System.err.println("Class forName initialize true");
		var cl2 = Class.forName(testClassFqdn, true, createClassLoader());

		System.err.println("loadClass");
		var cl3 = createClassLoader().loadClass(testClassFqdn);
		var methods = cl3.getDeclaredMethods();
		var fields = cl3.getDeclaredFields();
		Stream.of(fields).forEach(System.err::println);
		var f1 = cl3.getDeclaredFields()[0];
		f1.setAccessible(true);
		System.err.println("field.get(null)");
		f1.get(null);
	}

	private static ClassLoader createClassLoader() {
		var classLoader = new LaterDelegatingURLClassLoader(new URL[]{
				InitializationDemo.class.getProtectionDomain().getCodeSource().getLocation()
		}, InitializationDemo.class.getClassLoader());

		return classLoader;
	}

}
