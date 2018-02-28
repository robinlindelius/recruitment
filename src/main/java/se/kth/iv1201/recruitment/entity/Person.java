package se.kth.iv1201.recruitment.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * O/R mapping of the table person in the database.
 */
@Entity
@Table(name = "persons", catalog = "Recruitment")
public class Person {

    @Id
    @NotNull
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "person_id", unique = true, nullable = false)
    int personId;

    @NotNull
    @Size(min=4, max=20)
    @Column(name = "firstname")
    private String firstName;

    @NotNull
    @Size(min=4, max=20)
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Size(min=12, max=12)
    @Column(name = "personalnumber")
    private String personalNumber;

    @NotNull
    @NotEmpty
    @Email
    @Column(name = "email")
    private String email;

    public Person() {

    }

    public Person(String firstName, String lastName, String personalNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.email = email;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}