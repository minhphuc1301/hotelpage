/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.OrderDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author 84909
 */
public class OrderDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public String generateOrderID() throws SQLException {
        String o = "OR";
        int num = 0;
        String orderID = o + num;

        for (int i = 0; i < getListOrder().size(); i++) {
            {
                if (orderID.equals(getListOrder().get(i).getOrderID())) {
                    num++;
                    orderID = o + num;
                } else if ((!orderID.equals(getListOrder().get(i).getOrderID()))) {
                    num++;
                    orderID = o + "R" + num;

                } else {
                    num++;
                    orderID = o + "RR" + num;
                }
            }

        }
        return orderID;
    }

    public List<OrderDTO> getListOrder() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> list = new ArrayList();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select orderID,userID,total,status,orderDate From tblOrder ";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String userID = rs.getString("userID");
                    float total = rs.getFloat("total");
                    String status = rs.getString("status");
                    Date date = rs.getDate("orderDate");

                    list.add(new OrderDTO(orderID, userID, status, total, date));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<OrderDTO> getListOrderByUserID(String userID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> list = new ArrayList();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select orderID,total,status,orderDate From tblOrder where userID=? order by orderDate DESC";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");

                    float total = rs.getFloat("total");
                    String status = rs.getString("status");
                    Date date = rs.getDate("orderDate");

                    list.add(new OrderDTO(orderID, status, userID, total, date));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<OrderDTO> getListOrderByOrderIDOrDate(String fromDate, String toDate, String userID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> list = new ArrayList();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select orderID,total,status,orderDate From tblOrder where  userID =? and orderDate>=? and orderDate<=? order by orderDate DESC";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);

                stm.setString(2, fromDate);
                stm.setString(3, toDate);

                rs = stm.executeQuery();
                while (rs.next()) {

                    float total = rs.getFloat("total");
                    String status = rs.getString("status");
                    Date date = rs.getDate("orderDate");

                    list.add(new OrderDTO(rs.getString("orderID"), status, userID, total, date));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<OrderDTO> getListOrderByOrderID(String orderID, String userID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> list = new ArrayList();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select orderID,total,status,orderDate From tblOrder where  orderID=? and userID =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, userID);

                rs = stm.executeQuery();
                while (rs.next()) {

                    float total = rs.getFloat("total");
                    String status = rs.getString("status");
                    Date date = rs.getDate("orderDate");

                    list.add(new OrderDTO(rs.getString("orderID"), status, userID, total, date));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
  public OrderDTO getListOrderByOrderID1(String orderID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO dto = new OrderDTO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select orderID,total,status,orderDate,userID From tblOrder where  orderID=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
             

                rs = stm.executeQuery();
                while (rs.next()) {

                    float total = rs.getFloat("total");
                    String status = rs.getString("status");
                    Date date = rs.getDate("orderDate");

                   dto=new OrderDTO(rs.getString("orderID"), status, rs.getString("userID"), total, date);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
    public boolean insertOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Insert Into tblOrder(orderID,status,userID,orderDate,total) Values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getOrderID());
                stm.setString(2, dto.getStatus());
                stm.setString(3, dto.getUserID());
                long t = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(t);
                stm.setDate(4, date);
                stm.setFloat(5, dto.getTotal());

                int check1 = stm.executeUpdate();
                if (check1 > 0) {
                    check = true;

                }

            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;

    }

    public boolean updateStatus(String status, String orderID) throws Exception {
        String sql = "Update tblOrder Set status=? Where orderID=?";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, orderID);

            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;

    }
}
