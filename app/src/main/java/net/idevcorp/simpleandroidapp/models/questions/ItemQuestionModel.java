package net.idevcorp.simpleandroidapp.models.questions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemQuestionModel {

    private OwnerQuestionModel owner;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("is_answered")
    private Boolean isAnswered;
    @SerializedName("view_count")
    private Long viewCount;
    @SerializedName("answer_count")
    private Long answerCount;

    public List<String> getTags() {
        return tags;
    }

    public OwnerQuestionModel getOwner() {
        return owner;
    }

    public void setOwner(OwnerQuestionModel owner) {
        this.owner = owner;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Long answerCount) {
        this.answerCount = answerCount;
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

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("score")

    private Long score;
    @SerializedName("last_activity_date")
    private Long lastActivityDate;
    @SerializedName("creation_date")
    private Long creationDate;
    @SerializedName("question_id")
    private Long questionId;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private  String title;


//    {
//        "tags": [
//        "python-3.x",
//                "numpy"
//            ],
//        "owner": {
//        "reputation": 10,
//                "user_id": 7797962,
//                "user_type": "registered",
//                "profile_image": "https://lh6.googleusercontent.com/-ZrgTOUX7DWg/AAAAAAAAAAI/AAAAAAAAACI/3nVMRtmuWME/photo.jpg?sz=128",
//                "display_name": "Tom Jones",
//                "link": "https://stackoverflow.com/users/7797962/tom-jones"
//    },
//        "is_answered": false,
//            "view_count": 1,
//            "answer_count": 0,
//            "score": 0,
//            "last_activity_date": 1523119890,
//            "creation_date": 1523119890,
//            "question_id": 49709713,
//            "link": "https://stackoverflow.com/questions/49709713/why-the-n-symbol-is-in-numpy-empty-array",
//            "title": "Why the &#39;\\n&#39; symbol is in numpy empty array?"
//    }
}
