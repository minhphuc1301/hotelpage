/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author 84909
 */
public class UserDAO {

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

    public boolean insertUser(UserDTO dto) throws Exception {
        String url = "Insert Into tblUser(userID,password,fullName,address,phone,gender,createDate,hashCode,status) Values(?,?,?,?,?,?,?,?,?)";
        boolean check;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(url);
            stm.setString(1, dto.getUserID());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getFullName());
            stm.setString(4, dto.getAddress());
            stm.setString(5, dto.getPhone());
            stm.setString(6, dto.getGender());
            long t = System.currentTimeMillis();
            java.sql.Date now = new java.sql.Date(t);
            stm.setDate(7, now);
            stm.setString(8, dto.getHashCode());
            stm.setString(9, dto.getStatus());
            check = stm.executeUpdate() > 0 ? true : false;

        } finally {
            closeConnection();
        }
        return check;
    }

    public UserDTO checkLogin(String username, String password, String hashCode) throws Exception {
        String url = "Select userID,fullName,address,phone,gender From tblUser Where userID=? and password=? and hashCode=?";
        UserDTO dto = null;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(url);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, hashCode);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto = new UserDTO(username, password, rs.getString("fullName"), rs.getString("address"), rs.getString("phone"), rs.getString("gender"));
            }
        } finally {
            closeConnection();
        }
        return dto;

    }

    public boolean checkUser(String username) throws Exception {
        boolean check = true;
        String url = "Select userID From tblUser Where userID=?";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(url);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
