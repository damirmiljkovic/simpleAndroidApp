package net.idevcorp.simpleandroidapp.models.users;

import com.google.gson.annotations.SerializedName;

public class UserItemModel {
    @SerializedName("badge_counts")
    private UserBadgeModel userBadgeModel;
    @SerializedName("account_id")
    private Long accountId;
    @SerializedName("is_employee")
    private Boolean isEmployee;
    @SerializedName("reputation")
    private Long reputation;
    @SerializedName("creation_date")
    private Long creationDate;
    @SerializedName("user_type")
    private String userType;
    @SerializedName("user_id")
    private Long userId;
    @SerializedName("website_url")
    private String websiteUrl;
    @SerializedName("link")
    private String link;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("display_name")
    private String displayName;

    public UserBadgeModel getUserBadgeModel() {
        return userBadgeModel;
    }

    public void setUserBadgeModel(UserBadgeModel userBadgeModel) {
        this.userBadgeModel = userBadgeModel;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public Long getReputation() {
        return reputation;
    }

    public void setReputation(Long reputation) {
        this.reputation = reputation;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
}
//        "items": [
//        {
//        "badge_counts": {
//        "bronze": 1,
//        "silver": 0,
//        "gold": 0
//        },
//        "account_id": 12544551,
//        "is_employee": false,
//        "last_modified_date": 1524946683,
//        "last_access_date": 1525032301,
//        "reputation_change_year": 0,
//        "reputation_change_quarter": 0,
//        "reputation_change_month": 0,
//        "reputation_change_week": 0,
//        "reputation_change_day": 0,
//        "reputation": 1,
//        "creation_date": 1513881555,
//        "user_type": "registered",
//        "user_id": 9128363,
//        "website_url": "",
//        "link": "https://stackoverflow.com/users/9128363/mir-zona",
//        "profile_image": "https://i.stack.imgur.com/M81TF.jpg?s=128&g=1",
//        "display_name": "mir_zona"
//        }
//        ],