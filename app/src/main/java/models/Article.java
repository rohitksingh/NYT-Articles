package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import networkModels.Headline;
import networkModels.Multimedia;

@Entity(tableName = "article_history_table")
public class Article {

    /***********************************************************************************************
     *                              Property
     **********************************************************************************************/

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public long id;

    @SerializedName("abstract")
    private String abstractHeading;

    @SerializedName("lead_paragraph")
    private String leadParagraph;

    @SerializedName("multimedia")
    private List<Multimedia> multimediaList;

    @SerializedName("headline")
    private Headline headline;

    @ColumnInfo(name = "web_url")
    @SerializedName("web_url")
    private String url;


    @ColumnInfo(name = "thumbnail_url")
    public String thumbnail;

    @ColumnInfo(name = "headline")
    public String heading;

    /***********************************************************************************************
     *                              Public methods
     **********************************************************************************************/
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
        return (headline==null) ? "" : headline.getMainHeadline();
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

}
