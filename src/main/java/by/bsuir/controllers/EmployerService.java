package by.bsuir.controllers;

import by.bsuir.model.*;
import by.bsuir.service.serviceservice;
import by.bsuir.service.Thingservice;
import by.bsuir.service.Userservice;
import by.bsuir.service.mail.EmailSender;
import by.bsuir.service.mail.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
public class EmployerService extends BaseController {

    @Autowired
    Contractservice contractservice;
    @Autowired
    Userservice userservice;
    @Autowired
    Thingservice thingservice;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MessageBuilder messageBuilder;


    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @RequestMapping(value = "/employer/users_list", method = RequestMethod.GET)
    public String usersList(ModelMap modelMap, Locale locale) {

        UsersEntity user = userservice.getUserByLogin(authUser());

        List<User> users = userservice.getAllemployerUsers(user.getId());
        System.out.println(users.size());

        modelMap.addAttribute("users", users);

        String title = messageSource.getMessage("Main", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "employer/users_list.jsp");
        return "Template";
    }


    @RequestMapping(value = "/employer/send_mail", method = RequestMethod.POST)
    @ResponseBody
    public Employer sendMail(ModelMap modelMap, @ModelAttribute StatObject stat) {

        if (stat.getInt_list() != null) {
            for (Integer user_id : stat.getInt_list()) {

                try {
                    User u = userservice.getUserrrById(user_id);

                    new EmailSender(mailSender).set(
                            messageBuilder.init(u, mailSender).
                                    subject(stat.getString_1()).
                                    body("<html><head><meta charset=\"UTF-8\"></head><body>" +
                                            "<h3> Hi, " + u.getFio() + "</h3>" +
                                            "<h3> Theme, " + stat.getString_1() + "</h3>" +
                                            "<div> Theme, " + stat.getString_2() + "</div>" +
                                            "</body></html>", true).
                                    construct()
                    ).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


        System.out.println(stat);

        return null;
    }


    //////////////////// ok

    @RequestMapping(value = "/employer/edit_info", method = RequestMethod.GET)
    public String editemployerInfo(ModelMap modelMap, Locale locale) {

        String title = messageSource.getMessage("edit_info", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        Employer employerInfo = userservice.getemployerByLogin(this.authUser());

        modelMap.addAttribute("employerInfo", employerInfo);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "employer/edit_employer.jsp");
        return "Template";
    }


    @RequestMapping(value = "/employer/save_employer", method = RequestMethod.POST)
    @ResponseBody
    public Employer editemployerPOST(ModelMap modelMap, @ModelAttribute Employer employer) {
        if (employer.isValid()) {

            Employer oldEmployer = userservice.getemployerById(employer.getId());

            employer.setservices(oldEmployer.getcontracts());
            employer.setthings(oldEmployer.getthings());

            Employer newUser = (Employer) userservice.saveUser(employer);
            return newUser;
        }
        return null;
    }


    @RequestMapping(value = "/employer", method = RequestMethod.GET)
    public String index(ModelMap modelMap, Locale locale) {

        String title = messageSource.getMessage("Main", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "employer/index.jsp");
        return "Template";
    }


    @RequestMapping(value = "/employer/contracts_list", method = RequestMethod.GET)
    public String contractsList(ModelMap modelMap, Locale locale) {

        String title = messageSource.getMessage("contracts_list", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        Employer employer = UserService.getemployerByLogin(this.authUser());

        List<ContractEntity> contractsList = ContractService.getemployercontracts(employer);
        modelMap.addAttribute("contractsList", contractsList);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "employer/contracts_list.jsp");
        return "Template";
    }


    @RequestMapping(value = "/employer/edit_contract/{id}", method = RequestMethod.GET)
    public String editcontract(@PathVariable Integer id, ModelMap modelMap, Locale locale) {

        String title = messageSource.getMessage("edit_contract", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        ContractEntity contractEntity = ContractService.getById(id);

        modelMap.addAttribute("contractEntity", contractEntity);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "employer/add_contract.jsp");
        return "Template";
    }


    @RequestMapping(value = "/employer/add_contract", method = RequestMethod.GET)
    public String addcontract(ModelMap modelMap, Locale locale) {

        String title = messageSource.getMessage("add_contract", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());

        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "employer/add_contract.jsp");
        return "Template";
    }


    // список тарифов с возм удаления
    @RequestMapping(value = "/employer/things_list", method = RequestMethod.GET)
    public String thingsList(ModelMap modelMap, Locale locale) {

        String title = messageSource.getMessage("things_list", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        Employer employer = UserService.getemployerByLogin(this.authUser());

        List<ThingEntity> things = ThingService.getemployerthings(employer);

        System.out.println(things.size());

        modelMap.addAttribute("thingsList", things);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "employer/things_list.jsp");
        return "Template";
    }


    @RequestMapping(value = "/employer/edit_thing/{id}", method = RequestMethod.GET)
    public String editthing(@PathVariable Integer id, ModelMap modelMap, Locale locale) {

        String title = messageSource.getMessage("edit_thing", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        ThingEntity thingEntity = ThingService.getById(id);
        modelMap.addAttribute("thingEntity", thingEntity);

        Employer employer = UserService.getemployerByLogin(this.authUser());

        List<ContractEntity> contractsList = ContractService.getemployercontracts(employer);

        modelMap.addAttribute("contractsList", contractsList);


        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "employer/add_thing.jsp");
        return "Template";
    }


    @RequestMapping(value = "/employer/add_thing", method = RequestMethod.GET)
    public String addthing(ModelMap modelMap, Locale locale) {

        String title = messageSource.getMessage("add_thing", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "employer/add_thing.jsp");
        return "Template";
    }


    @RequestMapping(value = "/employer/save_contract", method = RequestMethod.POST)
    @ResponseBody
    public ContractEntity addemployerPOST(ModelMap modelMap, @ModelAttribute ContractEntity contractEntity) {
        if (contractEntity.isValid()) {

            // temp
            Employer employer = UserService.getemployerByLogin(this.authUser());
            contractEntity.setOwner(employer);
            return ContractService.save(contractEntity);
        }
        return null;
    }


    @RequestMapping(value = "/employer/delete_thing/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deletething(@PathVariable Integer id, ModelMap modelMap) {
        // temp
        Employer employer = UserService.getemployerByLogin(this.authUser());

        ThingEntity thingEntity = ThingService.getById(id);
        thingEntity.setcontracts(null);
        ThingService.save(thingEntity);

        if (id != null) {

            try {

                List<User> users = UserService.getAllUsersBything(id);

                ThingService.delete(id);

                if (users != null) {

                    for (User u : users) {
                        try {
                            new EmailSender(mailSender).set(
                                    messageBuilder.init(u, mailSender).
                                            subject("DELETED thing").
                                            body("<html><head><meta charset=\"UTF-8\"></head><body>" +
                                                    "<h3> Hi, " + u.getFio() + "</h3>" +
                                                    "<h3> Theme: DELETE thing FOR " + employer.getTitle() + " employer </h3>" +
                                                    "</body></html>", true).
                                            construct()
                            ).start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return "{\"result\":\"true\"}";
        }
        return "{\"result\":\"true\"}";
    }


    @RequestMapping(value = "/employer/delete_contract/{id}", method = RequestMethod.POST)
    public Boolean deletecontract(@PathVariable Integer id, ModelMap modelMap) {
        // temp
        Employer employer = UserService.getemployerByLogin(this.authUser());

        ContractEntity contractEntity = ContractService.getById(id);

        if (contractEntity != null && contractEntity.getOwner().getId().equals(employer.getId())) {
            return ContractService.delete(contractEntity);
        }

        return false;
    }


    @RequestMapping(value = "/employer/save_thing", method = RequestMethod.POST)
    @ResponseBody
    public ThingEntity addthingPOST(ModelMap modelMap, @ModelAttribute ThingEntity thingEntity) {
        if (thingEntity.isValid()) {

            if (thingEntity.getId() != null) {
                ThingEntity oldthing = ThingService.getById(thingEntity.getId());
                thingEntity.setcontracts(oldthing.getcontracts());
            }


            // temp
            Employer employer = UserService.getemployerByLogin(this.authUser());

            thingEntity.setOwner(employer);


            return ThingService.save(thingEntity);
        }
        return null;
    }


    @RequestMapping(value = "/employer/save_or_delete_contract_in_thing", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addOrDeletecontractInthingPOST(ModelMap modelMap, @ModelAttribute StatObject statObject) {

        ThingEntity thingEntity = ThingService.getById(statObject.getField_2());

        if (statObject.getString_1().equals("false")) {
            for (ContractEntity contractEntity : thingEntity.getcontracts()) {
                if (contractEntity.getId().equals(statObject.getField_1())) {
                    thingEntity.getcontracts().remove(contractEntity);
                    break;
                }
            }
        } else {
            ContractEntity contractEntity = ContractService.getById(statObject.getField_1());
            thingEntity.getcontracts().add(contractEntity);
        }

        ThingService.save(thingEntity);

        return true;
    }


}
