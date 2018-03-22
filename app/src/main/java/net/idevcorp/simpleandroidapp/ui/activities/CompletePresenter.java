package net.idevcorp.simpleandroidapp.ui.activities;


import android.util.Log;
import android.widget.Toast;

import net.idevcorp.simpleandroidapp.models.AnswerModel;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletePresenter {

    private CompleteInterface completeInterface;

    CompletePresenter(CompleteInterface completeInterface) {
        this.completeInterface = completeInterface;
    }

    public void find(String order, String sort, String site, String tags) {
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
