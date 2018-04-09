package net.idevcorp.simpleandroidapp.network;


import net.idevcorp.simpleandroidapp.models.AnswerModel;
import net.idevcorp.simpleandroidapp.models.QuestionModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Endpoints {

    @GET("answers?order=desc&sort=activity&site=stackoverflow")
    Call<AnswerModel> getAnswers();
//    @GET("answers?order=desc&sort=activity&site=stackoverflow")
//    Call<AnswerModel> getAnswers(@Query("tagged")String tags);
//    @GET("answers?order=asc&sort=activity&site=stackoverflow")
//    Call<AnswerModel> getAnswersAcending(@Query("tagged")String tags);

    @GET("answers")
    Call<AnswerModel> getAnswers(@Query("order") String order, @Query("sort") String sort,
                                 @Query("site") String site, @Query("tagged")String tags);
    @GET("questions?order=desc&sort=activity&site=stackoverflow")
    Call<QuestionModel> getQuestions();



}
