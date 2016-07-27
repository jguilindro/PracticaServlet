/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tributasoft
 */
public class UtilJdbc {
    
    private static String dbURL = "jdbc:derby://localhost:1527/adm";
    
    public static void actualizarUsuario(int tipo_documento, int id_documento, int estado){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        String sql = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //org.apache.derby.jdbc.EmbeddedDriver
            c = DriverManager.getConnection(dbURL);
            c.setAutoCommit(false);
            sql = "UPDATE retencion_desktop SET estado = ? WHERE id =? ;";
            //System.out.println("Rows impacted : " + sql ); 
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, estado);
            preparedStatement.setInt(2, id_documento);
            int rows = preparedStatement.executeUpdate();
            c.commit();
            //System.out.println("Rows impacted : " + rows );            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UtilJdbc.class.getName()).log(Level.SEVERE, null, ex);
            if (c != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    c.rollback();
                } catch(SQLException excep) {
                }
            }
        } finally{
            //finally block used to close resources
            try{
                if(preparedStatement!=null)
                    preparedStatement.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(c!=null)
                    c.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    public static void eliminarUsusario(String cad){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        String sql = null;
        int tipo_documento=1;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            c = DriverManager.getConnection(dbURL);
            c.setAutoCommit(false);
            sql = "DELETE from USUARIO where EMIAL=? ";
            
            //System.out.println("Rows impacted : " + sql ); 
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, cad);         
            int rows = preparedStatement.executeUpdate();
            c.commit();
            //System.out.println("Rows actualizarEstadoDocumentoAutorizado : " + rows );            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UtilJdbc.class.getName()).log(Level.SEVERE, null, ex);
            if (c != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    c.rollback();
                } catch(SQLException excep) {
                }
            }
        } finally{
            //finally block used to close resources
            try{
                if(preparedStatement!=null)
                    preparedStatement.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(c!=null)
                    c.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    public static void ingresarUsusario(String nombre, String email, String rol){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        String sql = null;
        int tipo_documento=1;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            c = DriverManager.getConnection(dbURL);
            c.setAutoCommit(false);
            sql = "insert into USUARIO (USER_NAME,PASSWORD,EMIAL,NOMBRE,ROL) values (?, ?, ?, ?, ?)";
            
            //System.out.println("Rows impacted : " + sql ); 
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, nombre);
            preparedStatement.setString(5, rol);            
            int rows = preparedStatement.executeUpdate();
            c.commit();
            //System.out.println("Rows actualizarEstadoDocumentoAutorizado : " + rows );            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UtilJdbc.class.getName()).log(Level.SEVERE, null, ex);
            if (c != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    c.rollback();
                } catch(SQLException excep) {
                }
            }
        } finally{
            //finally block used to close resources
            try{
                if(preparedStatement!=null)
                    preparedStatement.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(c!=null)
                    c.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
}
