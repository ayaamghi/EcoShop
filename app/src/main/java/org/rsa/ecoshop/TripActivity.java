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

        chicken = findViewById(R.id.chickens_Bought12);
        chicken.setText(MainActivity.chickenCounter +" bought CO2 emission of " + MainActivity.chickenImpact);

        apple = findViewById(R.id.apples_Bought2);
        apple.setText(MainActivity.appleCounter +"  bought, CO2 emission of  " + MainActivity.appleImpact);

        TextView pork = findViewById(R.id.pork_bought2);
        pork.setText(MainActivity.porkCounter +"  bought, CO2 emission of  " + MainActivity.porkImpact);

        TextView tomatos = findViewById(R.id.tomatoes_bought);
        tomatos.setText(MainActivity.tomatoCounter +"  bought, CO2 emission of  " + MainActivity.tomatoImpact);

        TextView potat = findViewById(R.id.potatoes_bought2);
        potat.setText(MainActivity.potatoCounter +"  bought, CO2 emission of  " + MainActivity.potatoImpact);

        TextView peas = findViewById(R.id.peas_bought2);
        peas.setText(MainActivity.peasCounter +"  bought, CO2 emission of  " + MainActivity.peasImpact);

        TextView fish = findViewById(R.id.fish_bought2);
        fish.setText(MainActivity.fishCounter +"  bought, CO2 emission of  " + MainActivity.fishImpact);

        TextView beef = findViewById(R.id.beef_bought2);
        beef.setText(MainActivity.beefCounter +"  bought, CO2 emission of  " + MainActivity.beefImpact);

        TextView milk = findViewById(R.id.milk_bought2);
        milk.setText(MainActivity.milkCounter +"  bought, CO2 emission of  " + MainActivity.milkImpact);

        TextView  shrimp = findViewById(R.id.shrimp_bought2);
        shrimp.setText(MainActivity.shrimpCounter +"  bought, CO2 emission of  " + MainActivity.shrimpImpact);


        TextView notBoughtApple = findViewById(R.id.apples_NotBought);
        notBoughtApple.setText(MainActivity.applesNotBought +" not bought, Co2 saving of " + MainActivity.notBoughtApplesImpact);

        TextView notPork = findViewById(R.id.pork_Notbought2);
        notPork.setText(MainActivity.porkNotBought +" not bought, Co2 saving of " + MainActivity.notBoughtPorkImpact);

        TextView notTomato = findViewById(R.id.tomatoes_Notbought);
        notTomato.setText(MainActivity.tomatoNotBought +" not bought, Co2 saving of " + MainActivity.notBoughtTomatoImpact);

        TextView notPot = findViewById(R.id.potatoes_Notbought2);
        notPot.setText(MainActivity.potatoNotBought +" not bought, Co2 saving of " + MainActivity.notBoughtPotatoImpact);

        TextView notPeas = findViewById(R.id.peasNot_bought2);
        notPeas.setText(MainActivity.peasNotBought +" not bought, Co2 saving of " + MainActivity.notBoughtPeasImpact);

        TextView notFish = findViewById(R.id.fish_Notbought2);
        notFish.setText(MainActivity.fishNotBought +" not bought, Co2 saving of " + MainActivity.notBoughtFishImpact);

        TextView notBeef = findViewById(R.id.beef_Notbought2);
        notBeef.setText(MainActivity.beefNotBought +" not bought, Co2 saving of " + MainActivity.notBoughtBeefImpact);

        TextView notMilk = findViewById(R.id.milk_Notbought);
        notMilk.setText(MainActivity.milkNotBought +" not bought, Co2 saving of " + MainActivity.notBoughtMilkImpact);

        TextView  notShrimp = findViewById(R.id.shrimp_Notbought2);
        notShrimp.setText(MainActivity.shrimpNotBought +" not bought, Co2 saving of " + MainActivity.notBoughtShrimpImpact);
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