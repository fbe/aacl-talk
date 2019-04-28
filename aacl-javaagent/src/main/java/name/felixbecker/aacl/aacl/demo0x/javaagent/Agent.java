package name.felixbecker.aacl.aacl.demo0x.javaagent;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String arg, Instrumentation instrumentation){
        instrumentation.addTransformer(new LicenseImprover());
    }
}
