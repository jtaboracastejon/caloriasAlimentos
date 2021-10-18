/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castejonpmp.caloriasalimentoscrud;

import com.castejonpmp.utilities.Layout;
import java.util.Scanner;

/**
 *
 * @author wwwjr
 */
public class Main {
     public static void main(String[] args){
        Layout.printHeader("Calorias en los Alimentos V1.0");
        String OpcionMenu = "";
        Scanner entradaTeclado = new Scanner(System.in);
        
        Application caloriasApp = new Application(entradaTeclado); 
        
        while(!(OpcionMenu.toUpperCase().equals("S"))){
            Layout.printMenu();
            OpcionMenu = entradaTeclado.nextLine();
            
            caloriasApp.activarEvento(OpcionMenu);
        }
     }
}
