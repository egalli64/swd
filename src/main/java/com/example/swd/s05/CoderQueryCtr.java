package com.example.swd.s05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.swd.dao.Coder;

@Controller
@RequestMapping("/s05")
public class CoderQueryCtr {
    private static final Logger log = LogManager.getLogger(CoderQueryCtr.class);

    private CoderQueryRepo repo;

    public CoderQueryCtr(CoderQueryRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/bySalaryRange")
    public String bySalaryRange(@RequestParam double low, @RequestParam double high, Model model) {
        log.traceEntry("byFirstName");

        Iterable<Coder> coders = repo.findBySalaryRange(low, high);
        model.addAttribute("message", "Found: " + coders);

        return "/s05/result";
    }
}
