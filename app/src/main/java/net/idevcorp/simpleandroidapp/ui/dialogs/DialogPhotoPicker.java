package net.idevcorp.simpleandroidapp.ui.dialogs;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import net.idevcorp.simpleandroidapp.R;

public class DialogPhotoPicker extends DialogFragment{

    View layoutPhotoPicker;
    ViewFlipper flipper;
    LayoutInflater layoutInflater;
    int imagesArray[] = {R.drawable.avatar1,R.drawable.avatar2,R.drawable.avatar3,R.drawable.avatar4,R.drawable.avatar5,R.drawable.avatar6,R.drawable.avatar7,R.drawable.avatar8};
    private OnImageSelectedListener listener;


    public interface OnImageSelectedListener{
        void imageSelected(Uri image);
    }

    public void setOnImageSelectedListener(OnImageSelectedListener listener){
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        layoutInflater = getActivity().getLayoutInflater();
        layoutPhotoPicker = layoutInflater.inflate(R.layout.layout_photo_picker, null,false);
        builder.setView(layoutPhotoPicker);
        flipper = layoutPhotoPicker.findViewById(R.id.viewFlipperMyProfile);

        for (int anImagesArray : imagesArray) {
            setDynamicImageViewAdd(anImagesArray);
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
                Uri profileImage = Uri.parse("android.resource://net.idevcorp.simpleandroidapp/"+imagesArray[flipper.getDisplayedChild()]);
                Toast.makeText(getActivity(), "Profile image added!", Toast.LENGTH_LONG).show();
                if(listener!=null){
                    listener.imageSelected(profileImage);
                }
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
