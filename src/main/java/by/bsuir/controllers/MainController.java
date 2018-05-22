package by.bsuir.controllers;


import by.bsuir.model.User;
import by.bsuir.model.UsersEntity;
import by.bsuir.service.Userservice;
import by.bsuir.service.logger.SppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
public class MainController extends BaseController {

    @Autowired
    Userservice userservice;

    @Autowired
    SppLogger sppLogger;




    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = {"/index", "/", ""}, method = RequestMethod.GET)
    public String index(ModelMap modelMap , Locale locale) {

        sppLogger.saveStr("welcom to page!");

        String cities = "[";
        List<String> citiesList = userservice.getAllUsersCities();

        for(String str : citiesList){
            cities += "'" + str + "', ";
        }
        cities += "]";


        String title =  messageSource.getMessage("Main", new Object[]{}, locale);
        modelMap.addAttribute("title", title);


        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "main/index.jsp");
        return "Template";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("Login", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "main/login.jsp");
        return "Template";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutGet(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("Login", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "main/login.jsp");
        return "Template";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String noAccess(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("Error", new Object[]{}, locale);
        modelMap.addAttribute("title", title);


        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "main/403.jsp");
        return "Template";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String noPage(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("Error", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "main/404.jsp");
        return "Template";
    }


    //////////////////////// OK

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("about", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "main/about.jsp");
        return "Template";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("Register", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "main/register.jsp");
        return "Template";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public User registerPost(ModelMap modelMap, @ModelAttribute User user) {
        if (user.isValid()) {
            UsersEntity findUser = userservice.getUserByLogin(user.getUsername());

            if (findUser == null) {
                User newUser = (User) userservice.saveUser(user);
                return newUser;
            }
        }
        return null;
    }

}
