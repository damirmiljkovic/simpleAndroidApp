package net.idevcorp.simpleandroidapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionModel {

    @SerializedName("items")
    private List<ItemQuestionModel> items;
    @SerializedName("has_more")
    private Boolean hasMore;
    @SerializedName("quota_max")
    private Integer quotaMax;
    @SerializedName("quota_remaining")
    private Integer quotaRemaining;

    public List<ItemQuestionModel> getItemQuestion() {
        return items;
    }

    public void setItemQuestion(List<ItemQuestionModel> items) {
        this.items = items;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(Integer quotaMax) {
        this.quotaMax = quotaMax;
    }

    public Integer getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(Integer quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

    //              "has_more": true,
//             "quota_max": 300,
//             "quota_remaining": 291
}
