package com.example.tugas2.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tugas2.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class MainFragment extends Fragment  implements View.OnClickListener {
  private MainViewModel mViewModel;
  private ImageView heroImage;
  private TextView heroDesc, tittle;
  private JsonObject jsonTree;
  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    final View inflartedView =inflater.inflate(R.layout.main_fragment, container, false);
    heroImage= inflartedView.findViewById(R.id.imageView3);
    heroDesc= inflartedView.findViewById(R.id.hero_desc);
    tittle= inflartedView.findViewById(R.id.title);
    inflartedView.findViewById(R.id.button).setOnClickListener(this);
    inflartedView.findViewById(R.id.button2).setOnClickListener(this);
    inflartedView.findViewById(R.id.button3).setOnClickListener(this);
    Switch mySwitch= inflartedView.findViewById(R.id.switch1);
    mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        Color prevColor= tittle.getTextColors();
        if (isChecked) {
          inflartedView.setBackgroundColor(Color.rgb(0, 0, 0));
          heroDesc.setTextColor(Color.WHITE);
          tittle.setTextColor(Color.WHITE);
        } else {
          inflartedView.setBackgroundColor(Color.rgb(255, 255, 255));
          tittle.setTextColor(Color.BLACK);
          heroDesc.setTextColor(Color.BLACK);
        }
      }
    });
    InputStream is = getResources().openRawResource(R.raw.dota);
    Writer writer= new StringWriter(); char [] buffer= new char[1024];
    try {
      Reader reader= new BufferedReader(new InputStreamReader(is, "UTF-8"));
      int n;
      while ((n= reader.read(buffer)) != -1) {
        writer.write(buffer, 0, n);
      }
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String json= writer.toString();
    JsonParser parser= new JsonParser();
    jsonTree= parser.parse(json).getAsJsonObject();
    return inflartedView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    // TODO: Use the ViewModel
  }


  @Override
  public void onClick(View v) {
    Button clicked= (Button) v;
    switch (clicked.getText().toString()) {
      case "Strength":
        heroImage.setImageResource(R.drawable.lc);
        heroDesc.setText(jsonTree.get("str").getAsString());
        break;
      case "Aglity":
        heroImage.setImageResource(R.drawable.sf);
        heroDesc.setText(jsonTree.get("agi").getAsString());
        break;
      case "Intelligence":
        heroImage.setImageResource(R.drawable.aa);
        heroDesc.setText(jsonTree.get("int").getAsString());
        break;
      default:
        heroDesc.setText("default "+ clicked.getText().toString());
    }
  }

}
