/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author Calvin ILOKI
 */
@Named(value = "checkDataSource")
@RequestScoped
public class CheckDataSource {

    @PersistenceContext
    EntityManager entityManager;
    public CheckDataSource() {
    }

    public void run() {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/cargo");
            Connection cnx = ds.getConnection();
            
            System.out.println("Context Time Out: " + ds.getLoginTimeout()
                    + "\nSchema : " + cnx.getSchema()
                    + "\nDriver Name : " + cnx.getMetaData().getDriverName()
                    + "\nURL : " + cnx.getMetaData().getURL()
                    + "\nUsername : " + cnx.getMetaData().getUserName());
            Statement stmt = cnx.createStatement();
            ResultSet rslt = stmt.executeQuery("select * from congo_terminal where mois='201907' and rownum <10");
            while (rslt.next()) {
                System.out.println("Escale : " + rslt.getString("escale"));
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CheckDataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
