/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castejonpmp.dao;


import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author wwwjr
 */
public class Conn {
    private static Connection _connection = null;
    
    private Conn(){
        
    }
    
    public static Connection getConn(){
        try {
            
            if (_connection == null) {
                Class.forName("org.sqlite.JDBC");
                _connection = DriverManager.getConnection("jdbc:sqlite:caloriasdb.db");
                return  _connection;
            }else{
                return _connection;
            }
            
        } catch (Exception e) {
            System.err.println("Error bd conn: "+e.getMessage());
            return null;
        }
    }
}
