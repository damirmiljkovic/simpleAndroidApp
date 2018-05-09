package net.idevcorp.simpleandroidapp.ui.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.activities.users.MyProfileActivity;

public class DialogPhotoPicker extends DialogFragment{

    View layoutPhotoPicker;
    ViewFlipper flipper;
    LayoutInflater layoutInflater;
    int imagesArray[] = {R.drawable.avatar1,R.drawable.avatar2,R.drawable.avatar3,R.drawable.avatar4,R.drawable.avatar5,R.drawable.avatar6,R.drawable.avatar7,R.drawable.avatar8};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        layoutInflater = getActivity().getLayoutInflater();
        layoutPhotoPicker = layoutInflater.inflate(R.layout.layout_photo_picker,null,false);
        builder.setView(layoutPhotoPicker);
        flipper = layoutPhotoPicker.findViewById(R.id.viewFlipperMyProfile);

        for (int i=0;i<imagesArray.length;i++){
            setDynamicImageViewAdd(imagesArray[i]);
        }
        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.showNext();
            }
        });
        flipper.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MyProfileActivity.uriProfileImage = Uri.parse("android.resource://net.idevcorp.simpleandroidapp/"+imagesArray[flipper.getDisplayedChild()]);
                Toast.makeText(getActivity(), "Profile image added!", Toast.LENGTH_LONG).show();
                dismiss();
                return false;
            }
        });
        return builder.create();

    }

    private void setDynamicImageViewAdd(int res) {
        ImageView imageViewNew;
        imageViewNew = new ImageView(getActivity());
        imageViewNew.setBackgroundResource(res);
        flipper.addView(imageViewNew);
    }
}
