package org.rsa.ecoshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TripActivityReal extends AppCompatActivity implements  View.OnClickListener {
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
        Intent intent = getIntent();
        System.out.println(intent.getStringExtra("key"));
        chicken.setText(intent.getStringExtra("key"));
        apple = findViewById(R.id.applecount2);
        apple.setText(intent.getStringExtra("apple"));
      //  updateCounters(intent.getIntExtra("chicken", 0), 0 );
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

        public void updateCounters(int chickenCount, int apples) {

      //  chicken.setText(chickenCount);
        }

}