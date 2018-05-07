package net.idevcorp.simpleandroidapp.ui.activities.questions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.support.v7.widget.ShareActionProvider;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.dialogs.DialogBrowser;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

public class WebViewActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;
    private String questionURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        setTitle(SharedPreferencesManager.getEmail(this));

        questionURL = getIntent().getStringExtra(DialogBrowser.URL_QUESTION);
        WebView webViewQuestion = findViewById(R.id.webViewQuestion);
        webViewQuestion.getSettings().getJavaScriptEnabled();
        webViewQuestion.setWebViewClient(new WebViewClient());
        webViewQuestion.loadUrl(questionURL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_share,menu);

        MenuItem menuItem = menu.findItem(R.id.shareId);
        shareActionProvider  = (ShareActionProvider)MenuItemCompat.getActionProvider(menuItem);
        setShareIntent(createShareIntent());
        return true;
    }
    private void setShareIntent(Intent shareIntent){
        if (shareActionProvider != null){
            shareActionProvider.setShareIntent(shareIntent);
        }
    }
    private Intent createShareIntent(){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,questionURL);
        return shareIntent;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.shareId){
//            Toast.makeText(this, "u dure", Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
