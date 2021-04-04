package com.example.swd.s04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoderCtorCtrl {
    private static final Logger log = LogManager.getLogger(CoderCtorCtrl.class);

    private CoderRepo repo;

    @Autowired
    public CoderCtorCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    // more than a ctor, the one that should be used by Spring should be autowired
    public CoderCtorCtrl(CoderRepo repo, String message) {
        log.info(message);
        this.repo = repo;
    }

    @GetMapping("/s04c/coders")
    public String getAll(Model model) {
        log.traceEntry("getAll");

        model.addAttribute("message", repo.findAll());

        return "/s04/result";
    }
}
