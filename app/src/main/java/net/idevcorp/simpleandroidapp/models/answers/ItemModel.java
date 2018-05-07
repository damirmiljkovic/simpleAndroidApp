package net.idevcorp.simpleandroidapp.models.answers;


import com.google.gson.annotations.SerializedName;

public class ItemModel {

    private OwnerModel owner;
    @SerializedName("is_accepted")
    private Boolean isAccepted;
    @SerializedName("score")
    private Long score;
    @SerializedName("lastActivityDate")
    private Long lastActivityDate;
    @SerializedName("lastEditDate")
    private Long lastEditDate;
    @SerializedName("creationDate")
    private Long creationDate;
    @SerializedName("answerId")
    private Long answerId;
    @SerializedName("questionId")
    private Long questionId;


    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Long lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Long getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Long lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public OwnerModel getOwner() {
        return owner;
    }

    public void setOwner(OwnerModel owner) {
        this.owner = owner;
    }





//    "owner":{  },
//            "is_accepted":true,
//            "score":3,
//            "last_activity_date":1520857560,
//            "last_edit_date":1520857560,
//            "creation_date":1520442388,
//            "answer_id":49157547,
//            "question_id":49105946

}
