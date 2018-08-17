package edu.northeastern.cs5200.person.admin;

import edu.northeastern.cs5200.person.Person;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin extends Person {
    @Column(name = "is_master")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isMaster;

    public Boolean getMaster() {
        return isMaster;
    }

    public void setMaster(Boolean master) {
        isMaster = master;
    }
}
