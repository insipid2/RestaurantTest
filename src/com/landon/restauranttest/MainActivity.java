package com.landon.restauranttest;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String TAG = "LANDON_" + MainActivity.class.getSimpleName();
    public static final boolean DEBUG = true;

    private int checkedRadioButtonId;
    private TextView memeText;
    private RadioButton rdBtn1;
    private RadioButton rdBtn2;
    private RadioButton rdBtn3;
    private Context mContext;

    // TODO: use content provider to store restaurants
    private static SparseArray<Restaurant> resArray;
    public static int resCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        init();
    }

    // Initiate TextView, RadioButtons, and Button
    private void init() {
        memeText = (TextView) findViewById(R.id.troll_meme_text);
        memeText.setTextColor(Color.WHITE);
        memeText.setTextSize(22);

        rdBtn1 = (RadioButton) findViewById(R.id.cheap_option);
        rdBtn1.setTextColor(Color.RED);
        rdBtn1.setTextSize(20);

        rdBtn2 = (RadioButton) findViewById(R.id.normal_option);
        rdBtn2.setTextColor(Color.RED);
        rdBtn2.setTextSize(20);

        rdBtn3 = (RadioButton) findViewById(R.id.expensive_option);
        rdBtn3.setTextColor(Color.RED);
        rdBtn3.setTextSize(20);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedRadioButtonId = checkedId;
            }
        });

        createResArray();

        Button button = (Button) findViewById(R.id.pickRes);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pickRestaurant(checkedRadioButtonId);
            }
        });
    }

    private void createResArray() {
        Restaurant pizzaHut = new Restaurant("Pizza Hut", PriceRange.CHEAP);
        Restaurant gyros = new Restaurant("Gyros", PriceRange.CHEAP);
        Restaurant sisters = new Restaurant("Sister's", PriceRange.CHEAP);

        Restaurant southGate = new Restaurant("South Gate", PriceRange.NORMAL);
        Restaurant mexican = new Restaurant("Mexican", PriceRange.NORMAL);
        Restaurant tmo = new Restaurant("Teriyaki And More", PriceRange.NORMAL);
        Restaurant taj = new Restaurant("Taj", PriceRange.NORMAL);
        Restaurant kungho = new Restaurant("Kungho", PriceRange.NORMAL);
        Restaurant thai = new Restaurant("Tasty Thai", PriceRange.NORMAL);
        Restaurant pho = new Restaurant("I Love Pho", PriceRange.NORMAL);

        Restaurant sushi = new Restaurant("Genki Sushi", PriceRange.EXPENSIVE);

        resArray = new SparseArray<Restaurant>();

        resArray.append(0, pizzaHut);
        resArray.append(1, gyros);
        resArray.append(2, sisters);

        resArray.append(3, southGate);
        resArray.append(4, mexican);
        resArray.append(5, tmo);
        resArray.append(6, taj);
        resArray.append(7, kungho);
        resArray.append(8, thai);
        resArray.append(9, pho);

        resArray.append(10, sushi);

        resCount = 11;
        
        if (DEBUG) Log.i(TAG, "Default restaurant list created. Count is: " + resCount);
    }

    // TODO: introduce AddRestaurant Activity to let users add new restaurants
    private void addRestaurant(String name, PriceRange price) {
        resArray.append(resCount, new Restaurant(name, price));
        resCount++;
        if (DEBUG) Log.i(TAG, "Added a restaurant " + name + " with price " + price.toString());
    }


    private void pickRestaurant(int checkedRadioButtonId) {
        Random r = new Random();
        int randomNumber = -1;

        switch (checkedRadioButtonId) {
        case R.id.cheap_option:
            randomNumber = r.nextInt(3);
            break;
        case R.id.normal_option:
            randomNumber = r.nextInt(6) + 3;
            break;
        case R.id.expensive_option:
            randomNumber = 10;
            break;
        }

        if (DEBUG) Log.i(TAG, "Random number to pick restaurant is: " + randomNumber);

        Toast toast = null;
        if (randomNumber < 0) {
            toast = Toast.makeText(mContext, "Please choose one of the options.",
                    Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(mContext, "We're going to "
                    + resArray.get(randomNumber) + " today!", Toast.LENGTH_LONG);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
