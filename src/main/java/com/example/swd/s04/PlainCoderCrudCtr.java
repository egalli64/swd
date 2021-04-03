package com.example.swd.s04;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.swd.dao.Coder;

@Controller
@RequestMapping("/s04")
public class PlainCoderCrudCtr {
    private static final Logger log = LogManager.getLogger(PlainCoderCrudCtr.class);

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

        return "/s04/result";
    }

    @GetMapping("/rename")
    public String rename(@RequestParam Integer id, Model model) {
        log.traceEntry("rename");

        repo.findById(id).ifPresentOrElse(coder -> {
            coder.setFirstName(coder.getFirstName() + "e");
            Coder edited = repo.save(coder);
            model.addAttribute("message", "Saved: " + edited);
        }, () -> model.addAttribute("message", "No coder found with id " + id));

        return "/s04/result";
    }

    @GetMapping("/check")
    public String check(@RequestParam Integer id, Model model) {
        log.traceEntry("check");

        model.addAttribute("message", String.format("Coder %d %sfound", id, repo.existsById(id) ? "" : "NOT "));

        return "/s04/result";
    }

    @GetMapping("/all")
    public String all(Model model) {
        log.traceEntry("all");

        model.addAttribute("message", "Found: " + repo.findAll());

        return "/s04/result";
    }

    @GetMapping("/some")
    public String some(@RequestParam List<Integer> ids, Model model) {
        log.traceEntry("some");

        model.addAttribute("message", "Found: " + repo.findAllById(ids));

        return "/s04/result";
    }

    @GetMapping("/count")
    public String count(Model model) {
        log.traceEntry("count");

        model.addAttribute("message", String.format("Found %d coders", repo.count()));

        return "/s04/result";
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam Integer id, Model model) {
        log.traceEntry("deleteById");

        try {
            repo.deleteById(id);
            return "redirect:/s04/check?id=" + id;
        } catch (Exception ex) {
            model.addAttribute("message", "Can't delete coder " + id);
            return "/s04/result";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, Model model) {
        log.traceEntry("delete");

        Optional<Coder> opt = repo.findById(id);
        if (opt.isPresent()) {
            try {
                repo.delete(opt.get());
                return "redirect:/s04/check?id=" + id;
            } catch (Exception ex) {
                log.warn("Can't delete coder " + id);
            }
        }

        model.addAttribute("message", "Can't delete coder " + id);
        return "/s04/result";
    }

    @GetMapping("/deleteSome")
    public String deleteSome(@RequestParam List<Integer> ids, Model model) {
        log.traceEntry("deleteSome");

        List<Coder> coders = new ArrayList<Coder>();
        ids.forEach(id -> repo.findById(id).ifPresent(coder -> coders.add(coder)));

        repo.deleteAll(coders);

        model.addAttribute("message", "To be deleted: " + coders);

        return "/s04/result";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(Model model) {
        log.traceEntry("deleteAll");

        repo.deleteAll();

        return "redirect:/s04/count";
    }
}
