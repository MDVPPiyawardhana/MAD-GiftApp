package com.example.madproject;


public class GiftItems {
    private int itemCode;
    private String itemName;
    private String price;
    private String category;
    private byte[] images;
    private String description;

    public GiftItems(){}

    public GiftItems(String itemName, String price, String category, byte[] images, String description) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.images = images;
        this.description = description;
    }

    public GiftItems(int itemCode, String itemName, String price, String category, byte[] images, String description) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.images = images;
        this.description = description;
    }

    public int getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public byte[] getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
