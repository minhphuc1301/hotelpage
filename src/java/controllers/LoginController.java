/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author 84909
 */
public class LoginController extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "home.jsp";
    private static final String ERROR1 = "login.jsp";

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
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserDAO dao = new UserDAO();
            String username = request.getParameter("username");
            boolean check = dao.checkUser(username);
            if (check) {
                String password = request.getParameter("password");
                String hashCode = getHash(password.getBytes(), "SHA-256");
                UserDTO dto = null;
                dto = dao.checkLogin(username, password, hashCode);
                if (dto != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", dto);
                    url = SUCCESS;
                    
                } else {
                    
                    request.setAttribute("MESSAGE", "Incorrect username or password");
                    url = ERROR1;
                }
            } else {
                request.setAttribute("ERROR", "Please sign up !");
                url = ERROR;
            }
            
        } catch (Exception e) {
            LOGGER.debug("Errort at LoginController" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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

    public String getHash(byte[] input, String algorhtim) {
        String hashValue = "";
        try {
            MessageDigest digest = MessageDigest.getInstance(algorhtim);
            digest.update(input);
            byte[] tmp = digest.digest();
            hashValue = DatatypeConverter.printHexBinary(tmp).toLowerCase();
        } catch (Exception e) {
        }
        return hashValue;
    }
}
