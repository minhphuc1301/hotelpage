/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DiscountCodeDAO;
import dtos.DiscountCodeDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author 84909
 */
public class CheckcodeController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CheckcodeController.class);

    private static final String SUCCESS = "checkout.jsp";

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
        String url = SUCCESS;
        try {
            String code = request.getParameter("code");
            DiscountCodeDAO dao = new DiscountCodeDAO();
            DiscountCodeDTO dto = dao.getCode(code);
            if (dto != null) {
                LocalDate date = java.time.LocalDate.now();
                Date today = java.sql.Date.valueOf(date);
                if (today.after(dto.getExpiryDate())) {
                    request.setAttribute("CODE", "Your code is expired");
                } else {
                    request.setAttribute("CODE1", "Your code is valid discount value: "+dto.getDiscountValue()+"%");
                    request.setAttribute("DISCOUNT", dto.getDiscountValue());
                }

            } else {
                request.setAttribute("CODE", "Your code is invalid please check again !");
            }
        } catch (Exception e) {
            LOGGER.debug("Error at CheckcodeController" + e.getMessage());
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

}
