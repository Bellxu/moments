package com.dajj.moment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int TAKE_PHOTO = 1;
    private Uri imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View take_photo = findViewById(R.id.take_photo);
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
//                try {
//                    if (outputImage.exists()) {
//                        outputImage.delete();
//                    }
//                    outputImage.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                if (Build.VERSION.SDK_INT >= 24) {
//                     imageUrl = FileProvider.getUriForFile(TakePhotoActivity.this, "com.example.cameraabumtest.fileprovider", outputImage);
//                }else {
//                    imageUrl= Uri.fromFile(outputImage);
//                }
                //启动相机程序
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, TAKE_PHOTO);
                }
            }
        });
    }
}