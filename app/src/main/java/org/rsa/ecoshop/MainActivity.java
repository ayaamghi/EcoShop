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

import org.rsa.ecoshop.ml.ModelFinal;
import org.rsa.ecoshop.ml.ModelR;
import org.rsa.ecoshop.ml.Modelt;
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

    static int chickenImpact;
    static int appleImpact;
    static int porkImpact;
    static int tomatoImpact;
    static int potatoImpact;
    static int peasImpact;
    static int fishImpact;


    static int chickensNotBought;
    static int applesNotBought;
    static int porkNotBought;
    static int tomatoNotBought;
    static int potatoNotBought;
    static int peasNotBought;
    static int fishNotBought;

    static int notBoughtChickenImpact;
    static int notBoughtApplesImpact;
    static int notBoughtPorkImpact;
    static int notBoughtTomatoImpact;
    static int notBoughtPotatoImpact;
    static int notBoughtPeasImpact;
    static int notBoughtFishImpact;

    int maxPos;
    int[] impact = {6, 0, 7, 1, 0, 1, 5};
    ImageButton infoButton;
ImageButton greenHands;
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
                        counter(maxPos+9);
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
            case 7:
                chickensNotBought++;
                notBoughtChickenImpact = chickensNotBought*impact[position-9];
                break;
            case 8:
                applesNotBought++;
                notBoughtApplesImpact = applesNotBought*impact[position-10];
                break;
            case 9:
                porkNotBought++;
                notBoughtPorkImpact = porkNotBought*impact[position-11];
                break;
            case 10:
                tomatoNotBought++;
                notBoughtTomatoImpact = tomatoNotBought*impact[position-12];
                break;
            case 11:
                potatoNotBought++;
                notBoughtPotatoImpact = potatoNotBought*impact[position-13];
                break;
            case 12:
                peasNotBought++;
                notBoughtPeasImpact = peasNotBought*impact[position-14];
                break;
            case 13:
                fishNotBought++;
                notBoughtFishImpact = fishNotBought*impact[position-15];
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
            //here too its not working oops

            //iterates over entire array of floats-- prevents nullpointerexception when changing between models
            ArrayList<Float>  confidences = new ArrayList<>();
            for (float v : outputFeature0.getFloatArray()) {
                confidences.add(v);
            }

            //finds max pos
             maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.size(); i++) {
                if (confidences.get(i) > maxConfidence) {
                    maxConfidence = confidences.get(i);
                    maxPos = i;
                }
            }
            //add class labels depending on model
            classes.add("chicken");
            classes.add("apple");
            classes.add("pork");
            classes.add("tomatoes");
            classes.add("potatoes");
            classes.add("peas");
            classes.add("fish");
            classes.add("other");
            product.setText(classes.get(maxPos));


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



