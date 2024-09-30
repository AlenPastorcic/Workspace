package com.techelevator.model;

public class MenuItem {

    private int itemId;

    private String title;

    private String description;

    private String imgUrl;

    public MenuItem() {}

    public MenuItem(int itemId, String title, String description, String imgUrl) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
