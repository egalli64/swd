package com.example.swd.s04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegionCtorCtrl {
    private static final Logger log = LogManager.getLogger(RegionCtorCtrl.class);

    private RegionRepo repo;

    @Autowired
    public RegionCtorCtrl(RegionRepo repo) {
        this.repo = repo;
    }

    // more than a ctor, the one that should be used by Spring should be autowired
    public RegionCtorCtrl(RegionRepo repo, String message) {
        log.info(message);
        this.repo = repo;
    }

    @GetMapping("/s04c/region/all")
    public String getAll(Model model) {
        log.traceEntry("getAll");

        model.addAttribute("message", repo.findAll());

        return "/s04/result";
    }
}
