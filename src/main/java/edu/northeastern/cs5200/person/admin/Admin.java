package edu.northeastern.cs5200.person.admin;

import edu.northeastern.cs5200.person.Person;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin extends Person {

}
