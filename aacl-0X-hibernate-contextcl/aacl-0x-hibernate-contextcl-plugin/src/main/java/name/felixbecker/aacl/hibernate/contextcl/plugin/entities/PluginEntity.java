package name.felixbecker.aacl.hibernate.contextcl.plugin.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PluginEntity {

    //public PluginEntity(){}
    public PluginEntity(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ID: '"+name+"' Name: '"+id+"']";
    }
}
