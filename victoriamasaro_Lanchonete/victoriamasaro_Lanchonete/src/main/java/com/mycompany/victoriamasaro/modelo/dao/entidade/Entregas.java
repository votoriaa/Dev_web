/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.victoriamasaro.modelo.dao.entidade;

/**
 *
 * @author victo
 */
public class Entregas {
    
    private Integer codEntrega;
    private String endereco, numeroCasa;
    
   Cliente objCliente;

    public Integer getCodEntrega() {
        return codEntrega;
    }

    public void setCodEntrega(Integer codFornecedor) {
        this.codEntrega = codFornecedor;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public Cliente getObjCliente() {
        return objCliente;
    }

    public void setObjCliente(Cliente objCliente) {
        this.objCliente = objCliente;
    }
    
    
    
    
}
