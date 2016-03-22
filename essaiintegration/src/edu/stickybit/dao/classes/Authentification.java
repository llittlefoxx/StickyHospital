/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.classes;

import com.dropbox.core.DbxWebAuth.Exception;
import edu.stickybit.technique.MyConnectionGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Tlili Mohamed Ali
 */
public class Authentification {

    private final Connection conn;
    public Authentification() throws SQLException {
        
        MyConnectionGroup cnx = new MyConnectionGroup();

        conn =cnx.getInstance();
    }
 int result=0;
    	public int auth(String user, String pass) throws SQLException {
            int RowNumber = 0;
        String sql = "select * from admin where login=? and pwd=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, pass);
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            RowNumber = res.getRow();
            res.getString(1);
            res.getString(2);
            System.out.println(RowNumber + " <--- nombre de lignes trouvé pour la fonction d'authentification");
        }
        if (RowNumber == 1) {
            System.out.println("Valid username & password !");
            result = 1;
            return result;
        } else {
            System.out.println(RowNumber + " <--- nombre de lignes trouvé");
            System.out.println("login ou mot de passe incorect !");
            result = 0;
            return result;
        }

	}
}
