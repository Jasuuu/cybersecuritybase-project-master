package sec.project.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import org.springframework.ui.Model;
import sec.project.repository.Dao;

@Controller
public class SignupController {

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, Model model) throws SQLException {
        Dao dao = new Dao();
        dao.save(new Signup(name, address));
        System.out.println("submitForm() name: " + name);
        model.addAttribute("nimi", name);
        model.addAttribute("osoite", address);
        return "done";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) throws SQLException {
        Dao dao = new Dao();
        List<Signup> lista = dao.findAll();
        /*        for (Signup item : lista) {
            System.out.println("nimi: " + item.getName());
            System.out.println("osoite: " + item.getAddress());
        }*/
        model.addAttribute("list", lista);
        return "singups";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam String username, @RequestParam String password) {
        if (!username.equals("admin") || !password.equals("secret")) {
            return "redirect:/login";
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public String deleteSignup(@RequestParam String name, @RequestParam String address) throws SQLException {
        Dao dao = new Dao();
        dao.delete(new Signup(name, address));
        return "redirect:/admin";
    }
}
