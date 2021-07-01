package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobs.mvc.controllers.ListController.tableChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {  //handler method renders the form defined in the search.html template

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // #3 - Create a handler to process a search request and render the updated search view.
    // lives at /search/results
    // method should take 3 parameters Model, two other parameters specifying type of search and search term
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;

        // If the user enters “all” in the search box, or if they leave the box empty, call the findAll() method from JobData. Otherwise, send the search information to findByColumnAndValue. In either case, store the results in a jobs ArrayList.
        if (searchTerm == "all" || searchTerm == "") {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("title", "Jobs With " + columnChoices.get(searchType) + ": "  + searchTerm);
        model.addAttribute("jobs", jobs);

        return "search";
    }

}

