/**
 * Introduction to Spring JDBC support
 * 
 * https://github.com/egalli64/swd/
 */
package com.example.swd.m1.s4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.swd.m1.entity.Region;

@Controller
@RequestMapping("/m1/s4")
public class JdbcClientRegionController {
    private static final Logger log = LogManager.getLogger(JdbcClientRegionController.class);

    private JdbcClientRegionRepository repo;

    public JdbcClientRegionController(JdbcClientRegionRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/read/{id}")
    public String read(@PathVariable Integer id, Model model) {
        log.traceEntry("read({})", id);

        try {
            Region region = repo.findById(id);
            model.addAttribute("message", "Read region " + region);
        } catch (DataAccessException ex) {
            log.warn(String.format("Can't read region %d", id), ex);
            model.addAttribute("message", "Entity _not_ read!");
        }

        return "/m1/s3/result";
    }

    @PostMapping("/create")
    public String create(@RequestParam String name, Model model) {
        log.traceEntry("create({})", name);

        try {
            repo.save(new Region(name));
            model.addAttribute("message", "Created region with name " + name);
        } catch (DataAccessException ex) {
            log.warn("Can't create entity", ex);
            model.addAttribute("message", "Entity _not_ created!");
        }

        return "/m1/s3/result";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam String name, Model model) {
        log.traceEntry("update({}, {})", id, name);

        try {
            Region region = new Region(id, name);
            repo.save(region);
            model.addAttribute("message", "Updated: " + region);
        } catch (DataAccessException ex) {
            log.warn("Can't update", ex);
            model.addAttribute("message", "Entity _not_ updated!");
        }

        return "/m1/s3/result";
    }
}
