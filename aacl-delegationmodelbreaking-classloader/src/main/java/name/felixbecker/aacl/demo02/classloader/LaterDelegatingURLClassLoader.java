package name.felixbecker.aacl.demo02.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class LaterDelegatingURLClassLoader extends URLClassLoader {


	public LaterDelegatingURLClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		
		synchronized (getClassLoadingLock(name)) {

			final Class<?> c = findLoadedClass(name);

			if(c == null) {
				try {
					return findClass(name);
				} catch (ClassNotFoundException cnfe) {
					return getParent().loadClass(name);
				}
			}

			 return c;
		}
	}
}
