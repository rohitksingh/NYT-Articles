package models;

import com.google.gson.annotations.SerializedName;


public class Multimedia {

    @SerializedName("subtype")
    private String subType;

    @SerializedName("url")
    private String imageUrl;

    @SerializedName("height")
    private int height;

    @SerializedName("width")
    private int width;

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
