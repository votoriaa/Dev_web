/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.victoriamasaro.modelo.dao.entidade;

/**
 *
 * @author victo
 */
public class Fornecedor {
    
    private Integer codFornecedor;
    private double custo;
    
    ItensVenda objItemVenda;

    public Integer getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(Integer codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public ItensVenda getObjItemVenda() {
        return objItemVenda;
    }

    public void setObjItemVenda(ItensVenda objItemVenda) {
        this.objItemVenda = objItemVenda;
    }
    
    
}
