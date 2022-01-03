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
public class UserDTO {

    private String userID, password, fullName, address, phone, gender, hashCode,status;
    private Date createDate;

    public String getGender() {
        return gender;
    }

    public UserDTO(String userID, String password, String fullName, String address, String phone, String gender) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO(String userID, String password, String fullName, String address, String phone, String gender, String hashCode, String status) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.hashCode = hashCode;
        this.status = status;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserDTO(String userID, String password, String fullName, String address, String phone, String gender, String hashCode, Date createDate) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.hashCode = hashCode;
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public UserDTO() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
