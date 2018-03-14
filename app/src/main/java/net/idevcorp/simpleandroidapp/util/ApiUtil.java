package net.idevcorp.simpleandroidapp.util;

import net.idevcorp.simpleandroidapp.network.Endpoints;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;

/**
 * Created by User on 13.3.2018.
 */

public class ApiUtil {

    public static Endpoints getEndpoints(){
        return RetrofitBuilder.getInstance().create(Endpoints.class);
    }
}
