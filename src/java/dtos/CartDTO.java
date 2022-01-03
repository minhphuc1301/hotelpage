/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 84909
 */
public class CartDTO {

    private Map<String, RoomInCartDTO> cart;

    public CartDTO() {
    }

    public CartDTO(Map<String, RoomInCartDTO> cart) {
        this.cart = cart;
    }

    public Map<String, RoomInCartDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, RoomInCartDTO> cart) {
        this.cart = cart;
    }

    public boolean addToCart(RoomInCartDTO dto) {
        boolean check = false;
        try {
            if (cart == null) {
                cart = new HashMap<>();
            }
            if (cart.containsKey(dto.getDto().getRoomID())) {
                int quantity = cart.get(dto.getDto().getRoomID()).getQuantity();
                dto.setQuantity(quantity + dto.getDto().getQuantity());
            }
            cart.put(dto.getDto().getRoomID(), dto);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

    public boolean delete(String roomID) {
        boolean check = false;
        try {
            if (cart != null) {
                if (cart.containsKey(roomID)) {
                    cart.remove(roomID);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
    public boolean update(RoomInCartDTO dto,String roomID)
    {
        boolean check=false;
        try {
            if(cart!=null)
            {
                if(cart.containsKey(roomID))
                {
                    cart.replace(roomID, dto);
                    check=true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
}
