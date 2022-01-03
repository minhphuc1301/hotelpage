/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dtos.CartDTO;
import dtos.RoomDTO;
import dtos.RoomInCartDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
public class AddToCartController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddToCartController.class);
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchController";

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
        String checkoutDate = null;
        String checkinDate = null;
        try {
            checkinDate = request.getParameter("checkinDate");
            if (checkinDate == null) {
                checkinDate = "";
            }
            checkoutDate = request.getParameter("checkoutDate");
            if (checkoutDate == null) {
                checkoutDate = "";
            }
            if (checkinDate.equals("") && checkoutDate.equals("")) {
                checkinDate = request.getParameter("checkinDate1");
                if (checkinDate == null) {
                    checkinDate = "";
                }
                checkoutDate = request.getParameter("checkoutDate1");
                if (checkoutDate == null) {
                    checkoutDate = "";
                }
                if (checkinDate.equals("") && checkoutDate.equals("")) {
                    LocalDate today = java.time.LocalDate.now();
                    Date date = java.sql.Date.valueOf(today);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(date);
                    c1.roll(Calendar.DATE, true);
                    checkinDate = sdf.format(date);
                    checkoutDate = sdf.format(c1.getTime());
                }

            }
            String roomID = request.getParameter("roomID");
            String hotelName = request.getParameter("hotelName");
            String roomName = request.getParameter("roomName");
            String hotelID = request.getParameter("hotelID");
            String typeRoom = request.getParameter("typeRoom");
            String price = request.getParameter("price");
            String unitInStock = request.getParameter("unitInStock");
            String image = request.getParameter("image");
            float price1 = Float.parseFloat(price);
            int amount = Integer.parseInt(unitInStock);
            int quantity = 1;
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("LIST_CART");
            RoomDTO dto = new RoomDTO(roomID, typeRoom, image, hotelName, checkinDate, checkoutDate, price1, amount, quantity, hotelID);
            RoomInCartDTO dto1 = new RoomInCartDTO(quantity, dto);
            if (cart == null) {
                cart = new CartDTO();
            }

            boolean check = cart.addToCart(dto1);
            if (check) {
                request.setAttribute("from", checkinDate);
                request.setAttribute("to", checkoutDate);
                session.setAttribute("LIST_CART", cart);
                request.setAttribute("ADD", "Add " + roomName + " room success to your cart !");
                url = SUCCESS;
            }

        } catch (Exception e) {
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
