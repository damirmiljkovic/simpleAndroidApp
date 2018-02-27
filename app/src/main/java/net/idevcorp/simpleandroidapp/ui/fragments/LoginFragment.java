package net.idevcorp.simpleandroidapp.ui.fragments;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.idevcorp.simpleandroidapp.R;

public class LoginFragment extends Fragment  {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Loging in");
        return inflater.inflate(R.layout.layout_login,container,false);
    }



}
