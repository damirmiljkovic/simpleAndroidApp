package net.idevcorp.simpleandroidapp.network;


import net.idevcorp.simpleandroidapp.models.answers.AnswerModel;
import net.idevcorp.simpleandroidapp.models.questions.QuestionModel;
import net.idevcorp.simpleandroidapp.models.users.UserModel;

import retrofit2.Call;
import retrofit2.http.GET;
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

    @GET("questions")
    Call<QuestionModel> getQuestions(@Query("order") String order, @Query("sort") String sort,
                                     @Query("tagged")String tags, @Query("site") String site);
    @GET("search/advanced")
    Call<QuestionModel> getSearchQuestion(@Query("order") String order, @Query("sort") String sort,
                                       @Query("title") String title, @Query("site") String site);
    @GET("users/{ids}")
    Call<UserModel> getUser(@Path("ids") long ids,@Query("order") String order, @Query("sort") String sort, @Query("site") String site);

}
