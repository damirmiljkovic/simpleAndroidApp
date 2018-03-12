package net.idevcorp.simpleandroidapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnswerModel {

    private List<ItemModel> items;
    @SerializedName("has_more")
    private Boolean hasMore;

//     "items":[  ],
//             "has_more":true,
//             "backoff":10,
//             "quota_max":300,
//             "quota_remaining":298

}
