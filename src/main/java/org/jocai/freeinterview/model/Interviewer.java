package org.jocai.freeinterview.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 * Created by Gerardo Martín Roldán on 02/06/17.
 */
@Entity
@Table(uniqueConstraints =
    @UniqueConstraint(name = "interviewer_uc", columnNames = {"firstName", "lastName"}))
public class Interviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    private String firstName;

    @Size(max = 30)
    private String lastName;

    /**
     * Class constructor with no-args.
     */
    public Interviewer() {}

    /**
     * Class constructor.
     *
     * @param firstName
     * @param lastName
     */
    public Interviewer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Class constructor.
     *
     * @param id
     * @param firstName
     * @param lastName
     */
    public Interviewer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

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
}
