package org.rsa.ecoshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.rsa.ecoshop.ml.ModelR;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bCapture;
    ImageView imageView;
    int imageSize = 224;
    ArrayList<String> classes = new ArrayList<>();
    ImageButton tripSummary;
    Button addList;
    Button dontAdd;
    TextView product;
    static int chickenCounter;
    static int appleCounter;
    static int porkCounter;
    static int tomatoCounter;
    static int potatoCounter;
    static int peasCounter;
    static int fishCounter;

    static double chickenImpact;
    static double appleImpact;
    static double porkImpact;
    static double tomatoImpact;
    static double potatoImpact;
    static double peasImpact;
    static double fishImpact;


    static int chickensNotBought;
    static int applesNotBought;
    static int porkNotBought;
    static int tomatoNotBought;
    static int potatoNotBought;
    static int peasNotBought;
    static int fishNotBought;

    static double notBoughtChickenImpact;
    static double notBoughtApplesImpact;
    static double notBoughtPorkImpact;
    static double notBoughtTomatoImpact;
    static double notBoughtPotatoImpact;
    static double notBoughtPeasImpact;
    static double notBoughtFishImpact;

    int maxPos;
    ImageButton infoButton;
ImageButton greenHands;
    double[] impact = {7.2, .3, 6.1, 1.4, .8, 5.1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCapture = findViewById(R.id.bCapture);
        bCapture.setOnClickListener(this);

        bCapture.setText("Click to Scan");

        imageView = findViewById(R.id.viewer);


        tripSummary = findViewById(R.id.Trip_list);
        tripSummary.setOnClickListener(this);

        addList = findViewById(R.id.addList);
        addList.setOnClickListener(this);

        dontAdd = findViewById(R.id.dontAdd);
        dontAdd.setOnClickListener(this);

        product = findViewById(R.id.Product);

        infoButton = findViewById(R.id.help);
        infoButton.setOnClickListener(this);

        greenHands = findViewById(R.id.greenhands);
        greenHands.setOnClickListener(this);
    }

        //on click listeners
        @Override
        public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.Trip_list:
                        startActivity(new Intent(this, TripActivity.class));
                        break;
                    case R.id.bCapture:
                        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 1);
                            addList.setVisibility(View.VISIBLE);
                            dontAdd.setVisibility(View.VISIBLE);
                            product.setVisibility(View.VISIBLE);
                        } else {
                            //Request camera permission if we don't have it.
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                        }
                    break;
                    case R.id.addList:
                        counter(maxPos);
                        addList.setVisibility(View.INVISIBLE);
                        dontAdd.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.dontAdd:
                        notCounter(maxPos);
                        addList.setVisibility(View.INVISIBLE);
                        dontAdd.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.help:
                        startActivity(new Intent(this, Info.class));
                        break;
                    case R.id.greenhands:
                        startActivity(new Intent(this, GreenHands.class));
                        break;
                }
        }
    //increments how many of each product has been on shopping list

    //i added the below part idk if it works tho -- idea is that something comes up if default in switch below below
public void notCounter(int position){
    switch(position){
    case 0:
    chickensNotBought++;
    notBoughtChickenImpact = chickensNotBought*impact[position];
    break;
    case 1:
    applesNotBought++;
    notBoughtApplesImpact = applesNotBought*impact[position];
    break;
    case 2:
    porkNotBought++;
    notBoughtPorkImpact = porkNotBought*impact[position];
    break;
    case 3:
    tomatoNotBought++;
    notBoughtTomatoImpact = tomatoNotBought*impact[position];
    break;
    case 4:
    potatoNotBought++;
    notBoughtPotatoImpact = potatoNotBought*impact[position];
    break;
    case 5:
    peasNotBought++;
    notBoughtPeasImpact = peasNotBought*impact[position];
    break;
    case 6:
    fishNotBought++;
    notBoughtFishImpact = fishNotBought*impact[position];
    break;
}
}
    public void counter(int position) {

        switch(position) {
            case 0:
                chickenCounter++;
                chickenImpact = chickenCounter*impact[position];
                break;
            case 1:
                appleCounter++;
                appleImpact = appleCounter * impact[position];
                break;
            case 2:
                porkCounter++;
                porkImpact = porkCounter * impact[position];
                break;
            case 3:
                tomatoCounter++;
                tomatoImpact = tomatoCounter * impact[position];
                break;
            case 4:
                potatoCounter++;
                potatoImpact = potatoCounter * impact[position];
                break;
            case 5:
                peasCounter++;
                peasImpact = peasCounter * impact[position];
                break;
            case 6:
                fishCounter++;
                fishImpact = fishCounter * impact[position];
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);
            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
             classifyImage(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void classifyImage(Bitmap image) {
        try {
            ModelR model = ModelR.newInstance(getApplicationContext()); //Modelt is name of specific file with .tflite extension that contains model
            //grr i cant figure this out its fine pls help
            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            // get 1D array of 224 * 224 pixels in image
            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            // iterate over pixels and extract R, G, and B values. Add to bytebuffer.
            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            ModelR.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            //iterates over entire array of doubles-- prevents nullpointerexception when changing between models
            ArrayList<Float>  confidences = new ArrayList<>();
            for (float v : outputFeature0.getFloatArray()) {
                confidences.add(v);
            }

            //finds max pos
            double maxConfidence = 0;
            for (int i = 0; i < confidences.size(); i++) {
                if (confidences.get(i) > maxConfidence) {
                    maxConfidence = confidences.get(i);
                    maxPos = i;
                }
            }

            //add class labels depending on model
            classes.add("Pork");
            classes.add("Apple");
            classes.add("Chicken");
            classes.add("Tomatoes");
            classes.add("Potatoes");
            classes.add("Peas");
            classes.add("Fish");
if(maxConfidence > .5) {
    product.setText(classes.get(maxPos));
}
else {
        product.setText("Please rescan!");
        addList.setVisibility(View.INVISIBLE);
        dontAdd.setVisibility(View.INVISIBLE);
}

            //prints out to system confidences- for debugging purposes
            String s = "";
         try {
             for (int i = 0; i < confidences.size(); i++) {
                 s += String.format("%s: %.1f%%\n", classes.get(i), confidences.get(i) * 100);
             }
             System.out.println(s);
         }
         catch(Exception e) {
            System.out.println("Something Went Wrong! Try again");
         }

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {

        }
    }

}



