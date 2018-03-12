package net.idevcorp.simpleandroidapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnswerModel {

    private List<ItemModel> items;
    @SerializedName("has_more")
    private Boolean hasMore;

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

    //     "items":[  ],
//             "has_more":true,
//             "backoff":10,
//             "quota_max":300,
//             "quota_remaining":298

}
