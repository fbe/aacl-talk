package name.felixbecker.aacl.demo02.mains;

import name.felixbecker.aacl.demo02.model.TestClass;
import name.felixbecker.aacl.demo02.classloader.LaterDelegatingURLClassLoader;

import java.net.URL;

public class TwiceLoadingClassDemo {

	public static void main(String... args) throws Exception {
		System.out.println(TwiceLoadingClassDemo.class.getProtectionDomain().getCodeSource().getLocation());

		final TestClass testClass = new TestClass();

		final LaterDelegatingURLClassLoader classLoader = new LaterDelegatingURLClassLoader(new URL[]{
				TwiceLoadingClassDemo.class.getProtectionDomain().getCodeSource().getLocation()
		}, TwiceLoadingClassDemo.class.getClassLoader());

		Object testClazz = classLoader.loadClass(TestClass.class.getCanonicalName()).getConstructor().newInstance();

		TestClass testClass2 = (TestClass) testClazz;
	}

}
