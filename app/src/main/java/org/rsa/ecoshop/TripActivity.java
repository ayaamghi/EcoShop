package org.rsa.ecoshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

public class TripActivity extends AppCompatActivity implements  View.OnClickListener {
Button back;
 TextView chicken;
 TextView apple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_real);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        chicken = findViewById(R.id.chickencount);
        chicken.setText(MainActivity.chickenCounter +" bought with a total CO2 emission of " + MainActivity.chickenImpact);

        apple = findViewById(R.id.applecount2);
        apple.setText(MainActivity.appleCounter +" bought with a total CO2 emission of " + MainActivity.appleImpact);

        TextView notBoughtApple = findViewById(R.id.textView3);
        notBoughtApple.setText(MainActivity.applesNotBought +" not bought with a total Co2 saving of " + MainActivity.notBoughtApplesImpact);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        }


}