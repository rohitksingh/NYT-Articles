package models;

import com.google.gson.annotations.SerializedName;

public class Headline {

    @SerializedName("main")
    private String mainHeadline;

    @SerializedName("print_headline")
    private String printHeadline;


    public String getMainHeadline() {
        return mainHeadline;
    }

    public void setMainHeadline(String mainHeadline) {
        this.mainHeadline = mainHeadline;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }
}
