package net.idevcorp.simpleandroidapp.ui.activities.users;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.dialogs.DialogPhotoPicker;
import net.idevcorp.simpleandroidapp.ui.fragments.RegisterFragment;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import java.util.Objects;

public class MyProfileActivity extends AppCompatActivity {

    ImageView    imageViewProfileImage;
    EditText     editTextUser;
    TextView     textViewName;
    TextView     textViewSurname;
    TextView     textViewMail;
    TextView     textViewLink;
    FirebaseAuth auth;
    MenuItem     menuItemEdit;
    MenuItem     menuItemEditDone;
    private Uri profileImage;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(getApplicationContext());
        menuInflater.inflate(R.menu.menu_my_profile, menu);
        menuItemEditDone = menu.getItem(1);
        menuItemEdit = menu.getItem(0);
        menuItemEditDone.setEnabled(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemEditProfile:
                setTitle("finish edit with DONE >>");
                Toast.makeText(getApplicationContext(), "Change  photo or display name by tapping on it!", Toast.LENGTH_LONG).show();
                editFieldsToggle(0.5f, true);
                imageViewProfileImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogPhotoPicker dialogPhotoPicker = new DialogPhotoPicker();
                        dialogPhotoPicker.setOnImageSelectedListener(new DialogPhotoPicker.OnImageSelectedListener() {
                            @Override
                            public void imageSelected(Uri image) {
                                profileImage = image;
                                Picasso.get().load(profileImage).into(imageViewProfileImage);

                            }
                        });
                        dialogPhotoPicker.show(getSupportFragmentManager(), "picker");
                    }
                });
                break;
            case R.id.menuItemEditFinished:
                setTitle("My Profile");
                editFieldsToggle(1f, false);
                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(editTextUser.getText().toString())
                        .setPhotoUri(profileImage)
                        .build();

                try {
                    Objects.requireNonNull(auth.getCurrentUser()).updateProfile(profileChangeRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SharedPreferencesManager.setUri(getApplicationContext(), String.valueOf(profileImage));
                SharedPreferencesManager.setUser(getApplicationContext(), editTextUser.getText().toString());
                Picasso.get().load(Uri.parse(SharedPreferencesManager.getUri(getApplicationContext()))).into(imageViewProfileImage);
                Toast.makeText(getApplicationContext(), "editing done", Toast.LENGTH_LONG).show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void editFieldsToggle(float alpha, boolean status) {
        menuItemEditDone.setEnabled(status);
        imageViewProfileImage.setAlpha(alpha);
        editTextUser.setAlpha(alpha);
        imageViewProfileImage.setAlpha(alpha);
        editTextUser.setEnabled(status);
        imageViewProfileImage.setClickable(status);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        setTitle("My Profile");
        auth = FirebaseAuth.getInstance();

        imageViewProfileImage = findViewById(R.id.imageViewMyProfileFire);
        editTextUser = findViewById(R.id.editTextUserMyProfile);
        editTextUser.setEnabled(false);
        textViewMail = findViewById(R.id.textViewMailMyProfileFire);
        textViewLink = findViewById(R.id.textViewLinkMyProfileFire);
        Picasso.get().load(Uri.parse(SharedPreferencesManager.getUri(getApplicationContext()))).into(imageViewProfileImage);
        editTextUser.setText(SharedPreferencesManager.getUser(getApplicationContext()));
        textViewMail.setText(SharedPreferencesManager.getEmail(getApplicationContext()));


    }
}
