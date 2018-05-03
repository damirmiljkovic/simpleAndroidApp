package net.idevcorp.simpleandroidapp.models.Answers;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnswerModel {

    private List<ItemModel> items;
    @SerializedName("has_more")
    private Boolean hasMore;
    @SerializedName("backoff")
    private Long backoff;
    @SerializedName("quota_max")
    private Long quotaMax;
    @SerializedName("quota_remaining")
    private Long quotaRemaining;

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Long getBackoff() {
        return backoff;
    }

    public void setBackoff(Long backoff) {
        this.backoff = backoff;
    }

    public Long getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(Long quotaMax) {
        this.quotaMax = quotaMax;
    }

    public Long getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(Long quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

//     "items":[  ],
//             "has_more":true,
//             "backoff":10,
//             "quota_max":300,
//             "quota_remaining":298

}
