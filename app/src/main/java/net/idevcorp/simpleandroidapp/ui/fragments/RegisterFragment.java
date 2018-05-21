package net.idevcorp.simpleandroidapp.ui.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.activities.answers.CompleteActivity;
import net.idevcorp.simpleandroidapp.ui.dialogs.DialogPhotoPicker;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends Fragment {

    @BindView(R.id.editTextMailId)  EditText     editTextRegisterMail;
    @BindView(R.id.editTextPassId)  EditText     editTextRegisterPass;
    @BindView(R.id.editTextUserReg)  EditText     editTextRegisterUser;
    @BindViews({R.id.editTextMailId,R.id.editTextPassId,R.id.editTextUserReg}) List<EditText> editTextList;
    @BindView(R.id.buttonReg)  Button       buttonRegister;
    @BindView(R.id.checkBoxRegister)  CheckBox     checkBoxRegister;
    @BindView(R.id.imageViewLogoId)  ImageView    userImage;
    @BindString(R.string.registration) String titleRegistration;
    private Uri          uriProfileImage;
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(titleRegistration);
        return inflater.inflate(R.layout.layout_reg, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        auth = FirebaseAuth.getInstance();
        PreferenceManager.getDefaultSharedPreferences(getActivity());

    }
    @OnClick(R.id.imageViewLogoId)
    public void choseAvatar(){
        DialogPhotoPicker dialogPhotoPicker = new DialogPhotoPicker();
        dialogPhotoPicker.setOnImageSelectedListener(new DialogPhotoPicker.OnImageSelectedListener() {
            @Override
            public void imageSelected(Uri image) {
                uriProfileImage = image;
                Picasso.get().load(uriProfileImage).into(userImage);

            }
        });
        dialogPhotoPicker.show(getFragmentManager(), "dialogPhotoPicker");
    }
    @OnClick(R.id.buttonReg)
    public void createUserFirebase() {
        String mailET = editTextRegisterMail.getText().toString();
        String passET = editTextRegisterMail.getText().toString();
        final String userET = editTextRegisterUser.getText().toString();

        if (mailET.isEmpty() || passET.isEmpty() || userET.isEmpty()) {
            Toast.makeText(getActivity(), R.string.empty_fields, Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(mailET, passET)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser currentUser = task.getResult().getUser();
                                UserProfileChangeRequest profileChangeRequest;
                                if (uriProfileImage != null){
                                    profileChangeRequest = new UserProfileChangeRequest.Builder()
                                            .setPhotoUri(uriProfileImage)
                                            .setDisplayName(userET)
                                            .build();
                                    SharedPreferencesManager.setUri(getActivity(), String.valueOf(uriProfileImage));
                                }else {
                                    Uri uriImageDefault = Uri.parse(getString(R.string.package_name)+R.drawable.unknown_user);
                                    profileChangeRequest = new UserProfileChangeRequest.Builder()
                                            .setPhotoUri(uriImageDefault)
                                            .setDisplayName(userET)
                                            .build();
                                    SharedPreferencesManager.setUri(getActivity(), getString(R.string.package_name)+String.valueOf(R.drawable.unknown_user));
                                }
                                currentUser.updateProfile(profileChangeRequest);
                                SharedPreferencesManager.setUser(getActivity(), userET);
                                SharedPreferencesManager.setKeepMeLoggedIn(getActivity(), checkBoxRegister.isChecked());
                                SharedPreferencesManager.setEmail(getActivity(), task.getResult().getUser().getEmail());
                                Intent intent = new Intent(getContext(), CompleteActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity(), R.string.something_is_wrong, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

}
