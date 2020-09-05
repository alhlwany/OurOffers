package com.sbm.ouroffers;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sbm.ouroffers.Class.Gnr_Variable;
import com.sbm.ouroffers.Class.Users;
import com.sbm.ouroffers.Prevalent.Prevalent;
import com.sbm.ouroffers.Users.HomeActivity;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import io.paperdb.Paper;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {
    private ImageView obj_civ_splash_img;
    Animation animation, animation2, animation3, animation4, animation_mix;
    private Button  obj_log_lang_Button,joinNowButton, loginButton;

    private ProgressDialog loadingBar;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);
        //region hide toolbar
        getSupportActionBar().hide();
        //endregion

        joinNowButton = (Button) findViewById(R.id.main_join_now_btn);
        loginButton = (Button) findViewById(R.id.main_login_btn);
        loadingBar = new ProgressDialog(this);


        obj_log_lang_Button=(Button) findViewById(R.id.btn_log_lang);
        obj_civ_splash_img=(ImageView) findViewById(R.id.app_logo);
        animation_mix= AnimationUtils.loadAnimation(getApplicationContext(), R.animator.zmix_anim);
        animation=AnimationUtils.loadAnimation(getApplicationContext(), R.animator.rotate);
        animation2=AnimationUtils.loadAnimation(getApplicationContext(), R.animator.alpha_disappears);
        animation3=AnimationUtils.loadAnimation(getApplicationContext(), R.animator.scale_max_min);
        animation4=AnimationUtils.loadAnimation(getApplicationContext(), R.animator.trans_slide);
        obj_civ_splash_img.startAnimation(animation2);


        obj_log_lang_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

              /* String languagename=Locale.getDefault().getDisplayLanguage().toString();
                String country=Locale.getDefault().getCountry();
                if (languagename.equals("English")) {
                    //Gnr_Variable gnrVR=(Gnr_Variable) getApplicationContext();
                    //gnrVR.setlang("ar");
                    setLocale("ar");
                    //obj_btn_registr_lang.setText("English");
                } else {
                    //Gnr_Variable gnrVR=(Gnr_Variable) getApplicationContext();
                    //gnrVR.setlang("en");
                    setLocale("en");

                    //obj_btn_registr_lang.setText("العربية");
                }*/

                String languagename=obj_log_lang_Button.getText().toString();
                //languagename=Locale.getDefault().getDisplayLanguage().toString();
                if (languagename.equals("En")) {
                    setLocale("en");
                } else {
                    setLocale("ar");
                }

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

        if (UserPhoneKey != "" && UserPasswordKey != "")
        {
            if (!TextUtils.isEmpty(UserPhoneKey)  &&  !TextUtils.isEmpty(UserPasswordKey))
            {
                AllowAccess(UserPhoneKey, UserPasswordKey);

                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait.....");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
    }

    //region Language
    @RequiresApi(api= Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setLocale(String lang) {
        Locale myLocale=new Locale(lang);
        Resources res=getResources();
        DisplayMetrics dm=res.getDisplayMetrics();
        Configuration conf=new Configuration();
        conf.locale=myLocale;
        getApplicationContext().getResources().updateConfiguration(conf, getApplicationContext().getResources().getDisplayMetrics());
        conf.locale=myLocale;
        res.updateConfiguration(conf, getApplicationContext().getResources().getDisplayMetrics());
        Intent refresh=new Intent(this, MainActivity.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(refresh);
        /* Toast.makeText(getApplicationContext(), languagename, Toast.LENGTH_SHORT).show();*/

    }

    //endregion

    private void AllowAccess(final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child("Users").child(phone).exists())
                {
                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(MainActivity.this, "Please wait, you are already logged in...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Account with this " + phone + " number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}