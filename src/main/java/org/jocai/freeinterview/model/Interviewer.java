package org.jocai.freeinterview.model;


import javax.persistence.*;

/**
 * Created by Gerardo Martín Roldán on 02/06/17.
 */
@Entity
public class Interviewer {
    @Id
    @SequenceGenerator(name = "id_generator", sequenceName = "INTERVIEWER_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private Long id;

    private String firstName;
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
