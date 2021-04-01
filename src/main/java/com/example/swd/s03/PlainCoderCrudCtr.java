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
public class PlainCoderCrudCtr {
    private static final Logger log = LogManager.getLogger(PlainCoderCrudCtr.class);
    private static final Integer TOM_ID = 202;

    private PlainCoderCrudRepo repo;

    public PlainCoderCrudCtr(PlainCoderCrudRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.traceEntry("create");

        try {
            Coder coder = repo.save(new Coder("Tom", "Slick", 1200));
            model.addAttribute("message", "Created: " + coder);
        } catch (Exception ex) {
            log.warn("Can't persist Tom");
            model.addAttribute("message", "Coder _not_ created!");
        }

        return "/s03/result";
    }

    @GetMapping("/rename")
    public String rename(Model model) {
        log.traceEntry("rename");

        repo.findById(TOM_ID).ifPresentOrElse(coder -> {
            coder.setFirstName(coder.getFirstName() + "e");
            Coder edited = repo.save(coder);
            model.addAttribute("message", "Saved: " + edited);
        }, () -> model.addAttribute("message", "No coder found with id " + TOM_ID));

        return "/s03/result";
    }

    @GetMapping("/check")
    public String check(Model model) {
        log.traceEntry("check");

        model.addAttribute("message", String.format("Coder %d %sfound", TOM_ID, repo.existsById(TOM_ID) ? "" : "NOT "));

        return "/s03/result";
    }
}
