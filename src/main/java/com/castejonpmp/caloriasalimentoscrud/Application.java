/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castejonpmp.caloriasalimentoscrud;

import com.castejonpmp.utilities.Layout;
import java.util.Scanner;
import java.util.ArrayList;
import com.castejonpmp.dao.CaloriasDB;

/**
 *
 * @author wwwjr
 */
public class Application {
    private Scanner _EntradaTeclado;
    private ArrayList _MisAlimentos;
    private CaloriasDB _CaloriasModel;
    public Application(Scanner EntradaTeclado){
        this._EntradaTeclado = EntradaTeclado;
        this._MisAlimentos = new ArrayList<AlimentoItem>();
        this._CaloriasModel  = new CaloriasDB();
        this._CaloriasModel.TableInitialize();
        this._MisAlimentos = this._CaloriasModel.getAlimentoItems(true);
    }
    
    public void activarEvento(String opcionMenu){
        switch(opcionMenu.toUpperCase()){
            case "M":
                this.mostrarRegistros();
                break;  
            case "I":
                System.out.println("Ingresar Registro");
                this.ingresarNuevoRegistro();
                break;
            case "A":
                System.out.println("Actualizar Registro");
                this.actualizarRegistro();
                break;
            case "E":
                System.out.println("Eliminar Registro");
                this.eliminarRegistro();
                break;
            case "S":
                System.out.println("Saliendo de la aplicacion");
                break;
            default:
                System.out.println("Opcion no reconocida!");
                break;
        }
    }
    
    private void ingresarNuevoRegistro(){
        Layout.printHeader("Nuevo Registro de Producto");
        AlimentoItem nuevoAlimentoItem = new AlimentoItem();
        nuevoAlimentoItem.setProducto(Layout.obtenerValorparaCampo("Nombre del Producto", "Pretzels", this._EntradaTeclado));
        nuevoAlimentoItem.setCategoria(Layout.obtenerValorparaCampo("Categoria", "Snacks", this._EntradaTeclado));
        nuevoAlimentoItem.setPorcion(Double.valueOf(Layout.obtenerValorparaCampo("Porcion (gr)", "27.8", this._EntradaTeclado)));
        nuevoAlimentoItem.setCaloriasPorPorcion(Integer.valueOf(Layout.obtenerValorparaCampo("Calorias por porcion", "110", this._EntradaTeclado)));
        nuevoAlimentoItem.setTotalCalorias(Integer.valueOf(Layout.obtenerValorparaCampo("Calorias del producto", "330", this._EntradaTeclado)));
        
        
        this._CaloriasModel.insertAlimentoItem(nuevoAlimentoItem);
        this._MisAlimentos = this._CaloriasModel.getAlimentoItems(true);
        
        Layout.printSeparator();
        System.out.println(this._MisAlimentos.size());
    }
    
    private void mostrarRegistros(){
        Layout.printSeparator();
        ArrayList<String> columnas = new ArrayList<String>();
        columnas.add("Codigo");
        columnas.add("Producto");
        columnas.add("Categoria");
        columnas.add("Porcion");
        columnas.add("Cal. Por");
        columnas.add("Tot. Cal");
        
        ArrayList<Integer> anchos = new ArrayList<Integer>();
        anchos.add(7);
        anchos.add(23);
        anchos.add(15);
        anchos.add(12);
        anchos.add(9);
        anchos.add(9);
        
        
        
        try{
            //Print header
            Layout.imprimirEnColumna(columnas, anchos,"|");
            Layout.printSeparator();
            for (int i = 0; i < this._MisAlimentos.size(); i++) {
                columnas = ((AlimentoItem) this._MisAlimentos.get(i)).obtenerCampos();
                Layout.imprimirEnColumna(columnas, anchos, "|");
            }
        }catch(Exception ex){
            System.err.println("Error al imprimir" + ex.getMessage());
        }
    }
    
    private void actualizarRegistro(){
        Layout.printHeader("Actualizar Registro");
        Integer selectedId = Integer.valueOf(Layout.obtenerValorparaCampo("Ingrese Codigo de Registro", "0", this._EntradaTeclado));
        AlimentoItem selectedAlimentoItem = null;
        selectedAlimentoItem = this._CaloriasModel.getAlimentoItemById(selectedId);
        if (selectedAlimentoItem == null) {
            System.out.println("Registro solicitado no existe!");
        }else{
            selectedAlimentoItem.setProducto(Layout.obtenerValorparaCampo("Nombre del Producto", selectedAlimentoItem.getProducto(), this._EntradaTeclado));
            selectedAlimentoItem.setCategoria(Layout.obtenerValorparaCampo("Categoria", selectedAlimentoItem.getCategoria(), this._EntradaTeclado));
            selectedAlimentoItem.setPorcion(Double.valueOf(Layout.obtenerValorparaCampo("Porcion (gr)", String.valueOf(selectedAlimentoItem.getPorcion()), this._EntradaTeclado)));
            selectedAlimentoItem.setCaloriasPorPorcion(Integer.valueOf(Layout.obtenerValorparaCampo("Calorias por porcion", String.valueOf(selectedAlimentoItem.getCaloriasPorPorcion()), this._EntradaTeclado)));
            selectedAlimentoItem.setTotalCalorias(Integer.valueOf(Layout.obtenerValorparaCampo("Calorias del producto", String.valueOf(selectedAlimentoItem.getTotalCalorias()), this._EntradaTeclado)));
            
            this._CaloriasModel.updateAlimentoItem(selectedAlimentoItem);
            this._MisAlimentos = this._CaloriasModel.getAlimentoItems(true);
            Layout.printSeparator();
            System.out.println("Registro Actualizado Satisfactoriamente!");
        }

    }
    
    private void eliminarRegistro(){
        Layout.printHeader("Eliminar Registro");
        Integer selectedId = Integer.valueOf(Layout.obtenerValorparaCampo("Ingrese Codigo de Registro", "0", this._EntradaTeclado));
        
        AlimentoItem selectedAlimentoItem = this._CaloriasModel.getAlimentoItemById(selectedId);
        if (selectedAlimentoItem != null) {
            Layout.printSeparator();
            String confirmado = Layout.obtenerValorparaCampo("Desea eliminar este registro? S - N", "N", this._EntradaTeclado);
            if (confirmado.toUpperCase().equals("S")) {
                
                this._CaloriasModel.deleteAlimentoItem(selectedAlimentoItem);
                this._MisAlimentos = this._CaloriasModel.getAlimentoItems(true);
                Layout.printSeparator();
                System.out.println("Registro Eliminado Satisfactoriamente!");
            }
        }else{
            System.out.println("Registro solicitado no existe!");
        }
    }
}
