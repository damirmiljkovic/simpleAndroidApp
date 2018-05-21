package net.idevcorp.simpleandroidapp.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.activities.questions.WebViewActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogBrowser extends DialogFragment {

    public static final String URL_QUESTION ="urlQuestion";
    View dialogLayout;
    String linkStr;
    LayoutInflater inflater;

    public static DialogBrowser newInstance(String urlQuestion){
        DialogBrowser dialogBrowser = new DialogBrowser();
        Bundle bundle = new Bundle();
        bundle.putString(URL_QUESTION,urlQuestion);
        dialogBrowser.setArguments(bundle);
        return dialogBrowser;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        linkStr = getArguments().getString(URL_QUESTION,"");
        inflater = getActivity().getLayoutInflater();
        dialogLayout = inflater.inflate(R.layout.dialog_browser_layout,null);
        ButterKnife.bind(this,dialogLayout);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogLayout);
        return builder.create();
    }
    @OnClick(R.id.buttonDialogApp)
    public void proceedWithApp(){
        Intent intent = new Intent(getActivity(),WebViewActivity.class);
        intent.putExtra(URL_QUESTION,linkStr);
        startActivity(intent);
    }
    @OnClick(R.id.buttonDialogBrowser)
    public void proceedWithBrowser(){
        Intent intentBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(linkStr));
        startActivity(intentBrowser);
    }
}
