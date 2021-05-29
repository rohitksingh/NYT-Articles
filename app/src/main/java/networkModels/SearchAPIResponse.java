package networkModels;

import com.google.gson.annotations.SerializedName;

public class SearchAPIResponse {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    @SerializedName("status")
    private String status;
    @SerializedName("response")
    private ArticleResponse articleResponse;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ArticleResponse getArticleResponse() {
        return articleResponse;
    }
    public void setArticleResponse(ArticleResponse articleResponse) {
        this.articleResponse = articleResponse;
    }
}
