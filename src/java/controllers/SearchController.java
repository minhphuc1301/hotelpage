/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.OrderDetailsDAO;
import daos.RoomDAO;
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
public class SearchController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchController.class);
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "home.jsp";

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

            if (from != null & to != null) {
                fromDate = new Date();
                toDate = new Date();
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                fromDate = sdf.parse(from);
                toDate = sdf.parse(to);
            } else {
                from = (String) request.getAttribute("from");
                to = (String) request.getAttribute("to");
                if (!from.equals("") && !to.equals("")) {
                    fromDate = new Date();
                    toDate = new Date();
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    fromDate = sdf.parse(from);
                    toDate = sdf.parse(to);
                }
            }
            boolean check = false;
            Date date = null;
            if (fromDate != null && toDate != null) {

                LocalDate today = java.time.LocalDate.now();
                date = java.sql.Date.valueOf(today);

                if (fromDate.before(date)) {
                    check = true;
                    request.setAttribute("CHECKDAY", "Checkin date must valid");
                } else if (fromDate.compareTo(toDate) > 0 || fromDate.equals(toDate)) {
                    check = true;
                    request.setAttribute("CHECKDAY", "Checkin date must before checkout date");
                }
            }
            String quantity = request.getParameter("quantity");
            if (quantity == null) {
                quantity = "1";
            }
            int quan = Integer.parseInt(quantity);

            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
            String search3 = request.getParameter("search3");
            if (search3 == null) {
                search3 = "";
            }
            RoomDAO dao2 = new RoomDAO();
            OrderDetailsDAO dao = new OrderDetailsDAO();
            List<RoomDTO> list1 = null;
            RoomDTO dto = null;
            List<RoomDTO> listRoom = dao2.getList();
            if (!check) {
                List<OrderDetailsDTO> list = dao.getList1(from, to);
                RoomDAO dao1 = new RoomDAO();
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < listRoom.size(); i++) {

                        int left = listRoom.get(i).getTotalRoom();
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j).getRoomID().equals(listRoom.get(i).getRoomID())) {
                                left = left - list.get(j).getQuantity();
                                dao2.updateUnitInStock(listRoom.get(i).getRoomID(), left);
                            }
                        }

                    }
                } else {
                    dao1.updateUnitInStock1(3);
                }
//                else {
//                    list = dao.getListIsCheckin(true);
//                    for (int i = 0; i < list.size(); i++) {
//                        dao1.updateUnitInStock(list.get(i).getRoomID(), list.get(i).getAmountRoom());
//                        dao.updateStatusCheckin1(false, list.get(i).getRoomID());
//                    }
//                }
                dao2 = new RoomDAO();
                list1 = dao2.getListWithCondition(quan, search3, search);

                if (listRoom != null && !listRoom.isEmpty()) {

                    for (int i = 0; i < listRoom.size(); i++) {

                        dao1.updateUnitInStock(listRoom.get(i).getRoomID(), listRoom.get(i).getUnitInStock());

                    }
                }

            }
            if (list1 != null && !list1.isEmpty()) {
                request.setAttribute("LIST", list1);
                url = SUCCESS;
            } else {
                request.setAttribute("NOTFOUND", "Nothing here !");
                url = SUCCESS;
            }
            request.setAttribute("FROM", from);
            request.setAttribute("TO", to);

        } catch (Exception e) {
            LOGGER.debug("Error at Search Controller" + e.getMessage());
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
