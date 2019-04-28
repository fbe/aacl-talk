package name.felixbecker.aacl.delegationmodelbreaking.mains;

import name.felixbecker.aacl.delegationmodelbreaking.classloader.LaterDelegatingURLClassLoader;
import name.felixbecker.aacl.delegationmodelbreaking.util.SyntheticClassGenerator;

import java.net.URL;

public class JavaLangDefiningDemo {
    public static void main(String... args){
        var bytes = SyntheticClassGenerator.generateClass("java.lang.String");
        var cl = new LaterDelegatingURLClassLoader(new URL[]{}, JavaLangDefiningDemo.class.getClassLoader());
        cl.loadClass("java.lang.String", bytes);
    }
}
