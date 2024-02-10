package com.example.is_site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

@Controller
public class SiteController {

    private final SiteEntityService service;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SiteController(SiteEntityService service, JdbcTemplate jdbcTemplate) {
        this.service = service;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/")
    public String redirectToLanding() {
        return "redirect:/landing";
    }

    @GetMapping("/clientHome")
    public String executeQuery(Model model) {
        List<SiteEntity> entries = service.getAllEntries();
        model.addAttribute("entries", entries);

        String databaseName = service.getDatabaseName();
        model.addAttribute("databaseName", databaseName);

        String databaseStatus = checkDatabaseConnection();
        model.addAttribute("databaseStatus", databaseStatus);

        return "clientHome";
    }

    @PostMapping("/clientHome/viewSteel")
    public String viewSteel(Model model) {
        executeQueryPost("steel_inventory", model);
        return "clientHome";
    }

    @PostMapping("/clientHome/viewRail")
    public String viewRail(Model model) {
        executeQueryPost("rail_inventory", model);
        return "clientHome";
    }

    @GetMapping("/landing")
    public String showLandingPage(Model model) {
        return "landing";
    }

    @GetMapping("/faq")
    public String showFAQ(Model model) {
        return "faq";
    }

    @GetMapping("/adminHome")
    public String adminHomePage(Model model) {
        List<SiteEntity> entries = service.getAllEntries();
        model.addAttribute("entries", entries);

        String databaseName = service.getDatabaseName();
        model.addAttribute("databaseName", databaseName);

        String databaseStatus = checkDatabaseConnection();
        model.addAttribute("databaseStatus", databaseStatus);

        return "adminHome";
    }

    @PostMapping("/login")
    public String adminLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("root".equals(username) && "root".equals(password)) {
            return "redirect:/adminHome";
        } else {
            model.addAttribute("error", "Authentication Error. Invalid username or password.");
            return "landing";
        }
    }

    @GetMapping("/login")
    public String n(Model model) {
        List<SiteEntity> entries = service.getAllEntries();
        model.addAttribute("entries", entries);

        String databaseName = service.getDatabaseName();
        model.addAttribute("databaseName", databaseName);

        String databaseStatus = checkDatabaseConnection();
        model.addAttribute("databaseStatus", databaseStatus);

        if (model.containsAttribute("error")) {
            model.addAttribute("error", model.getAttribute("error"));
        }

        return "landing";
    }

    private void executeQueryPost(String tableName, Model model) {
        try {
            String sqlQuery = "SELECT * FROM " + tableName;
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sqlQuery);
            model.addAttribute("queryResult", result);

            List<SiteEntity> entries = service.getAllEntries();
            model.addAttribute("entries", entries);

            String databaseName = service.getDatabaseName();
            model.addAttribute("databaseName", databaseName);

            String databaseStatus = "Connected";
            model.addAttribute("databaseStatus", databaseStatus);
        } catch (DataAccessException e) {
            model.addAttribute("error", "Error executing the query: " + e.getMessage());
        }
    }

    private String checkDatabaseConnection() {
        String databaseStatus = "";
        try {
            jdbcTemplate.execute("SELECT 1");
            databaseStatus += "Connected";
        } catch (DataAccessException e) {
            databaseStatus += "Not Connected (Exception: " + e.getMessage() + ")";
            e.printStackTrace();
        }
        return databaseStatus;
    }
}
