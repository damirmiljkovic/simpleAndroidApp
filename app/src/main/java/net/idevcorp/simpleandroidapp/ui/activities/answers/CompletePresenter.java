package net.idevcorp.simpleandroidapp.ui.activities.answers;


import android.util.Log;

import net.idevcorp.simpleandroidapp.models.Answers.AnswerModel;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;
import net.idevcorp.simpleandroidapp.ui.interfaces.CompleteInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletePresenter {

    private CompleteInterface completeInterface;

    CompletePresenter(CompleteInterface completeInterface) {
        this.completeInterface = completeInterface;
    }

    public void findAnswer(String order, String sort, String site, String tags) {
        RetrofitBuilder.getInstance()
                .getAnswers(order, sort, site, tags)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                        completeInterface.onFindResult(response.body());
                    }

                    @Override
                    public void onFailure(Call<AnswerModel> call, Throwable t) {
                        Log.i("errorMsg",t.toString());
                    }
                });
    }


}
