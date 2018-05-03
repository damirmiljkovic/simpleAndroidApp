package net.idevcorp.simpleandroidapp.ui.activities.questions;

import android.support.annotation.NonNull;
import android.util.Log;

import net.idevcorp.simpleandroidapp.models.Questions.QuestionModel;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;
import net.idevcorp.simpleandroidapp.ui.interfaces.QuestionInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterQuestion {
    private QuestionInterface questionInterface;

    PresenterQuestion(QuestionInterface questionInterface){
        this.questionInterface = questionInterface;
    }
    public void findQuestion(String order, String sort, String tags, String site){
        RetrofitBuilder.getInstance()
                .getQuestions(order,sort,tags,site)
                .enqueue(new Callback<QuestionModel>() {
                    @Override
                    public void onResponse(@NonNull Call<QuestionModel> call, @NonNull Response<QuestionModel> response) {
                        questionInterface.onFindQuestion(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuestionModel> call, @NonNull Throwable t) {
                        Log.e("failuer", t.getMessage(), t);
                    }
                });
    }
    public void searchQuestion(String order, String sort, String title, String site){
        RetrofitBuilder.getInstance()
                .getSearchQuestion(order,sort,title,site)
                .enqueue(new Callback<QuestionModel>() {
                    @Override
                    public void onResponse(@NonNull Call<QuestionModel> call, @NonNull Response<QuestionModel> response) {
                        questionInterface.onFindQuestion(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuestionModel> call, @NonNull Throwable t) {

                    }
                });
    }

}
