package name.felixbecker.aacl.shadingdemo.cassandradriver;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.apache.log4j.BasicConfigurator;

public class CassandraDriverDemo {

    private Cluster cluster;

    private Session session;

    public void connect(String node, Integer port) {
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }

    public static void main(String... args){
        BasicConfigurator.configure();
        CassandraDriverDemo cdd = new CassandraDriverDemo();
        cdd.connect("127.0.0.1", 9042);
    }
}
