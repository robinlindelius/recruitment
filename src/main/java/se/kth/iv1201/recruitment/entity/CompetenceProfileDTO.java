package se.kth.iv1201.recruitment.entity;

/**
 * DTO class to transer values from html form.
 */
public class CompetenceProfileDTO {

    private Integer competence;

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
