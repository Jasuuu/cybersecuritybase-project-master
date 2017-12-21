/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sec.project.domain.Signup;

/**
 *
 * @author jasudev
 */
public class Dao {
    private Connection connection;
    
    public Dao() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");
    }
    
    public void save(Signup su) throws SQLException {
         this.connection.createStatement().execute("insert into signups (name, address) values ('" + su.getName() +  "', '" + su.getAddress() + "')");
    }
    
    public List<Signup> findAll() throws SQLException {
        List<Signup> lista = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM signups");
        while (resultSet.next()) {
            Signup su = new Signup(resultSet.getString("name"), resultSet.getString("address"));
            lista.add(su);
        }
        return lista;
    }
    
    public void delete(Signup su) throws SQLException {
        this.connection.createStatement().execute("DELETE FROM signups where name = '" + su.getName() + "' and address = '" + su.getAddress() + "'");
    }
}
