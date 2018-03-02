package se.kth.iv1201.recruitment.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an application
 */
@Entity
@Table(name = "application", catalog = "Recruitment")
public class Application {

    @Id
    @GeneratedValue
    private long id;

    @Column (nullable = false)
    private boolean accepted;

    @Column (nullable = false)
    private Date date;

    @ManyToOne
    private Person person;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "application", cascade = CascadeType.ALL)
    private Set<CompetenceProfile> competenceProfiles = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "application", cascade = CascadeType.ALL)
    private Set<Availability> availabilities = new HashSet<>();

    /**
     * Empty constructor for an application - used by the persistence library
     */
    public Application() {
    }

    /**
     * Constructor for an application
     * @param date                  The date the application was created
     * @param person                The person that mad the application
     * @param competenceProfiles    The competence profiles related to the application
     * @param availabilities        The availabilities related to the application
     */
    public Application(Date date, Person person, Set<CompetenceProfile> competenceProfiles, Set<Availability> availabilities) {
        this.date = date;
        this.person = person;
        this.competenceProfiles = competenceProfiles;
        this.availabilities = availabilities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<CompetenceProfile> getCompetenceProfiles() {
        return competenceProfiles;
    }

    public void setCompetenceProfiles(Set<CompetenceProfile> competenceProfiles) {
        this.competenceProfiles = competenceProfiles;
    }

    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}
