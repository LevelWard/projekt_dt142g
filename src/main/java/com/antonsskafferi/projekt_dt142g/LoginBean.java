package com.antonsskafferi.projekt_dt142g;

import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.Objects;

@Stateless
@Named
public class LoginBean{
    private String username;
    private String password;
    public String login(){
        /*ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try{
            request.login(username, password);
            return "/secured/main.xhtml?faces-redirect=true";
        }catch (ServletException e){
            return "AdminInlogg.xhtml?error=true&faces-redirect=true";
        }*/


        if("olle".equals(username) && "hej123".equals(password)){
            return "main.xhtml";
        }
        else{
            //.addMessage(null, new FacesMessage("Login failed"));
            return "AdminInlogg.xhtml";
        }
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public  String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
