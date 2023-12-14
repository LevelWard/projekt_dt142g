package com.antonsskafferi.projekt_dt142g;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
@Named
@RequestScoped
public class Carte {
    int ID;

    int PRICE;

    String DESCRIPTION;

    String TYPE;

    ArrayList usersList;

    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }

    public int getPRICE(){
        return ID;
    }
    public void setPRICE(int PRICE){
        this.PRICE = PRICE;
    }


    public void setDESCRIPTION(String DESCRIPTION){
        this.DESCRIPTION = DESCRIPTION;
    }
    public String getDESCRIPTION(){
        return DESCRIPTION;
    }

    public void setTYPE(String TYPE){
        this.TYPE = TYPE;
    }

    public String getTYPE(){
        return TYPE;
    }

    Connection connection;

    //Used to establish connection
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asdb", "anton", "skafferi");
        }catch (Exception e){
            System.out.println(e);
        }
        return connection;

    }

    //Used to fetch all records

    public ArrayList usersList(){
        try {
            usersList = new ArrayList();
            connection = getConnection();
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from Carte");
            while (rs.next()) {
                Carte carte = new Carte();
                carte.setID(rs.getInt("ID"));
                carte.setPRICE(rs.getInt("PRICE"));
                carte.setDESCRIPTION(rs.getString("DESCRIPTION"));
                carte.setTYPE(rs.getString("TYPE"));
                usersList.add(carte);
            }
            connection.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return usersList;
    }

}
