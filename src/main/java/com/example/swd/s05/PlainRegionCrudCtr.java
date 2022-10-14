package com.example.swd.s05;

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

import com.example.swd.dao.Region;

@Controller
@RequestMapping("/s05")
public class PlainRegionCrudCtr {
    private static final Logger log = LogManager.getLogger(PlainRegionCrudCtr.class);

    private PlainRegionCrudRepo repo;

    public PlainRegionCrudCtr(PlainRegionCrudRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/create")
    public String create(@RequestParam String name, Model model) {
        log.traceEntry("create");

        try {
            Region entity = repo.save(new Region(name));
            model.addAttribute("message", "Created: " + entity);
        } catch (Exception ex) {
            log.warn("Can't persist {}", name);
            model.addAttribute("message", "Entity _not_ created!");
        }

        return "/s05/result";
    }

    @GetMapping("/rename")
    public String rename(@RequestParam Integer id, @RequestParam String name, Model model) {
        log.traceEntry("rename");

        repo.findById(id).ifPresentOrElse(entity -> {
            entity.setName(name);
            Region edited = repo.save(entity);
            model.addAttribute("message", "Saved: " + edited);
        }, () -> model.addAttribute("message", "No entity found with id " + id));

        return "/s05/result";
    }

    @GetMapping("/check")
    public String check(@RequestParam Integer id, Model model) {
        log.traceEntry("check");

        model.addAttribute("message", String.format("Entity %d %sfound", id, repo.existsById(id) ? "" : "NOT "));

        return "/s05/result";
    }

    @GetMapping("/all")
    public String all(Model model) {
        log.traceEntry("all");

        model.addAttribute("message", "Found: " + repo.findAll());

        return "/s05/result";
    }

    @GetMapping("/some")
    public String some(@RequestParam List<Integer> ids, Model model) {
        log.traceEntry("some");

        model.addAttribute("message", "Found: " + repo.findAllById(ids));

        return "/s05/result";
    }

    @GetMapping("/count")
    public String count(Model model) {
        log.traceEntry("count");

        model.addAttribute("message", String.format("Found %d entities", repo.count()));

        return "/s05/result";
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam Integer id, Model model) {
        log.traceEntry("deleteById");

        try {
            repo.deleteById(id);
            return "redirect:/s05/check?id=" + id;
        } catch (Exception ex) {
            model.addAttribute("message", "Can't delete entity " + id);
            return "/s05/result";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, Model model) {
        log.traceEntry("delete");

        Optional<Region> opt = repo.findById(id);
        if (opt.isPresent()) {
            try {
                repo.delete(opt.get());
                return "redirect:/s05/check?id=" + id;
            } catch (Exception ex) {
                log.warn("Can't delete entity " + id);
            }
        }

        model.addAttribute("message", "Can't delete entity " + id);
        return "/s05/result";
    }

    @GetMapping("/deleteSome")
    public String deleteSome(@RequestParam List<Integer> ids, Model model) {
        log.traceEntry("deleteSome");

        List<Region> regions = new ArrayList<>();
        ids.forEach(id -> repo.findById(id).ifPresent(entity -> regions.add(entity)));

        repo.deleteAll(regions);

        model.addAttribute("message", "To be deleted: " + regions);

        return "/s05/result";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(Model model) {
        log.traceEntry("deleteAll");

        repo.deleteAll();

        return "redirect:/s05/count";
    }
}
