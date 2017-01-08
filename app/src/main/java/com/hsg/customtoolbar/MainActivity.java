package com.hsg.customtoolbar;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CustomToolBar mBar;
    private Toolbar toolBar;
    private DrawerLayout mDrawer;
    private NavigationView mNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = ((DrawerLayout) findViewById(R.id.drawer));
        mNV = ((NavigationView) findViewById(R.id.nv));

        toolBar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolBar);

        mNV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            private MenuItem mPreMenuItem;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawer.closeDrawers();
                mPreMenuItem = item;
                return true;
            }
        });

        mBar = ((CustomToolBar) findViewById(R.id.ctb));
        mBar.setImageClickListener(new CustomToolBar.ImageClickListener() {
            @Override
            public void onLeftImageClick(View view) {
                mDrawer.openDrawer(GravityCompat.START);
            }

            @Override
            public void onRightImageClick(View view) {
                Toast.makeText(MainActivity.this, "点击了右边图片", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
