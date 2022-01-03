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
public class HotelDTO {
    private String hotelID,name,area;

    public HotelDTO(String hotelID, String name, String area) {
        this.hotelID = hotelID;
        this.name = name;
        this.area = area;
    }

    public HotelDTO() {
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
}
