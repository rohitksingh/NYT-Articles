package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import networkModels.Headline;
import networkModels.Multimedia;

public class Article {

    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    @SerializedName("abstract")
    private String abstractHeading;
    @SerializedName("lead_paragraph")
    private String leadParagraph;
    @SerializedName("multimedia")
    private List<Multimedia> multimediaList;
    @SerializedName("headline")
    private Headline headline;
    @SerializedName("web_url")
    private String url;

    public String thumbnail;
    public String heading;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public String getThumbnail() {

        if(multimediaList==null || multimediaList.size()==0){
            return "";
        }

        return multimediaList.get(0).getImageUrl();
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeading() {

        if(headline==null){
            return "";
        }

        return headline.getMainHeadline();
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }


    public String getAbstractHeading() {
        return abstractHeading;
    }
    public void setAbstractHeading(String abstractHeading) {
        this.abstractHeading = abstractHeading;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }
    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }
    public List<Multimedia> getMultimediaList() {
        return multimediaList;
    }
    public void setMultimediaList(List<Multimedia> multimediaList) {
        this.multimediaList = multimediaList;
    }
    public Headline getHeadline() {
        return headline;
    }
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }
}
