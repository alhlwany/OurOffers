package com.sbm.ouroffers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class GroupsActivity extends AppCompatActivity {
    private ImageView obj_img_clothes, obj_img_super, obj_img_restaurants, obj_img_libraries,
            obj_img_shoes, obj_img_games, obj_img_appliances, obj_img_spieces, obj_img_waterpurification,
            obj_img_maintenance, obj_img_clinc, obj_img_engineeringoffice, obj_img_beuty, obj_img_factory, obj_img_computer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        obj_img_clothes=(ImageView) findViewById(R.id.img_clothes);
        obj_img_super=(ImageView) findViewById(R.id.img_super);
        obj_img_restaurants=(ImageView) findViewById(R.id.img_restaurants);
        obj_img_libraries=(ImageView) findViewById(R.id.img_libraries);
        obj_img_shoes=(ImageView) findViewById(R.id.img_shoes);
        obj_img_games=(ImageView) findViewById(R.id.img_games);
        obj_img_appliances=(ImageView) findViewById(R.id.img_appliances);
        obj_img_spieces=(ImageView) findViewById(R.id.img_spieces);
        obj_img_waterpurification=(ImageView) findViewById(R.id.img_waterpurification);
        obj_img_maintenance=(ImageView) findViewById(R.id.img_maintenance);
        obj_img_clinc=(ImageView) findViewById(R.id.img_clinc);
        obj_img_engineeringoffice=(ImageView) findViewById(R.id.img_engineeringoffice);
        obj_img_beuty=(ImageView) findViewById(R.id.img_beuty);
        obj_img_factory=(ImageView) findViewById(R.id.img_factory);
        obj_img_computer=(ImageView) findViewById(R.id.img_computer);


    }
}