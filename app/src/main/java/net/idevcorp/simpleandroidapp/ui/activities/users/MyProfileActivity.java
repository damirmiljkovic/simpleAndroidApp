package net.idevcorp.simpleandroidapp.ui.activities.users;

import android.content.Intent;
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
import net.idevcorp.simpleandroidapp.ui.activities.InitialActivity;
import net.idevcorp.simpleandroidapp.ui.dialogs.DialogPhotoPicker;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyProfileActivity extends AppCompatActivity {


    FirebaseAuth auth;
    private Uri profileImage;
    private boolean readyToEdit = true;
    @BindView(R.id.imageViewMyProfileFire) ImageView imageViewProfileImage;
    @BindView(R.id.editTextUserMyProfile) EditText editTextUser;
    @BindView(R.id.textViewMailMyProfileFire) TextView textViewMail;
    @BindView(R.id.textViewLinkMyProfileFire) TextView textViewLink;
    @BindString(R.string.done) String titleDone;
    @BindString(R.string.edit) String titleEdit;
    @BindString(R.string.editing_done) String msgEditDone;
    @BindString(R.string.my_profile) String titleMyProfile;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_my_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemEditProfile:
                if (readyToEdit){
                    readyToEdit = false;
                    item.setTitle(titleDone);
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
                            dialogPhotoPicker.show(getSupportFragmentManager(), getString(R.string.picker));
                        }
                    });
                    break;
                }else {
                    readyToEdit = true;
                    item.setTitle(titleEdit);
                    editFieldsToggle(1f, false);
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(editTextUser.getText().toString())
                            .setPhotoUri(profileImage)
                            .build();
                    Objects.requireNonNull(auth.getCurrentUser()).updateProfile(profileChangeRequest);

                    SharedPreferencesManager.setUri(getApplicationContext(), String.valueOf(profileImage));
                    SharedPreferencesManager.setUser(getApplicationContext(), editTextUser.getText().toString());
                    Toast.makeText(getApplicationContext(), msgEditDone, Toast.LENGTH_LONG).show();

                    break;
                }
            case  R.id.menuSignOut :
                    auth.signOut();
                    SharedPreferencesManager.clearSavedPreferences(getApplicationContext());
                    Intent intent = new Intent(getApplicationContext(), InitialActivity.class);
                    startActivity(intent);
                    break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void editFieldsToggle(float alpha, boolean status) {
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
        ButterKnife.bind(this);
        setTitle(titleMyProfile);
        auth = FirebaseAuth.getInstance();
        editTextUser.setEnabled(false);
        Picasso.get().load(Uri.parse(SharedPreferencesManager.getUri(getApplicationContext()))).into(imageViewProfileImage);
        editTextUser.setText(SharedPreferencesManager.getUser(getApplicationContext()));
        textViewMail.setText(SharedPreferencesManager.getEmail(getApplicationContext()));


    }
}
