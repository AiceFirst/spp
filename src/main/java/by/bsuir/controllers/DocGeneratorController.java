package by.bsuir.controllers;

import by.bsuir.model.ThingEntity;
import by.bsuir.model.User;
import by.bsuir.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class DocGeneratorController {

    @Autowired
    ReportGenerationcontract reportGenerationcontract;

    @Autowired
    UserService UserService;

    @Autowired
    Paymentcontract paymentcontract;

    @Autowired
    ThingService ThingService;

    @Autowired
    ContractService ContractService;

    @RequestMapping(value = "/documents/user_contract/{user_login}/{thing_id}/", method = RequestMethod.GET)
    public void editcontract(@PathVariable String user_login, @PathVariable String thing_id, HttpServletResponse response) {

        try {
            User user = UserService.getUserrrById(Integer.parseInt(user_login));
            ThingEntity thing = ThingService.getById(Integer.parseInt(thing_id));
            reportGenerationcontract.generateUserService(response.getOutputStream(), user, thing, new Date());
            response.flushBuffer();
            response.setContentType("application/pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
