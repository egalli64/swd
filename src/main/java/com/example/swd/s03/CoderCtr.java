package com.example.swd.s03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.swd.dao.Coder;

@Controller
@RequestMapping("/s03")
public class CoderCtr {
    private static final Logger log = LogManager.getLogger(CoderCtr.class);

    private PlainCoderCrudRepo repo;

    public CoderCtr(PlainCoderCrudRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.traceEntry("create");

        try {
            Coder coder = repo.save(new Coder("Tom", "Slick", 1200));
            model.addAttribute("message", "Coder created: " + coder);
        } catch (Exception ex) {
            log.warn("Can't persist Tom");
            model.addAttribute("message", "Coder _not_ created!");
        }

        return "/s03/result";
    }

    @GetMapping("/update")
    public String update() {
        log.traceEntry("update");
        return "/s03/result";
    }
}
