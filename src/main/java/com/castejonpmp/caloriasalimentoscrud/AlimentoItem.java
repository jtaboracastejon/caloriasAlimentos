/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castejonpmp.caloriasalimentoscrud;


import java.util.ArrayList;


/**
 *
 * @author wwwjr
 */
public class AlimentoItem {
    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return the _producto
     */
    public String getProducto() {
        return _producto;
    }

    /**
     * @param _producto the _producto to set
     */
    public void setProducto(String _producto) {
        this._producto = _producto;
    }

    /**
     * @return the _categoria
     */
    public String getCategoria() {
        return _categoria;
    }

    /**
     * @param _categoria the _categoria to set
     */
    public void setCategoria(String _categoria) {
        this._categoria = _categoria;
    }

    /**
     * @return the _porcion
     */
    public double getPorcion() {
        return _porcion;
    }

    /**
     * @param _porcion the _porcion to set
     */
    public void setPorcion(double _porcion) {
        this._porcion = _porcion;
    }

    /**
     * @return the _caloriasPorPorcion
     */
    public int getCaloriasPorPorcion() {
        return _caloriasPorPorcion;
    }

    /**
     * @param _caloriasPorPorcion the _caloriasPorPorcion to set
     */
    public void setCaloriasPorPorcion(int _caloriasPorPorcion) {
        this._caloriasPorPorcion = _caloriasPorPorcion;
    }
    
    /**
     * @return the _totalCalorias
     */
    public int getTotalCalorias() {
        return _totalCalorias;
    }

    /**
     * @param _totalCalorias the _totalCalorias to set
     */
    public void setTotalCalorias(int _totalCalorias) {
        this._totalCalorias = _totalCalorias;
    }
    
    private int _id;
    private String _producto;
    private String _categoria;
    private double _porcion;
    private int _caloriasPorPorcion;
    private int _totalCalorias;
    
    public AlimentoItem(){
        this._id = 0;
        this._producto = "";
        this._categoria = "";
        this._caloriasPorPorcion = 0;
        this._totalCalorias = 0;
        this._porcion = 0;
    }
    
    public AlimentoItem(int id, String producto, String categoria,  double porcion, int caloriasPorPorcion, int totalCalorias){
        this._id = id;
        this._producto = producto;
        this._categoria = categoria;
        this._porcion = porcion;
        this._caloriasPorPorcion = caloriasPorPorcion;
        this._totalCalorias = totalCalorias;
    }
   //Setter Getters 
    
    public ArrayList<String> obtenerCampos(){
        ArrayList<String> campos = new ArrayList<String>();
        campos.add(String.valueOf(_id));
        campos.add(_producto);
        campos.add(_categoria);
        campos.add(String.valueOf(_porcion));
        campos.add(String.valueOf(_caloriasPorPorcion));
        campos.add(String.valueOf(_totalCalorias));
        
        return campos;
    }
}
