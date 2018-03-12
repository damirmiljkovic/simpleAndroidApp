package net.idevcorp.simpleandroidapp.network;


import net.idevcorp.simpleandroidapp.models.AnswerModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Endpoints {

    @GET("answers?order=desc&sort=activity&site=stackoverflow")
    Call<AnswerModel> getAnswers();

}
