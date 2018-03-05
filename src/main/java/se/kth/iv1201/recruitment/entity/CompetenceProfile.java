package se.kth.iv1201.recruitment.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity class for a competence profile that maps a competence to years of experience.
 * Creates the O/R Mapping for CompetenceProfile.
 */
@Entity
@Table(name = "competence_profile", catalog = "Recruitment")
public class CompetenceProfile {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "competence_profile_id", unique = true, nullable = false)
    private Integer competenceProfileId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Competence competence;

    @Column(name = "years_of_experience", nullable = false)
    private int yearsOfExperience;

    /**
     * Empty constructor for a competence profile - used by the persistence library
     */
    public CompetenceProfile() {
    }

    /**
     * Constructor for a competence profile, maps a competence to years of experience
     * @param competence            The competence
     * @param yearsOfExperience     Years of experience for the competence
     */
    public CompetenceProfile(Competence competence, int yearsOfExperience) {
        this.competence = competence;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Integer getCompetenceProfileId() {
        return competenceProfileId;
    }

    public void setCompetenceProfileId(Integer competenceProfileId) {
        this.competenceProfileId = competenceProfileId;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
