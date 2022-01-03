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
public class RoomInCartDTO {
    private int quantity;
    private RoomDTO dto;

    public RoomInCartDTO() {
    }

    public RoomInCartDTO(int quantity, RoomDTO dto) {
        this.quantity = quantity;
        this.dto = dto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public RoomDTO getDto() {
        return dto;
    }

    public void setDto(RoomDTO dto) {
        this.dto = dto;
    }
    
}
