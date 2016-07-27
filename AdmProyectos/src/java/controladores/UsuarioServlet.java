/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import base.datos.UtilJdbc;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author estudiante.2016
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String inputNombre = request.getParameter("inputNombre");
        String inputEmail = request.getParameter("inputEmail");
        String selectRol = request.getParameter("selectRol");
        
        response.setContentType("application/json");
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        
        if(name.equals("usuario")){
        //Ingreso de usuario a la base de datos.
            object.addProperty("Nombre", inputNombre);
            object.addProperty("Email", inputEmail);
            object.addProperty("Rol", selectRol);
            object.addProperty("Estado", name);
            System.out.println("aqui estoy --");
            UtilJdbc.ingresarUsusario(inputNombre, inputEmail, selectRol);
        }
        if(name.equals("edit")){
        //Ingreso de usuario a la base de datos.
            object.addProperty("Nombre", inputNombre);
            object.addProperty("Email", inputEmail);
            object.addProperty("Rol", selectRol);
            object.addProperty("Estado", name);
        }
        if(name.equals("del")){
        //Ingreso de usuario a la base de datos.
            object.addProperty("Nombre", inputNombre);
            object.addProperty("Email", inputEmail);
            object.addProperty("Rol", selectRol);
            object.addProperty("Estado", name);
            UtilJdbc.eliminarUsusario(inputEmail);
        }
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(object));
        out.flush();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
