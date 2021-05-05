package com.example.gifts_of_love;

public class GiftItems {
    private int itemCode;
    private String itemName;
    private String price;
    private String category;
    private String images;
    private String description;

    public GiftItems(String itemName, String price, String category, String images, String description) {
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

    public String getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }
}
