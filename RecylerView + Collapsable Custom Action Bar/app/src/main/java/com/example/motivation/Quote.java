package com.example.motivation;

public class Quote {
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Quote(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
