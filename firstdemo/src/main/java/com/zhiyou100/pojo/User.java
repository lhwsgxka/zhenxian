package com.zhiyou100.pojo;

public class User {
    private Integer id;

    private String userName;

    private String password;

    private Integer cellPhone;

    private String size;

    private String idNumbei;

    private Double money;

    private Double earnings;

    private Double topUp;

    private String pictureUrl;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Integer cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getIdNumbei() {
        return idNumbei;
    }

    public void setIdNumbei(String idNumbei) {
        this.idNumbei = idNumbei == null ? null : idNumbei.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public Double getTopUp() {
        return topUp;
    }

    public void setTopUp(Double topUp) {
        this.topUp = topUp;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}