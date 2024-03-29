package com.example.cinema;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cinema.adapters.ViewPagerAdapter;
import com.example.cinema.adapters.ViewPagerChiTietAdapter;
import com.example.cinema.ui.MovieDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ChiTietPhim extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;

    private ImageView MovieThumbnailImg;
    private WebView MovieCoverTrailer;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phim);

        // Tao nut Back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Chi tiết phim");

        tabLayout=(TabLayout)findViewById(R.id.tabChiTiet);
        viewPager=(ViewPager2)findViewById(R.id.vpChiTiet);
        viewPager.setAdapter(createCardAdapter());

        // ini views
        iniViews();
        play_fab=(FloatingActionButton)findViewById(R.id.play_fab);
        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChiTietPhim.this, XemTrailer.class);
                startActivity(intent);
            }
        });

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Lịch Chiếu");
                        break;
                    case 1:
                        tab.setText("Thông Tin");
                        break;
                    case 2:
                        tab.setText("Bình Luận");
                        break;
                }
            }
        }).attach();
    }
    private ViewPagerChiTietAdapter createCardAdapter() {
        ViewPagerChiTietAdapter adapter = new  ViewPagerChiTietAdapter(this);
        return adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    // Xu ly tung item trong Context Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void iniViews() {
        String movieTitle = getIntent().getExtras().getString("title");
        String imageResourceId = getIntent().getExtras().getString("imgURL");
        String trailer= getIntent().getExtras().getString("trailer");
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
       // Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        Picasso.get().load(imageResourceId).placeholder(R.drawable.slide1).networkPolicy(NetworkPolicy.OFFLINE).into(MovieThumbnailImg);
        MovieCoverTrailer = findViewById(R.id.detail_movie_cover);
        MovieCoverTrailer.getSettings().setJavaScriptEnabled(true);
        MovieCoverTrailer.setWebChromeClient(new WebChromeClient());
        MovieCoverTrailer.loadData(trailer, "text/html", "utf-8");

        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(R.id.detail_movie_desc); // chưa có

        sharedPreferences= getSharedPreferences("ChonGhePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("ten_phim", movieTitle);
        editor.commit();
    }
}