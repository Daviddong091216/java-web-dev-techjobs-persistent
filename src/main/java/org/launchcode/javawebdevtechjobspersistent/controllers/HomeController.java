package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Tech Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("job", new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "/jobs/add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    @RequestParam int employer,
                                    @RequestParam List<Integer> skills,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "/jobs/add";
        }

//        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//        newJob.setSkills(skillObjs);

        Optional optEmployer = employerRepository.findById(employer);
        if (optEmployer.isPresent()) {
            Employer aEmployer = (Employer) optEmployer.get();
            model.addAttribute("employer", aEmployer);
            jobRepository.save(newJob);
            return "/jobs/view";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()) {
            Job aJob = (Job) optJob.get();
            model.addAttribute("job", aJob);
            return "/jobs/view";
        } else {
            return "redirect:../";
        }

//        model.addAttribute("job", jobRepository.findById(jobId));
//        return "/jobs/view";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Job");
        model.addAttribute("jobs", jobRepository.findAll());
        return "/jobs/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] jobIds) {
        if (jobIds != null) {
            for (int id : jobIds) {
                jobRepository.deleteById(id);
            }
        }
        return "redirect:../";
    }


}
