package by.bsuir.controllers;

import by.bsuir.model.*;
import by.bsuir.contract.ContractService;
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
public class UserController extends BaseController {

    @Autowired
    ContractService ContractService;
    @Autowired
    UserService UserService;
    @Autowired
    ThingService ThingService;
    @Autowired
    by.bsuir.contract.PaymentService paymentcontract;

    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/user/add_employer/{id}", method = RequestMethod.GET)
    public String addemployer(@PathVariable Integer id, ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("add_employer", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        User user = UserService.gettUserrrByLogin(this.authUser());

        Employer employer = UserService.getFullemployerById(id);

        modelMap.addAttribute("employer", employer);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "user/add_employer.jsp");
        return "Template";
    }


    @RequestMapping(value = "/user/add_employer", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addemployerPOST(ModelMap modelMap, @ModelAttribute StatObject statObject) {

        Employer employer = UserService.getemployerById(statObject.getField_2());
        User user = UserService.getUserrrById(statObject.getField_1());
        Integer thing_id = statObject.getField_3();

        user.getemployers().add(employer);

        UserService.saveUser(user);

        UserService.updateUseremployerthing(statObject.getField_1(), statObject.getField_2(), thing_id);


        return true;
//        return UserService.updateUseremployerthing(user_id, employer_id, thing_id);
    }


    /////////// okkk

    @RequestMapping(value = "/user/payments_history", method = RequestMethod.GET)
    public String paymentsHistory(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("payment_history", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        User user = UserService.gettUserrrByLogin(this.authUser());

        List<PaymentEntity> payments = paymentcontract.getUserPayments(user.getId());


        modelMap.addAttribute("payments", payments);
        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "user/payments_history.jsp");
        return "Template";
    }


    @RequestMapping(value = "/user/add_payment", method = RequestMethod.GET)
    public String addPayment(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("add_payment", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        User user = UserService.gettUserrrByLogin(this.authUser());

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "user/add_payment.jsp");
        return "Template";
    }


    @RequestMapping(value = "/user/add_payment", method = RequestMethod.POST)
    @ResponseBody
    public PaymentEntity addPaymentPOST(ModelMap modelMap, @ModelAttribute StatObject statObject) {

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount(statObject.getField_3());
        paymentEntity.setemployer_id(statObject.getField_2());
        paymentEntity.setUser_id(statObject.getField_1());

        return paymentcontract.addPayment(paymentEntity);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String index(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("Main", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "user/index.jsp");
        return "Template";
    }


    @RequestMapping(value = "/user/employers_list", method = RequestMethod.GET)
    public String employersList(ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("employers_list", new Object[]{}, locale);
        modelMap.addAttribute("title", title);

        List<Employer> employersList = UserService.getemployersList();

        User user = UserService.gettUserrrByLogin(this.authUser());


        modelMap.addAttribute("employersList", employersList);
        modelMap.addAttribute("user", user);

        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "user/employers_list.jsp");
        return "Template";
    }


    @RequestMapping(value = "/user/edit_employer/{id}", method = RequestMethod.GET)
    public String editemployer(@PathVariable Integer id, ModelMap modelMap, Locale locale) {

        String title =  messageSource.getMessage("edit_employer", new Object[]{}, locale);
        modelMap.addAttribute("title", title);


        User user = UserService.gettUserrrByLogin(this.authUser());

        Employer employer = UserService.getFullemployerById(id);


//        UserService.updateUseremployerthing(user.getId(), employer.getId(), 5);
        Integer employerUserthingId = UserService.useremployerthing(user.getId(), employer.getId());

        modelMap.addAttribute("employer", employer);
        modelMap.addAttribute("employerUserthingId", employerUserthingId);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("userName", this.authUser());
        modelMap.addAttribute("menu", this.getMenuForUser());
        modelMap.addAttribute("view", "user/edit_employer.jsp");
        return "Template";
    }


    @RequestMapping(value = "/user/save_or_delete_contract_in_user", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addOrDeletecontractInUserPOST(ModelMap modelMap, @ModelAttribute StatObject statObject) {

        User user = UserService.getUserrrById(statObject.getField_2());


        if (statObject.getString_1().equals("false")) {
            for (ContractEntity contractEntity : user.getcontracts()) {
                if (contractEntity.getId().equals(statObject.getField_1())) {
                    user.getcontracts().remove(contractEntity);
                    break;
                }
            }
        } else {
            ContractEntity contractEntity = ContractService.getById(statObject.getField_1());
            user.getcontracts().add(contractEntity);
        }

        UserService.saveUser(user);


        return true;
    }


    @RequestMapping(value = "/user/update_user_employer_thing", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateUseremployerthingPOST(ModelMap modelMap, @ModelAttribute StatObject statObject) {

        Integer thing_id = statObject.getField_1();
        Integer user_id = statObject.getField_2();
        Integer employer_id = statObject.getField_3();

        return UserService.updateUseremployerthing(user_id, employer_id, thing_id);
    }

}


