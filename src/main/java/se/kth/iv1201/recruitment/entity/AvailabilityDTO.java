package se.kth.iv1201.recruitment.entity;

/**
 * DTO class to transer values from html form.
 */
public class AvailabilityDTO {

    String from;

    String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
