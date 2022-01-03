/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.OrderDetailsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author 84909
 */
public class OrderDetailsDAO {

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

    public List<OrderDetailsDTO> getList(String checkin, String checkout) throws Exception {
        List<OrderDetailsDTO> list = new ArrayList<>();
        String sql = "Select quantity,amoutRoom,roomID,checkOrder,checkinDate,checkoutDate From tblOrderDetails Where isCheckin=? and isCheckout=? and ? \n"
                + ">=checkinDate and ? <=checkoutDate or isCheckin=? and isCheckout=? and ? \n"
                + ">=checkinDate and ? <=checkoutDate or isCheckin=? and isCheckout=? and ? \n"
                + "<=checkinDate and ? >=checkinDate and ? \n"
                + "<=checkoutDate or isCheckin=? and isCheckout=? and ? >=checkinDate and ? \n"
                + "<=checkoutDate and ? >=checkoutDate or isCheckin=? and isCheckout=? and ? \n"
                + "<=checkinDate and ? >=checkoutDate";

        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setBoolean(2, false);
            stm.setString(3, checkin);
            stm.setString(4, checkin);
            stm.setBoolean(5, false);
            stm.setBoolean(6, false);
            stm.setString(7, checkout);
            stm.setString(8, checkout);
            stm.setBoolean(9, false);
            stm.setBoolean(10, false);
            stm.setString(11, checkin);
            stm.setString(12, checkout);
            stm.setString(13, checkout);
            stm.setBoolean(14, false);
            stm.setBoolean(15, false);
            stm.setString(16, checkin);
            stm.setString(17, checkin);
            stm.setString(18, checkout);
            stm.setBoolean(19, false);
            stm.setBoolean(20, false);
            stm.setString(21, checkin);
            stm.setString(22, checkout);

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetailsDTO(rs.getString("roomID"), rs.getInt("quantity"), rs.getInt("amoutRoom"), rs.getBoolean("checkOrder"), rs.getDate("checkinDate"), rs.getDate("checkoutDate")));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<OrderDetailsDTO> getList1(String checkin, String checkout) throws Exception {
        List<OrderDetailsDTO> list = new ArrayList<>();
        String sql = "Select quantity,amoutRoom,roomID,checkOrder,checkinDate,checkoutDate From tblOrderDetails Where ? \n"
                + ">=checkinDate and ? <=checkoutDate or ? \n"
                + ">=checkinDate and ? <=checkoutDate or ? \n"
                + "<=checkinDate and ? >=checkinDate and ? \n"
                + "<=checkoutDate or  ? >=checkinDate and ? \n"
                + "<=checkoutDate and ? >=checkoutDate or  ? \n"
                + "<=checkinDate and ? >=checkoutDate";

        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);

            stm.setString(1, checkin);
            stm.setString(2, checkin);

            stm.setString(3, checkout);
            stm.setString(4, checkout);

            stm.setString(5, checkin);
            stm.setString(6, checkout);
            stm.setString(7, checkout);

            stm.setString(8, checkin);
            stm.setString(9, checkin);
            stm.setString(10, checkout);

            stm.setString(11, checkin);
            stm.setString(12, checkout);

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetailsDTO(rs.getString("roomID"), rs.getInt("quantity"), rs.getInt("amoutRoom"), rs.getBoolean("checkOrder"), rs.getDate("checkinDate"), rs.getDate("checkoutDate")));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<OrderDetailsDTO> getListIsCheckin(boolean status) throws Exception {
        List<OrderDetailsDTO> list = new ArrayList<>();
        String sql = "Select quantity,amoutRoom,roomID,isCheckin,isCheckout From tblOrderDetails Where isCheckin = ?";

        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, status);

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetailsDTO(rs.getString("roomID"), rs.getInt("quantity"), rs.getInt("amoutRoom"), rs.getBoolean("isCheckin"), rs.getBoolean("isCheckout")));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<OrderDetailsDTO> getListCheckin(String checkin) throws Exception {
        List<OrderDetailsDTO> list = new ArrayList<>();
        String sql = "Select quantity,amoutRoom,roomID,isCheckin,isCheckout From tblOrderDetails Where checkinDate = ?";

        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, checkin);

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetailsDTO(rs.getString("roomID"), rs.getInt("quantity"), rs.getInt("amoutRoom"), rs.getBoolean("isCheckin"), rs.getBoolean("isCheckout")));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<OrderDetailsDTO> getListCheckout(String checkout) throws Exception {
        List<OrderDetailsDTO> list = new ArrayList<>();
        String sql = "Select quantity,amoutRoom,roomID,isCheckin,isCheckout From tblOrderDetails Where checkoutDate = ?";

        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, checkout);

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetailsDTO(rs.getString("roomID"), rs.getInt("quantity"), rs.getInt("amoutRoom"), rs.getBoolean("isCheckin"), rs.getBoolean("isCheckout")));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean updateStatusCheckin(boolean status, String roomID, String checkin) throws Exception {
        String sql = "Update tblOrderDetails Set isCheckin=? Where roomID=? and checkinDate=?";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setString(2, roomID);
            stm.setString(3, checkin);
            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateStatusCheckin2(boolean status) throws Exception {
        String sql = "Update tblOrderDetails Set isCheckin=?";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, status);

            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateStatusCheckin1(boolean status, String roomID) throws Exception {
        String sql = "Update tblOrderDetails Set isCheckin=? Where roomID=?";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setString(2, roomID);

            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateStatusCheckout(boolean status, String roomID, String checkout) throws Exception {
        String sql = "Update tblOrderDetails Set isCheckout=? Where roomID=? and checkoutDate=?";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setString(2, roomID);
            stm.setString(3, checkout);
            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateStatusCheckOrder(boolean status, String roomID, String checkin, String checkout) throws Exception {
        String sql = "Update tblOrderDetails Set checkOrder=? Where roomID=? and checkinDate=? and checkoutDate=?";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setString(2, roomID);
            stm.setString(3, checkin);
            stm.setString(4, checkout);
            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }
   public boolean updateStatusCheckOrderAll(boolean status) throws Exception {
        String sql = "Update tblOrderDetails Set checkOrder=?";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, status);
        
            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean insert(OrderDetailsDTO dto) throws Exception {
        String sql = "Insert Into tblOrderDetails(orderID,roomID,hotelID,quantity,unitPrice,checkinDate,checkoutDate,amoutRoom,checkOrder) Values(?,?,?,?,?,?,?,?,?)";
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getOrderID());
            stm.setString(2, dto.getRoomID());
            stm.setString(3, dto.getHotelID());
            stm.setInt(4, dto.getQuantity());
            stm.setFloat(5, dto.getUnitPrice());
            stm.setDate(6, dto.getCheckinDate());
            stm.setDate(7, dto.getCheckoutDate());
            stm.setInt(8, dto.getAmountRoom());
            stm.setBoolean(9, false);

            check = stm.executeUpdate() > 0 ? true : false;

        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OrderDetailsDTO> getListByOrderID(String orderID) throws Exception {
        List<OrderDetailsDTO> list = new ArrayList<>();
        String sql = "Select roomID,quantity,unitPrice,checkinDate,checkoutDate,amoutRoom From tblOrderDetails Where orderID=?";

        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, orderID);

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetailsDTO(orderID, rs.getString("roomID"), rs.getInt("amoutRoom"), rs.getFloat("unitPrice"), rs.getDate("checkinDate"), rs.getDate("checkoutDate"), rs.getInt("quantity")));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean deleteOrderDetails(String orderID) throws Exception {
        boolean check = false;
        String sql = "Delete tblOrderDetails where orderID=?";

        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, orderID);

            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OrderDetailsDTO> getList2(String orderID, String today) throws Exception {
        List<OrderDetailsDTO> list = new ArrayList<>();
        String sql = "Select quantity,roomID From tblOrderDetails Where orderID = ? and checkinDate=?";

        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, orderID);
            stm.setString(2, today);

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetailsDTO(rs.getString("roomID"), rs.getInt("quantity")));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
