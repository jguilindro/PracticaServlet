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
    
    public static void anulacionDocumentoElectronico(String numero_autorizacion, int id_documento
            , String proveedor, String num_estb_pto, String secuencia, String Clave_acceso
            , Date fecha_emision,String file){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        String sql = null;
        int tipo_documento=1;
        try {
            Class.forName("org.postgresql.Driver");
            //c = DriverManager.getConnection(conexion,user, clave);
            c = DriverManager.getConnection(dbURL);
            c.setAutoCommit(false);
            switch(tipo_documento){
                case 1:
                    sql = "INSERT INTO public.anulacion_comprobante_electronico(\n" +
                        "            numero_autorizacion, factura_id, \n" +
                        "            proveedor, numero_estab_punt_emision, secuencia, \n" +
                        "            clave_acceso, fecha_anulacion, fecha_creacion, fecha_emision, \n" +
                        "            motivo, tipo, status, file)\n" +
                        "    VALUES (?, ?, \n" +
                        "            ?, ?, ?, \n" +
                        "            ?, ?, ?, ?, \n" +
                        "            ?, ?, ?, ?);";
                    break;
                case 4:
                    sql = "UPDATE nota_credito_desktop SET status_send_email = ? WHERE id =? ;";
                    break;
                case 6:
                    sql = "UPDATE guia_remision_desktop SET status_send_email = ? WHERE id =? ;";
                    break;
                case 7:
                    sql = "UPDATE retencion_desktop SET status_send_email = ? WHERE id =? ;";
                    break;
                default:
                    //sql = "UPDATE pedido SET status_send_email = ? WHERE id =? ;";
                    break;
            }
            //System.out.println("Rows impacted : " + sql ); 
            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, numero_autorizacion);
            preparedStatement.setInt(2, id_documento);
            preparedStatement.setString(3, proveedor);
            preparedStatement.setString(4, num_estb_pto);
            preparedStatement.setString(5, secuencia);
            preparedStatement.setString(6, Clave_acceso);
            preparedStatement.setDate(7, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.setDate(8, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.setDate(9, fecha_emision);
            preparedStatement.setString(10, "ERROR DATOS DOCUMENTOS");
            preparedStatement.setInt(11, tipo_documento);
            preparedStatement.setInt(12, 0);
            preparedStatement.setString(13, file);
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
