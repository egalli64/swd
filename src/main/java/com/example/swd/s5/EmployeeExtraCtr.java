package com.example.swd.s5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.swd.m2.entity.Employee;

@Controller
@RequestMapping("/s5")
public class EmployeeExtraCtr {
    private static final Logger log = LogManager.getLogger(EmployeeExtraCtr.class);

    private EmployeeExtraRepo repo;

    public EmployeeExtraCtr(EmployeeExtraRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/byFirstName")
    public String byFirstName(@RequestParam String name, Model model) {
        log.traceEntry("byFirstName({})", name);

        Iterable<Employee> entities = repo.findByFirstName(name);
        model.addAttribute("message", "Found: " + entities);

        return "/s5/result";
    }

    @GetMapping("/firstNameStarting")
    public String firstNameStarting(@RequestParam String prefix, Model model) {
        log.traceEntry("firstNameStarting({})", prefix);

        Iterable<Employee> entities = repo.findByFirstNameStartingWith(prefix);
        model.addAttribute("message", "Found: " + entities);

        return "/s5/result";
    }

    @GetMapping("/firstNameStartingLastNameContaining")
    public String firstNameStartingLastNameContaining( //
            @RequestParam String first, @RequestParam String last, Model model) {
        log.traceEntry("firstNameStartingLastNameContaining({}, {})", first, last);

        Iterable<Employee> entities = repo.findByFirstNameStartingWithOrLastNameContainingIgnoreCase(first, last);
        model.addAttribute("message", "Found: " + entities);

        return "/s5/result";
    }
}
