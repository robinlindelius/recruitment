package se.kth.iv1201.recruitment.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity class for a work related competence.
 * Creates the O/R Mapping for Competence.
 */
@Entity
@Table(name = "competence", catalog = "Recruitment")
public class Competence {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "competence_id", unique = true, nullable = false)
    private Integer competenceId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    /**
     * Empty constructor for a competence - used by the persistence library
     */
    public Competence() {
    }

    /**
     * Constructor for a competence
     * @param name The name of the competence
     */
    public Competence(String name) {
        this.name = name;
    }


    public Integer getCompetenceId() {
        return competenceId;
    }


    public void setCompetenceId(Integer competenceId) {
        this.competenceId = competenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
