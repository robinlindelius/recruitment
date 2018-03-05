package se.kth.iv1201.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.recruitment.entity.*;
import se.kth.iv1201.recruitment.repository.ApplicationRepository;
import se.kth.iv1201.recruitment.repository.CompetenceRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service class to handle storing and retreiving applications from the database.
 */
@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    CompetenceRepository competenceRepository;

    /**
     * Method to save an appliction to the database.
     * @param competenceProfile DTO to construct from
     * @param availability DTO to construct from
     */
    public void registerApplication(CompetenceProfileDTO competenceProfile, AvailabilityDTO availability) {
        Application application = new Application();

        try {
            Person person = myUserDetailsService.getLoggedInPerson();
            application.setPerson(person);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        CompetenceProfile cp = new CompetenceProfile();
        cp.setYearsOfExperience(competenceProfile.getYearsOfExperience());
        cp.setCompetence(competenceRepository.findOne(competenceProfile.getCompetence()));

        try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(availability.getFrom());
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(availability.getTo());
            Availability av = new Availability(from, to);
            application.addAvailability(av);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        application.setAccepted(false);
        application.setDate(new Date());
        application.addCompetenceProfile(cp);

        applicationRepository.save(application);
    }

    /**
     * Retrieves Applications for the currently logged in user.
     * @return List of applications.
     */
    public List<Application> getApplicationsForUser() {
        List<Application> applications = new ArrayList<>();
        try {
            Person currentPerson = myUserDetailsService.getLoggedInPerson();
            applicationRepository.findAll().forEach(application -> {
                if(application.getPerson().getPersonId() == currentPerson.getPersonId()) {
                    applications.add(application);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return applications;
    }

    public List<Application> getApplications() {
        List<Application> applications = new ArrayList<>();
        applicationRepository.findAll().forEach(applications::add);

        return applications;
    }
}
