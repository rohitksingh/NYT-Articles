package networkModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {

    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    @SerializedName("abstract")
    private String abstractHeading;
    @SerializedName("web_url")
    private String webUrl;
    @SerializedName("lead_paragraph")
    private String leadParagraph;
    @SerializedName("multimedia")
    private List<Multimedia> multimediaList;
    @SerializedName("headline")
    private Headline headline;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public String getAbstractHeading() {
        return abstractHeading;
    }
    public void setAbstractHeading(String abstractHeading) {
        this.abstractHeading = abstractHeading;
    }
    public String getWebUrl() {
        return webUrl;
    }
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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
