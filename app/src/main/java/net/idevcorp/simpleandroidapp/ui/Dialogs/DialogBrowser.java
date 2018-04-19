package net.idevcorp.simpleandroidapp.ui.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.activities.questions.QuestionActivity;
import net.idevcorp.simpleandroidapp.ui.activities.questions.WebViewActivity;

public class DialogBrowser extends DialogFragment {

    private static final String URL_QUESTION ="urlQuestion";

    public static DialogBrowser newInstance(String urlQuestion){
        DialogBrowser dialogBrowser = new DialogBrowser();
        Bundle bundle = new Bundle();
        bundle.putString("urlQuestion",urlQuestion);
        dialogBrowser.setArguments(bundle);
        return dialogBrowser;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.btn_star)
                .setTitle(R.string.title_dialog)
                .setItems(R.array.dialog_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Toast.makeText(getActivity(), "not ready yet!", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                    Intent intent = new Intent(getContext(),WebViewActivity.class);
                                    intent.putExtra("urlQuestion",getArguments().getString(URL_QUESTION,""));
                                    startActivity(intent);
                                }
                                break;

                        }

                    }
                });
        return builder.create();
    }
}
