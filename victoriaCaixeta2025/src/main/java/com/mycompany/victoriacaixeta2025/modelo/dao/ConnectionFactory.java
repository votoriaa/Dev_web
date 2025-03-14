/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.victoriacaixeta2025.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 13826640608
 * a classe ConnectionFactory é um exemplo padrao de design Singleton aplicado para gerenciar
 * conexoes com o banco de dados em uma aplicação java. O objetivo da classe é encapsular a
 * lógica para criar conexões com um banco de dados e garantir que apenas uma instancia de
 * ConnectionFactory seja criada durante o ciclo de vida
 */
public class ConnectionFactory {
    
    private static final String DB_URL = "jdbc:mysql://localhost3306/bdestudo";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    private static ConnectionFactory instance;
    
    private ConnectionFactory(){
        try{
            Class.forName(DB_DRIVER);
        }catch(ClassNotFoundException ex){
            throw new RuntimeException("Driver do banco de dados não encontrado.",ex);
        }
      }
    
    public static ConnectionFactory getInstance(){
        if (instance == null){
            instance = new ConnectionFactory();
        }
        return instance;
    }
    
    public Connection getConnection () throws  SQLException{
        return DriverManager.getConnection(DB_URL, DB_USER,DB_PASSWORD);
    }
    
    public PreparedStatement getPreparedStatement(String sql)throws  SQLException{
        Connection con = getConnection();
        return con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
    }
    

        
           
        
    
    
        
    
    

