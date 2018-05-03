package net.idevcorp.simpleandroidapp.models.Users;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {
    private List<UserItemModel> items;
    @SerializedName("has_more")
    private Boolean hasMore;
    @SerializedName("quota_max")
    private Long quotaMax;
    @SerializedName("quota_remaining")
    private Long quotaRemaining;

    public List<UserItemModel> getUserItemModel() {
        return items;
    }

    public void setUserItemModel(List<UserItemModel> userItemModel) {
        this.items = userItemModel;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
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
}
//{
//        "items": [...
//
//        ],
//        "has_more": false,
//        "quota_max": 10000,
//        "quota_remaining": 9977
//        }