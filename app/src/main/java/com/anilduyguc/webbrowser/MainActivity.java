package com.anilduyguc.webbrowser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.anilduyguc.webbrowser.adapters.WebViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private Button addButton;
    private WebViewPageAdapter webViewPageAdapter;
    private ArrayList<String> tabCounts;
    private ArrayList<String> titles;
    private ArrayList<Integer> icons;
    private Integer tabCount=1;
    private HashMap<String, String> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabCounts = new ArrayList<>();
        titles = new ArrayList<>();
        icons = new ArrayList<>();
        hashMap = new HashMap<>();
        addButton = findViewById(R.id.new_button);
        tabLayout = findViewById(R.id.tab_layout_view);
        viewPager2 = findViewById(R.id.view_pager);

        addTab(tabCount, "google.com");
        tabCount++;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTab(tabCount, "https://www.google.com");
                tabCount++;
                Log.d("TabCUNT", String.valueOf(tabCount));
            }
        });
    }
    private void addTab(Integer tabCount, String url) {
        tabCounts.add(String.valueOf(tabCount));
        titles.add("Tab-" + (tabCount));
        icons.add(R.drawable.ic_launcher_foreground);
        hashMap.put(String.valueOf(tabCount), url);
        viewPager2.setCurrentItem(tabCount -1);
        webViewPageAdapter = new WebViewPageAdapter(this, tabCounts, url);
        viewPager2.setAdapter(webViewPageAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
                tab.setIcon(icons.get(position));
            }
        }).attach();
    }

}