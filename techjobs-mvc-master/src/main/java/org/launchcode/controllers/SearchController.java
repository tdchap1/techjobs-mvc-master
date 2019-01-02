package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }


    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "/results")
    public String results(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
    @RequestMapping(value = "/results", method = RequestMethod.GET, params = {"searchType", "searchTerm"})
    public String results(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {

        //Conditional-or for 'all' or 'none' search terms
        ArrayList<HashMap<String, String>> jobs;

        if (searchType.equals("all") || searchType.equals("")) {
            jobs = JobData.findByValue(searchTerm);
        }

        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("jobsSize", jobs.size());

        return "search";
    }
}