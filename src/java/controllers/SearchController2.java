/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.OrderDAO;
import daos.OrderDetailsDAO;
import daos.RoomDAO;
import dtos.OrderDTO;
import dtos.OrderDetailsDTO;
import dtos.RoomDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author 84909
 */
public class SearchController2 extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchController2.class);
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "listOrder.jsp";

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
        String from = null;
        String to = null;
        Date fromDate = null;
        Date toDate = null;
        SimpleDateFormat sdf = null;
        try {
            from = request.getParameter("from");
            to = request.getParameter("to");

            if (from != null && to != null && !from.equals("")) {
                fromDate = new Date();
                toDate = new Date();
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                fromDate = sdf.parse(from);
                toDate = sdf.parse(to);
            }
            boolean check = false;
            if (fromDate != null && toDate != null) {

                LocalDate today = java.time.LocalDate.now();
                Date date = java.sql.Date.valueOf(today);

                if (fromDate.before(date)) {
                    check = true;
                    request.setAttribute("CHECKDAY", "Checkin date must valid");
                } else if (fromDate.compareTo(toDate) > 0 || fromDate.equals(toDate)) {
                    check = true;
                    request.setAttribute("CHECKDAY", "Checkin date must before checkout date");
                }
            }

            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
            OrderDAO dao = new OrderDAO();

            if (check != true) {
                List<OrderDTO> list = null;
                if (!from.equals("") && !to.equals("")) {
                    list = dao.getListOrderByOrderIDOrDate(from, to, request.getParameter("userID"));
                } else {
                    list = dao.getListOrderByOrderID(search, request.getParameter("userID"));

                }
                if (list.size() > 0) {
                    url = SUCCESS;
                    request.setAttribute("LIST_ORDER", list);
                } else {
                    request.setAttribute("MESS3", "Nothing here !");
                    url = SUCCESS;
                }

            }

        } catch (Exception e) {
            LOGGER.debug("Error at Search Controller2" + e.getMessage());
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
