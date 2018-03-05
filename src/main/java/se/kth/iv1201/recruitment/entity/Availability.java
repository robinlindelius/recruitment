package se.kth.iv1201.recruitment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents an availability for an application
 */
@Entity
@Table(name = "availability", catalog = "Recruitment")
public class Availability {

    @Id
    @GeneratedValue
    @Column(name = "availability_id")
    private long id;

    @Column(name = "from_date")
    private Date from;

    @Column(name = "to_date")
    private Date to;

    /**
     * Empty constructor for an availability - used by the persistence library
     */
    public Availability() {
    }

    /**
     * Constructor for an availability
     * @param from  Available from this date
     * @param to    Available to this date
     */
    public Availability(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
