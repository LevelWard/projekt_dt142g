package com.antonsskafferi.projekt_dt142g;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
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
    /*Har skapat en login funktion där admin kan logga in */
    public String login(){
        /*ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try{
            request.login(username, password);
            return "/secured/main.xhtml?faces-redirect=true";
        }catch (ServletException e){
            return "AdminInlogg.xhtml?error=true&faces-redirect=true";
        }*/

        //Om Username och password stämmer överrens med Olle och hej123
        //kan admin logga in och gå vidare till nästa sida
        //Om det är fel får admin prova igen

        if(Objects.equals(username, "olle") && Objects.equals(password, "hej123")){
            return "main.xhtml";
        }
        else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Felaktig inloggning, ange rätt info", "Misslyckad inloggnig"));
            return "AdminInlogg.xhtml";
        }
    }
}
