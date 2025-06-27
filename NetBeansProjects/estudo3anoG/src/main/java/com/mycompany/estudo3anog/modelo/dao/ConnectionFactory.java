/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudo3anog.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
A classe ConnectionFactory é um exemplo do padrão de design Singleton aplicado para gerenciar
* conexões com o banco de dados em uma aplicação java. O objetivo da classe é encapsular a  
* lógica para criar conexões com o banco de dados e garantir que apenas uma instância de 
* ConnectioFactory seja criada durante o ciclo de vida
 */
public class ConnectionFactory {
    
    private static final String DB_URL= "jdbc:mysql://localhost:3306/bdestudo?useSSL=false";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";
    
    //variável estática que mantém a instância única da ConnectioFactory
    
    private static ConnectionFactory instance;
    
    // O construtor é privado para impedir a criação direta de instâncias da classe fora dela mesma
    
    private ConnectionFactory(){
        try{
            Class.forName(DB_DRIVER);
        }catch(ClassNotFoundException ex){
            throw new RuntimeException("Driver do banco de dados não encontrado.",ex);
        }
    }
    
    /* public static ConnectionFactory getInstance(): método estático que permite o acesso à
    nstância única de ConnectioFactory: Padrão SingleTon -> garante que apenas uma instância
    seja usada em toda aplicação*/
    public static ConnectionFactory getInstance(){
        if(instance==null){
            instance = new ConnectionFactory();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
    }
   
    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
        Connection con = getConnection();
        return con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
    
  /*
    PreparedStatement.RETURN_GENERATED_KEYS: Rucupera o ID gerado pelo banco após a inserção 
    de um registro
    
    */  
    
    
    
    
    
    
}
