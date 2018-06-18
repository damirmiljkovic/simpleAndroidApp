package net.idevcorp.simpleandroidapp.ui.activities.answers;


import android.support.annotation.NonNull;
import android.util.Log;

import net.idevcorp.simpleandroidapp.models.answers.AnswerModel;
import net.idevcorp.simpleandroidapp.models.users.UserModel;
import net.idevcorp.simpleandroidapp.network.Endpoints;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;
import net.idevcorp.simpleandroidapp.ui.interfaces.CompleteInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletePresenter {

    private CompleteInterface completeInterface;
    private Endpoints endpoints;
    private UserModel userModel;


    CompletePresenter(Endpoints endpoints, UserModel userModel) {
//        this.completeInterface = completeInterface
        this.endpoints = endpoints;
        this.userModel = userModel;
    }

    public void findAnswer(String order, String sort, String site, String tags) {
        endpoints.getAnswers(order, sort, site, tags)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AnswerModel> call, @NonNull Response<AnswerModel> response) {
//                        completeInterface.onFindResult(response.body());
                    }

                    @Override
                    public void onFailure(Call<AnswerModel> call, @NonNull Throwable t) {
                        Log.i("errorMsg",t.toString());
                    }
                });
    }


}
