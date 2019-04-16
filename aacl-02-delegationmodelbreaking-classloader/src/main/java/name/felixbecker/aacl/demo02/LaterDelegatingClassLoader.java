package name.felixbecker.aacl.demo02;

public class LaterDelegatingClassLoader extends ClassLoader {

	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		
		synchronized (getClassLoadingLock(name)) {

			final Class<?> c = findLoadedClass(name);

			if (c == null) {

				if (name.contentEquals("own class")) {
					// FIXME lookup in own folder
					return String.class;
				}

				return super.loadClass(name, resolve);

			} else {
				return c;
			}
		}
	}
}
