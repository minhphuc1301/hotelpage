/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Date;

/**
 *
 * @author 84909
 */
public class DiscountCodeDTO {

    private String code;
    private Date expiryDate;
    private int discountValue;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public DiscountCodeDTO() {
    }

    public DiscountCodeDTO(String code, Date expiryDate, int discountValue) {
        this.code = code;
        this.expiryDate = expiryDate;
        this.discountValue = discountValue;
    }

}
