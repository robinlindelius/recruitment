package se.kth.iv1201.recruitment.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO class to transer values from html form.
 */
public class CompetenceProfileDTO {

    @NotNull
    private Integer competence;

    @NotNull
    @Min(1)
    private int yearsOfExperience;

    public Integer getCompetence() {
        return competence;
    }

    public void setCompetence(Integer competence) {
        this.competence = competence;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
