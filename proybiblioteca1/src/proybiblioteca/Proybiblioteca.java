/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proybiblioteca;

import forms.login;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author ronychoche
 */
public class Proybiblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
         login objeto= new login();
        objeto.setVisible(true);  
       
    }
    
}
