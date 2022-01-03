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
public class OrderDetailsDTO {

    public boolean isCheckOrder() {
        return checkOrder;
    }

    public void setCheckOrder(boolean checkOrder) {
        this.checkOrder = checkOrder;
    }

    public OrderDetailsDTO(String orderID, String roomID, String hotelID, int quantity, int amountRoom, float unitPrice, Date checkinDate, Date checkoutDate, boolean isCheckin, boolean isCheckout) {
        this.orderID = orderID;
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.quantity = quantity;
        this.amountRoom = amountRoom;
        this.unitPrice = unitPrice;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.isCheckin = isCheckin;
        this.isCheckout = isCheckout;
    }

    public OrderDetailsDTO(String orderID, String roomID, int amountRoom, float unitPrice, Date checkinDate, Date checkoutDate,int quantity) {
        this.orderID = orderID;
        this.roomID = roomID;
        this.amountRoom = amountRoom;
        this.unitPrice = unitPrice;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.quantity = quantity;
    }

    private String orderID, roomID, hotelID;
    private int quantity, amountRoom;
    private float unitPrice;
    private Date checkinDate, checkoutDate;
    private boolean isCheckin, isCheckout, checkOrder;

    public OrderDetailsDTO(String roomID, int quantity) {
        this.roomID = roomID;
        this.quantity = quantity;
    }

    public OrderDetailsDTO(String roomID, int quantity, int amountRoom, boolean checkOrder, Date checkinDate, Date checkoutDate) {
        this.roomID = roomID;
        this.quantity = quantity;
        this.amountRoom = amountRoom;
        this.checkOrder = checkOrder;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    public boolean isIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(boolean isCheckin) {
        this.isCheckin = isCheckin;
    }

    public boolean isIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(boolean isCheckout) {
        this.isCheckout = isCheckout;
    }

    public OrderDetailsDTO(String roomID, int quantity, int amountRoom, boolean isCheckin, boolean isCheckout) {
        this.roomID = roomID;
        this.quantity = quantity;
        this.amountRoom = amountRoom;
        this.isCheckin = isCheckin;
        this.isCheckout = isCheckout;
    }

    public int getAmountRoom() {
        return amountRoom;
    }

    public void setAmountRoom(int amountRoom) {
        this.amountRoom = amountRoom;
    }

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderID, String roomID, String hotelID, int quantity, float unitPrice, boolean isCheckin, boolean isCheckout) {
        this.orderID = orderID;
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.isCheckin = isCheckin;
        this.isCheckout = isCheckout;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

}
