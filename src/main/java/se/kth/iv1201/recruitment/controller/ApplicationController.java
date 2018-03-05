package se.kth.iv1201.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.recruitment.entity.*;
import se.kth.iv1201.recruitment.repository.CompetenceRepository;
import se.kth.iv1201.recruitment.service.ApplicationService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * A controller that handles traffic between the application form and application list, and the application service used to store and retrieve from db.
 */
@Controller
public class ApplicationController {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    ApplicationService applicationService;

    /**
     * Method that runs when the /applicant/application page receives a http GET request, to add objects necessary for
     * making applications to the model.
     * @param model the model to populate with objects.
     * @return String with a view name.
     */
    @GetMapping("/applicant/application")
    public String getApplication(Model model) {

        List<Competence> competences = new ArrayList<>();
        competenceRepository.findAll().forEach(competences::add);

        //List<CompetenceProfile> competenceProfiles = new ArrayList<>();
        //model.addAttribute("competenceProfiles", competenceProfiles);

        model.addAttribute("applications", applicationService.getApplicationsForUser());
        model.addAttribute("competences", competences);
        model.addAttribute("competenceProfile", new CompetenceProfileDTO());
        model.addAttribute("availability", new AvailabilityDTO());
        return "applicant/application";
    }

    /**
     * Populates the model with all applications.
     * @param model the Model to populate
     * @return String with a wiev name.
     */
    @GetMapping("/recruiter/applications")
    public String getApplications(Model model) {

        model.addAttribute("applications", applicationService.getApplications());

        return "recruiter/applications";
    }

    /**
     * Method that runs when the /applicant/application page receives a http POST request from the form located on that page,
     * tries to bind the values from the form into objects that are then stored in the DB.
     * @param competenceProfile DTO to make a CompetenceProfile object from.
     * @param bindingResultCompetenceProfile result from binding the form fields into the object.
     * @param availability DTO to make a Availability object from.
     * @param bindingResultAvailability result from binding the form fields into the object.
     * @return
     */
    @PostMapping("/applicant/application")
    public String postApplication(@Valid CompetenceProfileDTO competenceProfile, BindingResult bindingResultCompetenceProfile,
                                  @Valid AvailabilityDTO availability, BindingResult bindingResultAvailability) {

        if(bindingResultCompetenceProfile.hasErrors() || bindingResultAvailability.hasErrors()) {
            System.out.println(bindingResultCompetenceProfile.toString());
            System.out.println(bindingResultAvailability.toString());

            return "applicant/application";
        }

        applicationService.registerApplication(competenceProfile, availability);

        return "redirect:/applicant/application";
    }
}
