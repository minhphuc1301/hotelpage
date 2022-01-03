/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.DiscountCodeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtils;

/**
 *
 * @author 84909
 */
public class DiscountCodeDAO {

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

    public DiscountCodeDTO getCode(String code) throws Exception {
        String sql = "Select expiryDate,discountValue From tblDiscountCode Where code=?";
        DiscountCodeDTO dto = null;
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, code);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto = new DiscountCodeDTO(code, rs.getDate("expiryDate"), rs.getInt("discountValue"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
