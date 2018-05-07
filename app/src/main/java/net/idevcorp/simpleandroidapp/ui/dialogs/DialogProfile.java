package net.idevcorp.simpleandroidapp.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.idevcorp.simpleandroidapp.R;

public class DialogProfile extends DialogFragment {

    TextView textViewUserName;
    TextView textViewUserLink;
    ImageView imageViewProfileImage;
    View layoutDialog;

    public static final String USER_IMAGE_URI = "userImageUri";
    public static final String USER_NAME = "userName";
    public static final String USER_LINK = "userLink";
    public static final String STACK_OVERFLOW_ADDRESS = "https://stackoverflow.com/";

    public static DialogProfile newInstance(String stringImage, String userName, String userLink){
        DialogProfile dialogProfile = new DialogProfile();
        Bundle bundle = new Bundle();
        bundle.putString(USER_IMAGE_URI,stringImage);
        bundle.putString(USER_NAME,userName);
        bundle.putString(USER_LINK,userLink);
        dialogProfile.setArguments(bundle);
        return dialogProfile;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        layoutDialog = layoutInflater.inflate(R.layout.layout_profile,null);

        imageViewProfileImage = layoutDialog.findViewById(R.id.imageViewProfileImage);
        Picasso.get().load(Uri.parse(getArguments().getString(USER_IMAGE_URI))).into(imageViewProfileImage);
        textViewUserLink = layoutDialog.findViewById(R.id.textViewProfileLink);
        textViewUserLink.setText(getArguments().getString(USER_LINK,STACK_OVERFLOW_ADDRESS));
        layoutDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        textViewUserLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getArguments().getString(USER_LINK)));
                startActivity(intent);
            }
        });
        textViewUserName = layoutDialog.findViewById(R.id.textViewProfileName);
        textViewUserName.setText(getArguments().getString(USER_NAME,"UNKNOWN"));

        builder.setView(layoutDialog);

        return builder.create();
    }
}
