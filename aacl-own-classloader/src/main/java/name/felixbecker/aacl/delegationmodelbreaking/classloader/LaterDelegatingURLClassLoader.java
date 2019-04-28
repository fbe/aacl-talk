package name.felixbecker.aacl.delegationmodelbreaking.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class LaterDelegatingURLClassLoader extends URLClassLoader {

	public LaterDelegatingURLClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
}
