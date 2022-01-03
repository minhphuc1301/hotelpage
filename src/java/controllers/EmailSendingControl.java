/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.OrderDAO;
import dtos.OrderDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import utils.SendMail;

/**
 *
 * @author 84909
 */
public class EmailSendingControl extends HttpServlet {

    private static final String ERROR = "error.jsp";

    private static final String SUCCESS = "listOrder.jsp";
    private String host;
    private String port;
    private String userID;
    private String pass;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        userID = context.getInitParameter("user");
        pass = context.getInitParameter("password");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        System.out.println(userID + pass);
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("USER");
        String recipient = user.getUserID();
        String subject = "Confirm order by MP Booking Page";
        OrderDAO dao = new OrderDAO();
        String orderID = request.getParameter("orderID");
       OrderDTO dto=null;
        try {
           dto = dao.getListOrderByOrderID1(orderID);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(EmailSendingControl.class.getName()).log(Level.SEVERE, null, ex);
        }
//        OrderDTO order = (OrderDTO) session.getAttribute("ORDER_INFOR");
        String content = "Hello \b" + user.getFullName() + ", I'm admin at Booking Hotel Page.\n"
                + "I want to say thanks for your ordering!\nYour orderID: "
                + dto.getOrderID()+ "\nYour order date: " + dto.getOrderDate()
                + "\nYour Total Price: $" + dto.getTotal();
//                + order.getOrderId() + "\nYour order date: " + order.getDate()
//                + "\nYour Total Price: $" + order.getTotalPrice();

        try {

            SendMail.send(host, port, recipient, subject,
                    content, userID, pass);
            url = SUCCESS;
            request.setAttribute("SUCCESS", "Order Success !");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
