package edu.northeastern.cs5200.pet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.northeastern.cs5200.person.Person;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer experience;
    private Integer level;

    public Boolean getIs_primary() {
        return is_primary;
    }

    public void setIs_primary(Boolean is_primary) {
        this.is_primary = is_primary;
    }

    @Column(name = "is_primary", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean is_primary;
    private Integer pokedex_no;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    @Transient
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPokedex_no() {
        return pokedex_no;
    }

    public void setPokedex_no(Integer pokedex_no) {
        this.pokedex_no = pokedex_no;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        return id != null && id.equals(((Pet) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

