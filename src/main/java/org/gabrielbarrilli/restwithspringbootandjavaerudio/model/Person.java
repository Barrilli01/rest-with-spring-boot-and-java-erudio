package org.gabrielbarrilli.restwithspringbootandjavaerudio.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "p01_person")
public class Person implements Serializable {

    // testando
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p01_codigo_pessoa")
    private Long id;

    @Column(name = "p01_first_name", nullable = false)
    private String firstName;

    @Column(name = "p01_last_name", nullable = false)
    private String lastName;

    @Column(name = "p01_address")
    private String address;

    @Column(name = "p01_gender")
    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person(String firstName, String lastName, String address, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Person() {
    }
}
