package net.idevcorp.simpleandroidapp.models.Users;

import com.google.gson.annotations.SerializedName;

public class UserBadgeModel {
    @SerializedName("bronze")
    private Long bronze;
    @SerializedName("silver")
    private Long silver;
    @SerializedName("gold")
    private Long gold;

    public Long getBronze() {
        return bronze;
    }

    public void setBronze(Long bronze) {
        this.bronze = bronze;
    }

    public Long getSilver() {
        return silver;
    }

    public void setSilver(Long silver) {
        this.silver = silver;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }
}
