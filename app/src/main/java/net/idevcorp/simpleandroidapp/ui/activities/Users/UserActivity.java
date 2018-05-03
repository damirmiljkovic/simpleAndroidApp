package net.idevcorp.simpleandroidapp.ui.activities.Users;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.Users.UserItemModel;
import net.idevcorp.simpleandroidapp.models.Users.UserModel;
import net.idevcorp.simpleandroidapp.ui.interfaces.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements UserInterface {

    private static final List<UserItemModel> USER_MODELS = new ArrayList<>();
    TextView profileUserName;
    TextView profileReputation;
    TextView profileLink;
    ImageView profileImage;
    Uri profileImageUri;
    UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("My Stack Overflow Profile");
        profileUserName = findViewById(R.id.textViewNameMyProfile);
        profileReputation = findViewById(R.id.textViewReputMyProfile);
        profileLink = findViewById(R.id.textViewLinkMyProfile);
        profileImage = findViewById(R.id.imageViewMyProfile);
        userPresenter = new UserPresenter(this);
        userPresenter.findUser(9128363,"desc","reputation","stackoverflow");


    }

    @Override
    public void onFindUser(UserModel userModel) {
        if (userModel != null){
            UserItemModel myUserItemModel= userModel.getUserItemModel().get(0);
            profileImageUri = Uri.parse(myUserItemModel.getProfileImage());
            Picasso.get().load(profileImageUri).into(profileImage);
            String reputationText = "reputation: " + String.valueOf(myUserItemModel.getReputation());
            String linkText =myUserItemModel.getLink();
            String nameText = myUserItemModel.getDisplayName();
            profileReputation.setText(reputationText);
            profileUserName.setText(nameText);
            profileLink.setText(linkText);

        }

    }

}
