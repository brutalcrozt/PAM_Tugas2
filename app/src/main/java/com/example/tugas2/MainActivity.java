package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugas2.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

//  private ImageView heroImage;
//  private TextView heroDesc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
              .replace(R.id.container, MainFragment.newInstance())
              .commitNow();
    }
//    heroImage= findViewById(R.id.imageView3);
//    heroDesc= findViewById(R.id.hero_desc);
  }

}
