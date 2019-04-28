package name.felixbecker.aacl.delegationmodelbreaking.mains;

import name.felixbecker.aacl.delegationmodelbreaking.classloader.LaterDelegatingURLClassLoader;
import name.felixbecker.aacl.delegationmodelbreaking.model.TestClass;

import java.net.URL;

public class TwiceLoadingClassDemo {

	public static void main(String... args) throws Exception {
		System.out.println(TwiceLoadingClassDemo.class.getProtectionDomain().getCodeSource().getLocation());

		final TestClass testClass = new TestClass();

		final var classLoader = new LaterDelegatingURLClassLoader(new URL[]{
				TwiceLoadingClassDemo.class.getProtectionDomain().getCodeSource().getLocation()
		}, TwiceLoadingClassDemo.class.getClassLoader());

		var testClazz = classLoader.loadClass(TestClass.class.getCanonicalName()).getConstructor().newInstance();
		var testClass2 = (TestClass) testClazz;
	}

}
