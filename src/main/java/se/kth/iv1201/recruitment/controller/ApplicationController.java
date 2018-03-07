package se.kth.iv1201.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.recruitment.entity.*;
import se.kth.iv1201.recruitment.repository.CompetenceRepository;
import se.kth.iv1201.recruitment.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Locale;

/**
 * A controller that handles traffic between the application form and application list, and the application service used to store and retrieve from db.
 */
@Controller
public class ApplicationController {
    private final Locale DEFAULT_LOCALE = Locale.ENGLISH;

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
        model.addAttribute("competences", addCompetences());
        model.addAttribute("competenceProfile", addCompetenceProfile());
        model.addAttribute("availability", addAvailability());
        model.addAttribute("applications", addApplications());

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
     * @return String with a view.
     */
    @PostMapping("/applicant/application")
    public String postApplication(@Valid CompetenceProfileDTO competenceProfile, BindingResult bindingResultCompetenceProfile,
                                  @Valid AvailabilityDTO availability, BindingResult bindingResultAvailability,
                                  Model model) {

        if(bindingResultCompetenceProfile.hasErrors() || bindingResultAvailability.hasErrors()) {

            model.addAttribute("error", new Exception("Invalid date."));
            return "applicant/application";
        }

        try {
            applicationService.registerApplication(competenceProfile, availability);
        } catch (Exception ex) {
            //log
            System.out.println(ex.getMessage());
        }
        model.addAttribute("applications", addApplications());

        return "redirect:/applicant/application?new=true";
    }

    @ModelAttribute("competenceProfile")
    private CompetenceProfileDTO addCompetenceProfile() {
        return new CompetenceProfileDTO();
    }

    @ModelAttribute("competences")
    private List<Competence> addCompetences() {
        Locale locale = request.getLocale();

        List<Competence> competences = new ArrayList<>();
        competenceRepository.findByLang(locale.getLanguage()).forEach(competences::add);

        if (competences.isEmpty()) {
            competenceRepository.findByLang(DEFAULT_LOCALE.getLanguage()).forEach(competences::add);
        }

        return competences;
    }

    @ModelAttribute("availability")
    private AvailabilityDTO addAvailability() {
        return new AvailabilityDTO();
    }

    @ModelAttribute("applications")
    private List<Application> addApplications() {
        return applicationService.getApplicationsForUser();
    }
}
