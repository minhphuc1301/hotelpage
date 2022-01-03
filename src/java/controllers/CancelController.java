/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.OrderDAO;
import daos.OrderDetailsDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.OrderDetailsDTO;
import dtos.RoomDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
public class CancelController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CancelController.class);

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
        try {
            String orderID = request.getParameter("orderID");
            OrderDetailsDAO orderDetail = new OrderDetailsDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate todate = java.time.LocalDate.now();
            Date today = java.sql.Date.valueOf(todate);
            String toDAY = sdf.format(today);
            RoomDAO roomDao = new RoomDAO();
            List<RoomDTO> listRoom = roomDao.getList();
            List<OrderDetailsDTO> list = orderDetail.getList2(orderID, toDAY);
            boolean check = orderDetail.deleteOrderDetails(orderID);
            if (check) {
                for (int i = 0; i < listRoom.size(); i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getRoomID().equals(listRoom.get(i).getRoomID())) {
                            boolean check3 = roomDao.updateUnitInStock(list.get(j).getRoomID(), list.get(j).getQuantity() + listRoom.get(i).getUnitInStock());
                            if (check3) {
                                OrderDAO dao = new OrderDAO();
                                boolean check1 = dao.updateStatus("Cancel", orderID);
                                if (check1) {
                                    request.setAttribute("MESS", "Cancel the booking success !");
                                    url = SUCCESS;
                                }
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            LOGGER.debug("Errort at CancelController" + e.getMessage());
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
