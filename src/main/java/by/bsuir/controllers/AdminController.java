package by.bsuir.controllers;

import by.bsuir.model.Employer;
import by.bsuir.model.UsersEntity;
import by.bsuir.contract.ThingService;
import by.bsuir.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


@Controller
public class AdminController extends BaseController {

    @Autowired
    UserService UserService;

    @Autowired
    ThingService ThingService;

    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(ModelMap modelMap, Locale locale)
    {
        String title =  messageSource.getMessage("main_adminpanel", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "admin/index.jsp");
        return "Template";
    }


    @RequestMapping(value = "/admin/employers_list", method = RequestMethod.GET)
    public String employersList(ModelMap modelMap, Locale locale)
    {
        String title =  messageSource.getMessage("employers_list", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        List<Employer> employersList = UserService.getemployersList();

        modelMap.addAttribute("employersList", employersList);
        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "admin/employers_list.jsp");
        return "Template";
    }


    // стр с формой добавления
    @RequestMapping(value = "/admin/new_employer", method = RequestMethod.GET)
    public String addemployer(ModelMap modelMap, Locale locale)
    {
        String title =  messageSource.getMessage("new_employer", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "admin/new_employerr.jsp");
        return "Template";
    }


    @RequestMapping(value = "/admin/save_employer", method = RequestMethod.POST)
    @ResponseBody
    public Employer addemployerPOST(ModelMap modelMap, @ModelAttribute Employer employer) {
        if (employer.isValid()) {
            UsersEntity findUser = UserService.getUserByLogin(employer.getUsername());

            if(findUser == null){
                Employer newUser = (Employer) UserService.saveUser(employer);
                return newUser;
            }
        }
        return null;
    }


    @RequestMapping(value = "/admin/delete_employer/{id}", method = RequestMethod.POST)
    public Boolean deleteemployer(@PathVariable Integer id, ModelMap modelMap) {
        // temp
        UsersEntity usersEntity = UserService.getUserById(id);

        if(usersEntity != null){
            UserService.delete(usersEntity);
            return true;
        }

        return false;
    }

}
