package com.example.foundnlost;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfileActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private TextView profileNameTextView, profileSurnameTextView, profilePhonenoTextView;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ImageView profilePicImageView;
    private FirebaseStorage firebaseStorage;
    private TextView textViewemailname;
    private BottomNavigationView bottomNavigationView;
    private EditText editTextName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        setupUIViews();
        //editTextName = (EditText)findViewById(R.id.et_username);
        //profilePicImageView = findViewById(R.id.profile_pic_imageView);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference storageReference = firebaseStorage.getReference();
        // Get the image stored on Firebase via "User id/Images/Profile Pic.jpg".
//        storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                // Using "Picasso" (http://square.github.io/picasso/) after adding the dependency in the Gradle.
//                // ".fit().centerInside()" fits the entire image into the specified area.
//                // Finally, add "READ" and "WRITE" external storage permissions in the Manifest.
//                Picasso.get().load(uri).fit().centerInside().into(profilePicImageView);
//            }
//        });
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                Toast.makeText(getApplicationContext(), "History", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_home:
                                Intent inentHome = new Intent(ProfileActivity.this, ProfileActivity.class);
                                startActivity(inentHome);
                                break;
                            case R.id.action_profile:
                                Intent inentProfile = new Intent(ProfileActivity.this, ProfileActivity.class);
                                startActivity(inentProfile);
                                break;
                            case R.id.action_upload:
                                Intent IntentUpload = new Intent(ProfileActivity.this,Upload.class);
                                startActivity(IntentUpload);
                                break;
                        }
                        return true;
                    }
                });
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                Userinformation userProfile = dataSnapshot.getValue(Userinformation.class);
                profileNameTextView.setText(userProfile.getUserName());
                profileSurnameTextView.setText(userProfile.getUserSurname());
                profilePhonenoTextView.setText(userProfile.getUserPhoneno());
                textViewemailname=(TextView)findViewById(R.id.textViewEmailAdress);
                textViewemailname.setText(user.getEmail());
            }
            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupUIViews()
    {
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        profileNameTextView = findViewById(R.id.profile_name_textView);
        profileSurnameTextView = findViewById(R.id.profile_surname_textView);
        profilePhonenoTextView = findViewById(R.id.profile_phoneno_textView);
    }

    public void buttonClickedEditName(View view) {
//        LayoutInflater inflater = getLayoutInflater();
//        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_edit_name, null);
//        final EditText etUsername = alertLayout.findViewById(R.id.et_username);
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("Name Edit");
//        // this is set the view from XML inside AlertDialog
//        alert.setView(alertLayout);
//        // disallow cancel of AlertDialog on click of back button and outside touch
//        alert.setCancelable(false);
//        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String name = etUsername.getText().toString();
//                String surname = profileSurnameTextView.getText().toString();
//                String phoneno =  profilePhonenoTextView.getText().toString();
//                Userinformation userinformation = new Userinformation(name,surname, phoneno);
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                databaseReference.child(user.getUid()).setValue(userinformation);
//                databaseReference.child(user.getUid()).setValue(userinformation);
//                etUsername.onEditorAction(EditorInfo.IME_ACTION_DONE);
//            }
//        });
//        AlertDialog dialog = alert.create();
//        dialog.show();
    }
    public void buttonClickedEditSurname(View view) {
//        LayoutInflater inflater = getLayoutInflater();
//        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_edit_surname, null);
//        final EditText etUserSurname = alertLayout.findViewById(R.id.et_userSurname);
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("Surname Edit");
//        // this is set the view from XML inside AlertDialog
//        alert.setView(alertLayout);
//        // disallow cancel of AlertDialog on click of back button and outside touch
//        alert.setCancelable(false);
//        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                String name = profileNameTextView.getText().toString();
//                String surname = etUserSurname.getText().toString();
//                String phoneno =  profilePhonenoTextView.getText().toString();
//                Userinformation userinformation = new Userinformation(name,surname, phoneno);
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                databaseReference.child(user.getUid()).setValue(userinformation);
//                databaseReference.child(user.getUid()).setValue(userinformation);
//                etUserSurname.onEditorAction(EditorInfo.IME_ACTION_DONE);
//            }
//        });
//        AlertDialog dialog = alert.create();
//        dialog.show();
    }
    public void buttonClickedEditPhoneNo(View view) {
//        LayoutInflater inflater = getLayoutInflater();
//        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog_edit_phoneno, null);
//        final EditText etUserPhoneno = alertLayout.findViewById(R.id.et_userPhoneno);
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("Phone No Edit");
//        // this is set the view from XML inside AlertDialog
//        alert.setView(alertLayout);
//        // disallow cancel of AlertDialog on click of back button and outside touch
//        alert.setCancelable(false);
//        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String name = profileNameTextView.getText().toString();
//                String surname = profileSurnameTextView.getText().toString();
//                String phoneno =  etUserPhoneno.getText().toString();
//                Userinformation userinformation = new Userinformation(name,surname, phoneno);
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                databaseReference.child(user.getUid()).setValue(userinformation);
//                databaseReference.child(user.getUid()).setValue(userinformation);
//                etUserPhoneno.onEditorAction(EditorInfo.IME_ACTION_DONE);
//            }
//        });
//        AlertDialog dialog = alert.create();
//        dialog.show();
    }

    public void navigateLogOut(View v){
        FirebaseAuth.getInstance().signOut();
        Intent inent = new Intent(this, MainActivity.class);
        startActivity(inent);
    }
}