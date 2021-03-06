package com.exfume.luengo.universalcipher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.exfume.luengo.universalcipher.adapter.CipherAdapter;
import com.exfume.luengo.universalcipher.fragment.affine_cipher;
import com.exfume.luengo.universalcipher.fragment.affine_decipher;
import com.exfume.luengo.universalcipher.fragment.vigenere_cipher;
import com.exfume.luengo.universalcipher.fragment.vigenere_decipher;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private CipherAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPagerAdapter = new CipherAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.main_pager);
        mViewPager.setAdapter(mPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setDrawer(navigationView);
            selectItem(navigationView.getMenu().findItem(R.id.Vigenere));
        }

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }

    }

    private void setDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        selectItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void selectItem(MenuItem itemDrawer) {

        switch (itemDrawer.getItemId()) {
            case R.id.Vigenere:
                mPagerAdapter.addCipher(new vigenere_cipher(),new vigenere_decipher() );
                break;
            case R.id.Affine:
                mPagerAdapter.addCipher(new affine_cipher(), new affine_decipher());
                break;

            case R.id.nav_keys:
                Intent intent = new Intent(this, KeyManagementActivity.class);
                startActivity(intent);
                break;

        }
        mPagerAdapter.notifyDataSetChanged();
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
