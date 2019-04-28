package name.felixbecker.aacl.hibernate.contextcl.plugin.entities;

import org.hibernate.cfg.Configuration;

public class MyFancyPlugin {

    public void start(){
        var sf = new Configuration().configure().buildSessionFactory();
        var session = sf.openSession();

        session.getTransaction().begin();
        session.persist(new PluginEntity("Felix"));
        session.persist(new PluginEntity("Felix"));
        session.persist(new PluginEntity("Felix"));
        session.persist(new PluginEntity("Felix"));
        session.getTransaction().commit();

        session.getTransaction().begin();
        var pluginEntities = session.createQuery("FROM PluginEntity").list();
        pluginEntities.forEach(System.out::println);
        session.getTransaction().commit();

    }
}
