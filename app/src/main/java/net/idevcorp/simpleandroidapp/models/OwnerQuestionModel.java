package net.idevcorp.simpleandroidapp.models;

import com.google.gson.annotations.SerializedName;

public class OwnerQuestionModel {

    @SerializedName("reputation")
    private Integer reputation;
    @SerializedName("user_id")
    private Long userId;
    @SerializedName("user_type")
    private String userType;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("link")
    private String link;

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

//    "owner": {
//        "reputation": 10,
//                "user_id": 7797962,
//                "user_type": "registered",
//                "profile_image": "https://lh6.googleusercontent.com/-ZrgTOUX7DWg/AAAAAAAAAAI/AAAAAAAAACI/3nVMRtmuWME/photo.jpg?sz=128",
//                "display_name": "Tom Jones",
//                "link": "https://stackoverflow.com/users/7797962/tom-jones"

    }
