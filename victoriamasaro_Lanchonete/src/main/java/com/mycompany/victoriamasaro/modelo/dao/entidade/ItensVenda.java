/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.victoriamasaro.modelo.dao.entidade;

/**
 *
 * @author 13826640608
 */
public class ItensVenda {
    
    private Integer codItemVenda;
    private double quantVenda;
    
    private Produto objProduto = new Produto();
    private Venda objVenda = new Venda();

    public Integer getCodItemVenda() {
        return codItemVenda;
    }

    public void setCodItemVenda(Integer codItemVenda) {
        this.codItemVenda = codItemVenda;
    }

    public double getQuantVenda() {
        return quantVenda;
    }

    public void setQuantVenda(double quantVenda) {
        this.quantVenda = quantVenda;
    }

    public Produto getObjProduto() {
        return objProduto;
    }

    public void setObjProduto(Produto objProduto) {
        this.objProduto = objProduto;
    }

    public Venda getObjVenda() {
        return objVenda;
    }

    public void setObjVenda(Venda objVenda) {
        this.objVenda = objVenda;
    }
    
}
