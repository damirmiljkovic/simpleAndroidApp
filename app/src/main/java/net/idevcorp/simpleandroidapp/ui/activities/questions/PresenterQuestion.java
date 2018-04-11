package net.idevcorp.simpleandroidapp.ui.activities.questions;

import android.util.Log;

import net.idevcorp.simpleandroidapp.models.QuestionModel;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterQuestion {
    private QuestionInterface questionInterface;

    PresenterQuestion(QuestionInterface questionInterface){
        this.questionInterface = questionInterface;
    }
    public void findQuestion(String order, String sort, String site, String tags){
        RetrofitBuilder.getInstance()
                .getQuestions(order,sort,site,tags)
                .enqueue(new Callback<QuestionModel>() {
                    @Override
                    public void onResponse(Call<QuestionModel> call, Response<QuestionModel> response) {
                        questionInterface.onFindQuestion(response.body());
                    }

                    @Override
                    public void onFailure(Call<QuestionModel> call, Throwable t) {
                        Log.e("failuer", t.getMessage(), t);
                    }
                });
    }

}
