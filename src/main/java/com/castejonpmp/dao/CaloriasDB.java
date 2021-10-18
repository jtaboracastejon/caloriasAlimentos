/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castejonpmp.dao;

import com.castejonpmp.caloriasalimentoscrud.AlimentoItem;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


/**
 *
 * @author wwwjr
 */
public class CaloriasDB {
    private ArrayList<AlimentoItem> _AlimentosItems = null;
    
    public CaloriasDB(){
        this._AlimentosItems = new ArrayList<AlimentoItem>();
    }
    
    public ArrayList<AlimentoItem> getAlimentoItems (){
       return this.getAlimentoItems(false);
    }
    
    public void TableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS CALORIAS"
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " Producto TEXT NOT NULL,"
                + " Categoria TEXT NOT NULL,"
                + " Porcion REAL NOT NULL,"
                + " CaloriasPorPorcion INTEGER NOT NULL,"
                + " TotalCalorias INTEGER NOT NULL"
                + ")";
        
        try {
            Statement comando = Conn.getConn().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<AlimentoItem> getAlimentoItems (boolean forceLoad){
        try {
            if (forceLoad) {
                Statement comando = Conn.getConn().createStatement();
                ResultSet misRegistros = comando.executeQuery("SELECT * FROM CALORIAS;");
                this._AlimentosItems.clear();
                while (misRegistros.next()){
                    AlimentoItem registro = new AlimentoItem();
                    registro.setId(misRegistros.getInt("ID"));
                    registro.setProducto(misRegistros.getString("Producto"));
                    registro.setCategoria(misRegistros.getString("Categoria"));
                    registro.setPorcion(misRegistros.getDouble("Porcion"));
                    registro.setCaloriasPorPorcion(misRegistros.getInt("CaloriasPorPorcion"));
                    registro.setTotalCalorias(misRegistros.getInt("TotalCalorias"));
                    this._AlimentosItems.add(registro);
                }
            }
            
            return this._AlimentosItems;
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return this._AlimentosItems;
        }
    }
    
    public AlimentoItem getAlimentoItemById (int id){
        try {
            String SQLGetById = "SELECT * FROM CALORIAS WHERE ID=?;";
            PreparedStatement comando = Conn.getConn().prepareStatement(SQLGetById);
            comando.setInt(1, id);
            ResultSet misRegistros = comando.executeQuery();
            AlimentoItem registro = new AlimentoItem();
            
            while (misRegistros.next()){
                registro.setId(misRegistros.getInt("ID"));
                registro.setProducto(misRegistros.getString("Producto"));
                registro.setCategoria(misRegistros.getString("Categoria"));
                registro.setPorcion(misRegistros.getDouble("Porcion"));
                registro.setCaloriasPorPorcion(misRegistros.getInt("CaloriasPorPorcion"));
                registro.setTotalCalorias(misRegistros.getInt("TotalCalorias"));
                break;
            }
            comando.close();
            
            return registro;
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public int updateAlimentoItem(AlimentoItem itemToUpdate){
        try {
            String SQLUpdate = "UPDATE CALORIAS set Producto=?, Categoria=?, Porcion=?, CaloriasPorPorcion=?, TotalCalorias=? where ID=?;";
            PreparedStatement comando = Conn.getConn().prepareStatement(SQLUpdate);
            
            comando.setString(1, itemToUpdate.getProducto());
            comando.setString(2, itemToUpdate.getCategoria());
            comando.setDouble(3, itemToUpdate.getPorcion());
            comando.setInt(4, itemToUpdate.getCaloriasPorPorcion());
            comando.setInt(5, itemToUpdate.getTotalCalorias());
            comando.setInt(6, itemToUpdate.getId());
            
            int registroAfectado = comando.executeUpdate();
            comando.close();
            return registroAfectado;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    
    
    public int insertAlimentoItem(AlimentoItem itemToInsert){
        try {
            String SQLInsert = "INSERT INTO CALORIAS (Producto, Categoria, Porcion, CaloriasPorPorcion, TotalCalorias) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement comando = Conn.getConn().prepareStatement(SQLInsert);
            
            comando.setString(1, itemToInsert.getProducto());
            comando.setString(2, itemToInsert.getCategoria());
            comando.setDouble(3, itemToInsert.getPorcion());
            comando.setInt(4, itemToInsert.getCaloriasPorPorcion());
            comando.setInt(5, itemToInsert.getTotalCalorias());
            
            
            int registroAfectado = comando.executeUpdate();
            comando.close();
            return registroAfectado;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    public int deleteAlimentoItem(AlimentoItem itemToDelete){
        try {
            String SQLDelete = "DELETE FROM CALORIAS WHERE ID = ?;";
            PreparedStatement comando = Conn.getConn().prepareStatement(SQLDelete);
            
            comando.setInt(1, itemToDelete.getId());
            
            int registroAfectado = comando.executeUpdate();
            comando.close();
            return registroAfectado;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
}
