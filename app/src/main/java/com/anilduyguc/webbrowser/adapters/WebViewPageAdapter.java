package com.anilduyguc.webbrowser.adapters;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.anilduyguc.webbrowser.fragments.WebViewFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class WebViewPageAdapter extends FragmentStateAdapter {
    private ArrayList<String> arrayList;
    private String url;
    private HashMap<String, String> hashMap;
    public WebViewPageAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<String> arrayList, String url) {
        super(fragmentActivity);
        this.arrayList = arrayList;
        this.url = url;
        this.hashMap = new HashMap<>();
        Log.d("DEBUG", arrayList.toString());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", arrayList.get(position));
        bundle.putString("url", url);
        Log.d("CREATE-FRAGMENT", String.valueOf(position));
        String tag = fragment.getTag();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}

