package name.felixbecker.aacl.ownclassloader.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class LaterDelegatingURLClassLoader extends URLClassLoader {

	public LaterDelegatingURLClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}


	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		synchronized (getClassLoadingLock(name)){
			var clazz = findLoadedClass(name);
			if(clazz != null){
				return clazz;
			}

			try {
				var cl = findClass(name);
				return cl;
			} catch (ClassNotFoundException e){
				return getParent().loadClass(name);
			}
		}
	}
}
