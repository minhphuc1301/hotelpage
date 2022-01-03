/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author 84909
 */
public class RoomDTO {

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    private String roomID, name, type, imageUrl, hotelName, areaName, hotelUrl, checkinDate, checkoutDate, hotelID;
    private float price;
    private int unitInStock, quantity,totalRoom;

    public RoomDTO(String roomID, String type, String imageUrl, String hotelName, String checkinDate, String checkoutDate, float price, int unitInStock, int quantity, String hotelID) {
        this.roomID = roomID;
        this.type = type;
        this.imageUrl = imageUrl;
        this.hotelName = hotelName;

        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.price = price;
        this.unitInStock = unitInStock;
        this.quantity = quantity;
        this.hotelID = hotelID;
    }

    public RoomDTO(String roomID, String name, String type, String imageUrl, String hotelName, String areaName, String hotelUrl, String checkinDate, String checkoutDate, String hotelID, float price, int unitInStock, int quantity, int totalRoom) {
        this.roomID = roomID;
        this.name = name;
        this.type = type;
        this.imageUrl = imageUrl;
        this.hotelName = hotelName;
        this.areaName = areaName;
        this.hotelUrl = hotelUrl;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.hotelID = hotelID;
        this.price = price;
        this.unitInStock = unitInStock;
        this.quantity = quantity;
        this.totalRoom = totalRoom;
    }

    public int getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(int totalRoom) {
        this.totalRoom = totalRoom;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public RoomDTO(String roomID, String name, String type, String imageUrl, String hotelName, String areaName, float price, int unitInStock, String hotelUrl, String hotelID,int totalRoom) {
        this.roomID = roomID;
        this.name = name;
        this.type = type;
        this.imageUrl = imageUrl;
        this.hotelName = hotelName;
        this.areaName = areaName;
        this.price = price;
        this.unitInStock = unitInStock;
        this.hotelUrl = hotelUrl;
        this.hotelID = hotelID;
         this.totalRoom = totalRoom;
    }

    public RoomDTO(String roomID, String name, String type, String imageUrl, String hotelName, String areaName, float price, int unitInStock, String hotelUrl) {
        this.roomID = roomID;
        this.name = name;
        this.type = type;
        this.imageUrl = imageUrl;
        this.hotelName = hotelName;
        this.areaName = areaName;
        this.price = price;
        this.unitInStock = unitInStock;
        this.hotelUrl = hotelUrl;

    }

    public String getHotelUrl() {
        return hotelUrl;
    }

    public void setHotelUrl(String hotelUrl) {
        this.hotelUrl = hotelUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    public RoomDTO(String roomID, String name, String type, float price, int unitInStock, int quantity) {
        this.roomID = roomID;
        this.name = name;
        this.type = type;
        this.price = price;
        this.unitInStock = unitInStock;
        this.quantity = quantity;
    }

    public RoomDTO() {
    }

    public RoomDTO(String roomID, String name, String type, float price, int quantity) {
        this.roomID = roomID;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
