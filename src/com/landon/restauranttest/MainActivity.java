package com.landon.restauranttest;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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
    // private static final String TAG = "RESTAURANT_TEST";

    private int checkedRadioButtonId;
    private TextView memeText;
    private RadioButton rdBtn1;
    private RadioButton rdBtn2;
    private RadioButton rdBtn3;
    private Context mContext;

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

        createRestaurant();

        Button button = (Button) findViewById(R.id.start);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pickRestaurant(checkedRadioButtonId);
            }
        });
    }

    private void createRestaurant() {
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
    }

    /*private void createResArray() {

    }*/

    private void pickRestaurant(int checkedRadioButtonId) {
        Random r = new Random();
        int randomNumber = -1;

        SparseArray<String> array = new SparseArray<String>();

        array.append(0, "Pizza Hut");
        array.append(1, "Gyros");
        array.append(2, "Sisters");

        array.append(3, "South Gate");
        array.append(4, "Mexican");
        array.append(5, "TMO");
        array.append(6, "Taj");
        array.append(7, "Kungho");
        array.append(8, "Thai");
        array.append(9, "Pho");

        array.append(10, "Sushi");

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

        Toast toast = null;
        if (randomNumber < 0) {
            toast = Toast.makeText(mContext, "Please choose one of the options.",
                    Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(mContext, "We're going to "
                    + array.get(randomNumber) + " today!", Toast.LENGTH_LONG);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
