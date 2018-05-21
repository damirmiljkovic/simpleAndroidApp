package net.idevcorp.simpleandroidapp.ui.dialogs;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import net.idevcorp.simpleandroidapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class DialogPhotoPicker extends DialogFragment{

    @BindView(R.id.viewFlipperMyProfile) ViewFlipper flipper;
    @BindView(R.id.textViewPhotoPicker) TextView textViewCaption;
    LayoutInflater layoutInflater;
    int imagesArray[] = {R.drawable.avatar1,R.drawable.avatar2,R.drawable.avatar3,R.drawable.avatar4,R.drawable.avatar5,R.drawable.avatar6,R.drawable.avatar7,R.drawable.avatar8};
    private OnImageSelectedListener listener;
    View layoutPhotoPicker;


    public interface OnImageSelectedListener{
        void imageSelected(Uri image);
    }

    public void setOnImageSelectedListener(OnImageSelectedListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        layoutInflater = getActivity().getLayoutInflater();
        layoutPhotoPicker = layoutInflater.inflate(R.layout.layout_photo_picker, null,false);
        ButterKnife.bind(this,layoutPhotoPicker);
        builder.setView(layoutPhotoPicker);
        textViewCaption.setText(R.string.tap_for_next_photo);
        for (int imageOfArray : imagesArray) {
            setDynamicImageViewAdd(imageOfArray);
        }
        return builder.create();

    }
    @OnClick(R.id.viewFlipperMyProfile)
    public void showNextPhoto(){
        textViewCaption.setText(R.string.photo_picker_caption);
        flipper.showNext();
    }
    @OnLongClick(R.id.viewFlipperMyProfile)
    public boolean keepPhoto(){
        Uri profileImage = Uri.parse(getString(R.string.package_name)+imagesArray[flipper.getDisplayedChild()]);
        if(listener!=null){
            listener.imageSelected(profileImage);
        }
        dismiss();
        return false;
    }
    private void setDynamicImageViewAdd(int res) {
        ImageView imageViewNew;
        imageViewNew = new ImageView(getActivity());
        imageViewNew.setBackgroundResource(res);
        flipper.addView(imageViewNew);
    }
}
