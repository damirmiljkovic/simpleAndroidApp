package net.idevcorp.simpleandroidapp.models.answers;


import com.google.gson.annotations.SerializedName;

public class OwnerModel {

    private Long reputation;
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

    public Long getReputation() {
        return reputation;
    }

    public void setReputation(Long reputation) {
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

    //        "reputation":14868,
//                "user_id":6287910,
//                "user_type":"registered",
//                "profile_image":"https://www.gravatar.com/avatar/ba5244151f4f87b5ac81595cfff518de?s=128&d=identicon&r=PG&f=1",
//                "display_name":"Cheticamp",
//                "link":"https://stackoverflow.com/users/6287910/cheticamp"

}
