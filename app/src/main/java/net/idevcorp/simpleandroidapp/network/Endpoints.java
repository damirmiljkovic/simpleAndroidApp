package net.idevcorp.simpleandroidapp.network;


import net.idevcorp.simpleandroidapp.models.AnswerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Endpoints {

    @GET("answers?order=desc&sort=activity&site=stackoverflow")
    Call<AnswerModel> getAnswers();
    @GET("answers?order=desc&sort=activity&site=stackoverflow")
    Call<AnswerModel> getAnswers(@Query("tagged")String tags);


}
