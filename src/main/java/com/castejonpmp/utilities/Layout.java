/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castejonpmp.utilities;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author wwwjr
 */
public class Layout {
    public static void printSeparator () {
        System.out.println("==========================================================================");
    }
    
    public static void printHeader(String header){
        int headerLength = header.length();
        int startPoint = (int) Math.floor((80 - headerLength)/2);
        String headerBuffer = "";
        for(int i = 0; i<startPoint; i++){
            headerBuffer += " ";
        }
        headerBuffer += header;
        startPoint = headerBuffer.length();
        for(int i = startPoint; i<80; i++){
            headerBuffer += " ";
        }
        
        printSeparator();
        System.out.println(headerBuffer);
        printSeparator();
    }
    
    public static void printMenu(){
        printHeader("Menu");
        System.out.println("\nM - Mostrar\nI - Ingresar\nA - Actualizar\nE - Eliminar\n\nS - Salir\n");
        printSeparator();
        System.out.println("Seleccione una opcion:");
    }
    
    public static String obtenerValorparaCampo(String Etiqueta, String ValorPredeterminado, Scanner entradaTeclado){
        System.out.println(Etiqueta + " ("+ ValorPredeterminado + ") :");
        String valor = entradaTeclado.nextLine();
        if(valor.isEmpty()){
            return ValorPredeterminado;
        }
        return valor;
    }
    
    public static void imprimirEnColumna(ArrayList<String> columnas, ArrayList<Integer> anchos, String  separator) throws Exception{
        if(columnas.size() != anchos.size()){
            throw new Exception();
        }
        System.err.print(separator);
        for (int i = 0; i < columnas.size(); i++) {
            String columna = String.format("%1$-"+String.valueOf(anchos.get(i))+"s", columnas.get(i));
            columna = columna.substring(0, anchos.get(i)-1);
            
            System.out.print(columna);
            System.out.print(separator);
        }
        
        System.out.println();
    }
}
