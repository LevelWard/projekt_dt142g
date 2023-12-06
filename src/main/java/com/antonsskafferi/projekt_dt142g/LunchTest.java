package com.antonsskafferi.projekt_dt142g;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.NamedQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
    @NamedQuery(name = "lunchTest.allData", query = "SELECT l FROM LunchTest l")
    @ManagedBean
public class LunchTest {

    @RequestScoped
    int id;
    int price;
    String description;
    String type;

    ArrayList lunchList;
    FacesContext fc;
    private Map<String, Object> sessionMap = fc.getCurrentInstance().getExternalContext().getSessionMap();
    Connection connection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    // Used to establish connection
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asdb", "anton", "skafferi");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    // Used to fetch all records
    public ArrayList lunchList() {
        try {
            lunchList = new ArrayList();
            connection = getConnection();
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from LUNCH");
            while (rs.next()) {
                LunchTest lunchtest = new LunchTest();
                lunchtest.setId(rs.getInt("ID"));
                lunchtest.setPrice(rs.getInt("PRICE"));
                lunchtest.setDescription(rs.getString("DESCRIPTION"));
                lunchtest.setType(rs.getString("TYPE"));
                lunchList.add(lunchtest);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return lunchList;
    }

    // Used to save user record
    public String save() {
        int result = 0;
        try {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "insert into LUNCH(price,description,type) values(?,?,?)");
            stmt.setInt(1, price);
            stmt.setString(2, description);
            stmt.setString(3, type);
            result = stmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (result != 0)
            return "index.xhtml?faces-redirect=true";
        else return "create.xhtml?faces-redirect=true";
    }

    // Used to fetch record to update
    public String edit(int id) {
        LunchTest lunchtest = null;
        System.out.println(id);
        try {
            connection = getConnection();
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from LUNCH where id = " + (id));
            rs.next();
            lunchtest= new LunchTest();
            lunchtest.setId(rs.getInt("ID"));
            lunchtest.setPrice(rs.getInt("PRICE"));
            lunchtest.setDescription(rs.getString("DESCRIPTION"));
            lunchtest.setType(rs.getString("TYPE"));

            sessionMap.put("editLunch", lunchtest);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/edit.xhtml?faces-redirect=true";
    }

    // Used to update user record
    public String update(LunchTest l) {
    //int result = 0;
        try {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "update LUNCH set price=?,description=?,type=? where id=?");
            stmt.setInt(1, l.getPrice());
            stmt.setString(2, l.getDescription());
            stmt.setString(3, l.getType());
            stmt.setInt(4, l.getId());
            stmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println();
        }
        return "/index.xhtml?faces-redirect=true";
    }

    // Used to delete user record
    public void delete(int id) {
        try {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("delete from LUNCH where id = " + id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}