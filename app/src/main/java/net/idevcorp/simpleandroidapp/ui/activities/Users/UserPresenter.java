package net.idevcorp.simpleandroidapp.ui.activities.Users;

import android.util.Log;

import net.idevcorp.simpleandroidapp.models.Users.UserModel;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;
import net.idevcorp.simpleandroidapp.ui.interfaces.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {

    private UserInterface userInterface;
    public UserPresenter(UserInterface userInterface){
        this.userInterface = userInterface;
    }
    public void findUser(long ids, String order, String sort, String site){
        RetrofitBuilder.getInstance()
                .getUser(ids,order,sort,site)
                .enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        userInterface.onFindUser(response.body());
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Log.i("errorMsg",t.getMessage());

                    }
                });
    }
}
