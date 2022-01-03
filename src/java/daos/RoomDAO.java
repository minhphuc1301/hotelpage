/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.OrderDetailsDTO;
import dtos.RoomDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author 84909
 */
public class RoomDAO {

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

    public List<RoomDTO> getList() throws Exception {
        List<RoomDTO> list = new ArrayList<>();
        String sql = "Select L.roomID,L.roomName,L.unitInStock,L.imageUrl,P.typeName,P.price,H.hotelName,"
                + "H.hotelUrl,A.areaName,H.hotelID,L.totalRoom from tblRoom L inner join tblRoomType P on L.typeID=P.typeID "
                + "inner join tblHotel H on L.hotelID=H.hotelID inner join tblArea A on H.areaID=A.areaID"
                + " Where unitInStock>0";

        try {
//            OrderDetailsDAO dao = new OrderDetailsDAO();
//            LocalDate today = java.time.LocalDate.now();
//            Date date = java.sql.Date.valueOf(today);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String from = sdf.format(date);
//            List<OrderDetailsDTO> list1 = dao.getListCheckin(from);
//            RoomDAO dao1 = new RoomDAO();
//            List<OrderDetailsDTO> list2 = dao.getListCheckout(from);
//            RoomDTO dto = null;
//            if (list1 != null && !list1.isEmpty()) {
//                
//                for (int i = 0; i < list1.size(); i++) {
//                    if (!list1.get(i).isIsCheckin()) {
//                        dto = dao1.getRoom(list1.get(i).getRoomID());
//                        int stock = dto.getUnitInStock();
//                        dao1.updateUnitInStock(list1.get(i).getRoomID(), stock - list1.get(i).getQuantity());
//                        dao.updateStatusCheckin(true, list1.get(i).getRoomID(), from);
//                    }
//                }
//            }
//            if (list2 != null && !list2.isEmpty()) {
//                
//                for (int i = 0; i < list2.size(); i++) {
//                    if (!list2.get(i).isIsCheckout()) {
//                        dto = dao1.getRoom(list2.get(i).getRoomID());
//                        int stock = dto.getUnitInStock();
//                        dao1.updateUnitInStock(list2.get(i).getRoomID(), stock + list2.get(i).getQuantity());
//                        dao.updateStatusCheckout(true, list2.get(i).getRoomID(), from);
//                    }
//                }
//            }
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new RoomDTO(rs.getString("roomID"), rs.getString("roomName"), rs.getString("typeName"), rs.getString("imageUrl"), rs.getString("hotelName"), rs.getString("areaName"), rs.getFloat("price"), rs.getInt("unitInStock"), rs.getString("hotelUrl"), rs.getString("hotelID"),rs.getInt("totalRoom")));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public RoomDTO getRoomByRoomID(String roomID) throws Exception {
        RoomDTO dto = null;
        String sql = "Select L.roomID,L.roomName,L.unitInStock,L.imageUrl,P.typeName,P.price,H.hotelName,"
                + "H.hotelUrl,A.areaName,H.hotelID,L.totalRoom from tblRoom L inner join tblRoomType P on L.typeID=P.typeID "
                + "inner join tblHotel H on L.hotelID=H.hotelID inner join tblArea A on H.areaID=A.areaID"
                + " Where roomID=? ";

        try {
//
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, roomID);
            rs = stm.executeQuery();

            while (rs.next()) {
                dto = new RoomDTO(rs.getString("roomID"), rs.getString("roomName"), rs.getString("typeName"), rs.getString("imageUrl"), rs.getString("hotelName"), rs.getString("areaName"), rs.getFloat("price"), rs.getInt("unitInStock"), rs.getString("hotelUrl"), rs.getString("hotelID"),rs.getInt("totalRoom"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean updateUnitInStock(String roomID, int quantity) throws Exception {
        String sql = "Update tblRoom Set unitInStock=? Where roomID=?";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setString(2, roomID);
            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateUnitInStock1(int quantity) throws Exception {
        String sql = "Update tblRoom Set unitInStock=? ";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, quantity);

            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateUnitInStock1(String roomID, int quantity) throws Exception {
        String sql = "Update tblRoom Set unitInStock=? Where roomID!=?";

        boolean check = false;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setString(2, roomID);
            check = stm.executeUpdate() > 0 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<RoomDTO> getListWithCondition(int quantity, String areaName, String hotelName) throws Exception {
        List<RoomDTO> list = new ArrayList<>();
        String sql = "Select L.roomID,L.roomName,L.unitInStock,L.imageUrl,P.typeName,P.price,H.hotelName,"
                + "H.hotelUrl,A.areaName,H.hotelID,L.totalRoom from tblRoom L inner join tblRoomType P on L.typeID=P.typeID "
                + "inner join tblHotel H on L.hotelID=H.hotelID inner join tblArea A on H.areaID=A.areaID"
                + " Where unitInStock>=? and H.hotelName like ? and A.areaName like ?";

        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setString(2, "%" + hotelName + "%");
            stm.setString(3, "%" + areaName + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RoomDTO(rs.getString("roomID"), rs.getString("roomName"), rs.getString("typeName"), rs.getString("imageUrl"), rs.getString("hotelName"), rs.getString("areaName"), rs.getFloat("price"), rs.getInt("unitInStock"), rs.getString("hotelUrl"), rs.getString("hotelID"),rs.getInt("totalRoom")));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public RoomDTO getRoom(String roomID) throws Exception {
        RoomDTO dto = new RoomDTO();
        String sql = "Select L.roomID,L.roomName,L.unitInStock,L.imageUrl,P.typeName,P.price,H.hotelName,"
                + "H.hotelUrl,A.areaName from tblRoom L inner join tblRoomType P on L.typeID=P.typeID "
                + "inner join tblHotel H on L.hotelID=H.hotelID inner join tblArea A on H.areaID=A.areaID"
                + " Where unitInStock>0 and roomID=?";

        try {

            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, roomID);
            rs = stm.executeQuery();

            if (rs.next()) {
                dto = new RoomDTO(rs.getString("roomID"), rs.getString("roomName"), rs.getString("typeName"), rs.getString("imageUrl"), rs.getString("hotelName"), rs.getString("areaName"), rs.getFloat("price"), rs.getInt("unitInStock"), rs.getString("hotelUrl"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

}
