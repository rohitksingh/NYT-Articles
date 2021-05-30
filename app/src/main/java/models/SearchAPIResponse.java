package models;

import com.google.gson.annotations.SerializedName;

public class SearchAPIResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("response")
    private ArticleResponse articleResponse;

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
