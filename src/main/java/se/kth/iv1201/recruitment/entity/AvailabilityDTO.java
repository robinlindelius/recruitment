package se.kth.iv1201.recruitment.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

/**
 * DTO class to transfer values from html form.
 */
public class AvailabilityDTO {

    @NotNull
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    String from;

    @NotNull
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy/MM/dd")
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
