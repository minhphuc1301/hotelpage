/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.OrderDAO;
import daos.OrderDetailsDAO;
import daos.RoomDAO;
import dtos.CartDTO;
import dtos.OrderDTO;
import dtos.OrderDetailsDTO;
import dtos.RoomDTO;
import dtos.RoomInCartDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author 84909
 */
public class OrderController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(OrderController.class);
    private static final String ERROR = "checkout.jsp";
    private static final String SUCCESS = "EmailSendingControl";

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
            String orderID = request.getParameter("orderID");
            HttpSession session = request.getSession();
            String total = request.getParameter("total");
            UserDTO user = (UserDTO) session.getAttribute("USER");
            Float totalFloat = Float.parseFloat(total);
            CartDTO cart = (CartDTO) session.getAttribute("LIST_CART");
            OrderDTO dto = new OrderDTO(orderID, "In Progress", user.getUserID(), totalFloat);
            OrderDetailsDAO dao = new OrderDetailsDAO();
            RoomDTO dto1 = null;
            RoomDAO roomDAO = new RoomDAO();
            OrderDAO orderDAO = new OrderDAO();
            boolean flag = true;
            if (cart != null) {
                for (RoomInCartDTO room : cart.getCart().values()) {
                    List<OrderDetailsDTO> list = dao.getList1(room.getDto().getCheckinDate(), room.getDto().getCheckoutDate());
                    RoomDAO dao1 = new RoomDAO();
                    if (list != null && !list.isEmpty()) {
                        int left = list.get(0).getAmountRoom();

                        for (int i = 0; i < list.size(); i++) {

                            if (list.get(i).getRoomID().equals(room.getDto().getRoomID())) {
                               left=left-list.get(i).getQuantity();
                                if (list.get(i).isCheckOrder() == false) {

                                  
                                    room.getDto().setUnitInStock(left);
                                    
                                    Date from = list.get(i).getCheckinDate();
                                    Date to = list.get(i).getCheckoutDate();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    String fromDate = sdf.format(from);
                                    String toDate = sdf.format(to);
                                    dao.updateStatusCheckOrder(true, room.getDto().getRoomID(), fromDate, toDate);
                                }
                                dao.updateStatusCheckOrderAll(false);
                            }
                        }
                    }
                    if (room.getQuantity() > room.getDto().getUnitInStock()) {
                        request.setAttribute("ERROR", "The " + room.getDto().getType() + " from " + room.getDto().getCheckinDate() + " to " + room.getDto().getCheckoutDate() + " only has " + room.getDto().getUnitInStock() + " rooms left");
                        dao.updateStatusCheckOrderAll(false);
                        flag = false;
                    }
//                    if (list != null && !list.isEmpty()) {
//
//                        for (int i = 0; i < list.size(); i++) {
//                            dto1 = dao1.getRoom(list.get(i).getRoomID());
//                            int stock = dto1.getUnitInStock();
//                            dao1.updateUnitInStock(list.get(i).getRoomID(), list.get(i).getAmountRoom());
//
//                        }
//                    }
                }
                if (flag) {
                    boolean check1 = orderDAO.insertOrder(dto);
                    for (RoomInCartDTO room : cart.getCart().values()) {
                        cart.getCart().remove(room);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date from = new Date();
                        Date to = new Date();
                        from = sdf.parse(room.getDto().getCheckinDate());
                        to = sdf.parse(room.getDto().getCheckoutDate());
                        java.sql.Date fromDate = new java.sql.Date(from.getTime());
                        java.sql.Date toDate = new java.sql.Date(to.getTime());
                        OrderDetailsDTO orderDetail = new OrderDetailsDTO(orderID, room.getDto().getRoomID(), room.getDto().getHotelID(), room.getQuantity(), 3, room.getDto().getPrice(), fromDate, toDate, false, false);

                        if (check1) {
                            boolean check = dao.insert(orderDetail);
                            if (check) {
                                LocalDate local = java.time.LocalDate.now();
                                Date date = java.sql.Date.valueOf(local);
                                if (fromDate.equals(date)) {
                                    int quan = room.getDto().getUnitInStock() - room.getQuantity();
                                    boolean check2;
                                    check2 = roomDAO.updateUnitInStock(room.getDto().getRoomID(), quan);
                                    if (check2) {
                                        dao.updateStatusCheckOrderAll(false);
                                     
                                        url = SUCCESS;

                                    }
                                } else {
                                    dao.updateStatusCheckOrderAll(false);
                                    
                                    url = SUCCESS;

                                }

                            }
                        }

                    }
                    cart.getCart().clear();

                }
            }
        } catch (Exception e) {
            LOGGER.debug("Errot at OrderController" + e.getMessage());
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
