package com.sbm.ouroffers.Class;

public class Users {
    private String name, phone, password, image, address, active, is_trader;

    public Users() {

    }

    public Users(String name, String phone, String password, String image, String address, String active, String is_trader) {
        this.name=name;
        this.phone=phone;
        this.password=password;
        this.image=image;
        this.address=address;
        this.active=active;
        this.is_trader=is_trader;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone=phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image=image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public String getIs_trader() {
        return is_trader;
    }

    public void setIs_trader(String is_trader) {
        this.is_trader=is_trader;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active=active;
    }
}
