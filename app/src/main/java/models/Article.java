package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {

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

    @SerializedName("news_desk")
    private String topic;

    public String thumbnail;
    public String heading;

    //TODO Make ir concise
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

    //TODO Make ir concise
    public String getHeading() {

        if(headline==null){
            return "";
        }

        return headline.getMainHeadline();
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}