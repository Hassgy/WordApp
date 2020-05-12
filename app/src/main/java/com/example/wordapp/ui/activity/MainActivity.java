package com.example.wordapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.wordapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomnavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomnavigationView = findViewById(R.id.bottomnavigationView);

        //JectPack组件中的Navigation组件
        // 获取NaviController，做Fragment的切换
        NavController controller = Navigation.findNavController(this,R.id.nav_container);
        NavigationUI.setupWithNavController(bottomnavigationView,controller);
    }
}
