/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author 84909
 */
public class OrderDTO {

    public OrderDTO(String orderID, String status, String userID, float total) {
        this.orderID = orderID;
        this.status = status;
        this.userID = userID;
        this.total = total;
    }

    private String orderID, status, userID;
    private float total;
    private Date orderDate;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String status, String userID, float total, Date orderDate) {
        this.orderID = orderID;
        this.status = status;
        this.userID = userID;
        this.total = total;
        this.orderDate = orderDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

}
