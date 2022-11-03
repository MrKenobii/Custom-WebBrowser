package com.anilduyguc.webbrowser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.anilduyguc.webbrowser.adapters.WebViewPageAdapter;
import com.anilduyguc.webbrowser.fragments.WebViewFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private EditText editText;
    private Button goButton;
    private Button addButton;
    private WebViewPageAdapter webViewPageAdapter;
    private ArrayList<String> tabCounts;
    private ArrayList<String> titles;
    private ArrayList<Integer> icons;
    private Integer tabCount=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabCounts = new ArrayList<>();
        titles = new ArrayList<>();
        icons = new ArrayList<>();
        addButton = findViewById(R.id.new_button);
        goButton = findViewById(R.id.go_button);
        editText = findViewById(R.id.edit_text_main);
        tabLayout = findViewById(R.id.tab_layout_view);
        viewPager2 = findViewById(R.id.view_pager);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTab(tabCount, "https://www.google.com");
                tabCount++;
                Log.d("TabCOUNT", String.valueOf(tabCount));
            }
        });
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                Log.d("URL-Main", url);
                go(url);
            }
        });
//        for(int i=0; i<tabCount; i++){
//            arrayList1.add(String.valueOf(i));
//            arrayList2.add("Tab-" + (i+1));
//            arrayList3.add(R.drawable.ic_launcher_foreground);
//        }


//        webViewPageAdapter = new WebViewPageAdapter(this, arrayList1);
//        viewPager2.setAdapter(webViewPageAdapter);
//        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText(arrayList2.get(position));
//                tab.setIcon(arrayList3.get(position));
//            }
//        }).attach();
    }
    private void go(String url){
        webViewPageAdapter=new WebViewPageAdapter(this, tabCounts, url);
        viewPager2.setAdapter(webViewPageAdapter);
    }

    private  void addTab(Integer tabCount, String url) {
        tabCounts.add(String.valueOf(tabCount));
        titles.add("Tab-" + (tabCount));
        icons.add(R.drawable.ic_launcher_foreground);
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