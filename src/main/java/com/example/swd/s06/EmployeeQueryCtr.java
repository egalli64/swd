package com.example.swd.s06;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.swd.dao.Employee;

@Controller
@RequestMapping("/s06")
public class EmployeeQueryCtr {
    private static final Logger log = LogManager.getLogger(EmployeeQueryCtr.class);

    private EmployeeQueryRepo repo;

    public EmployeeQueryCtr(EmployeeQueryRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/bySalaryRange")
    public String bySalaryRange(@RequestParam double low, @RequestParam double high, Model model) {
        log.traceEntry("bySalaryRange({}, {})", low, high);

        Iterable<Employee> entities = repo.findBySalaryRange(low, high);
        model.addAttribute("message", "Found: " + entities);

        return "/s06/result";
    }

    @GetMapping("/bySalaryRangeNames")
    public String bySalaryRangeNames(@RequestParam double low, @RequestParam double high, Model model) {
        log.traceEntry("bySalaryRangeNames({}, {})", low, high);

        Iterable<Employee> entities = repo.findBySalaryRangeNames(low, high);
        model.addAttribute("message", "Found: " + entities);

        return "/s06/result";
    }

    @GetMapping("/withPrefix")
    public String withPrefix(@RequestParam String prefix, Model model) {
        log.traceEntry("withPrefix({})", prefix);

        Iterable<Employee> entities = repo.findByFirstName(prefix);
        model.addAttribute("message", "Found: " + entities);

        return "/s06/result";
    }

    @GetMapping("/withInfix")
    public String withInfix(@RequestParam String infix, Model model) {
        log.traceEntry("withInfix({})", infix);

        Iterable<Employee> entities = repo.findByFirstNameIn(infix);
        model.addAttribute("message", "Found: " + entities);

        return "/s06/result";
    }

    @GetMapping("/bySalaryRangeNative")
    public String bySalaryRangeNative(@RequestParam double low, @RequestParam double high, Model model) {
        log.traceEntry("bySalaryRangeNative({}, {})", low, high);

        Iterable<Employee> entities = repo.findBySalaryRangeNative(low, high);
        model.addAttribute("message", "Found: " + entities);

        return "/s06/result";
    }
}
