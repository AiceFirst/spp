package by.bsuir.controllers;

import by.bsuir.model.UsersEntity;
import by.bsuir.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.contract;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@contract
public class BaseController {

    @Autowired
    UserService UserService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    public String authUser() {

        try {

            /*HttpServletRequest curRequest =
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                            .getRequest();*/

            Principal p = httpServletRequest.getUserPrincipal();
            if(p == null){
                return null;
            }else {
                return p.getName();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMenuForUser() {
        if (this.authUser() != null) {
            UsersEntity user = UserService.getUserByLogin(this.authUser());

            if (user != null && user.getType().equals("employer")) {
                return "employer/menu.jsp";
            } else if (user.getType().equals("user")) {
                return "user/menu.jsp";
            } else {
                return "admin/menu.jsp";
            }

        }
        return "";
    }

}
