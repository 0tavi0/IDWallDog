package com.jonasvieira.iddog.Presentations.Feed;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jonasvieira.iddog.Data.Feed.Feed;
import com.jonasvieira.iddog.Presentations.Feed.Adapter.TabAdapter;
import com.jonasvieira.iddog.R;
import com.jonasvieira.iddog.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, FeedContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    FeedPresenter feedPresenter;
    FeedFragment feedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);

        feedPresenter = new FeedPresenter(this, FeedActivity.this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.toolbarTitle));

        configureTabLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();
        feedPresenter.getTokenToRequestDogs();
    }

    private void configureTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.husky)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.labrador)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.hound)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.pug)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(tabAdapter);
        mViewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void populateRecyclerView(String breed, final Feed list) {
        switch (breed) {
            case Constants.HUSKY:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        feedFragment = (FeedFragment) mViewPager.getAdapter().instantiateItem(mViewPager, Constants.INT_HUSKY);
                        feedFragment.setAdapter(list.getList());
                    }
                });
                break;
            case Constants.LABRADOR:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        feedFragment = (FeedFragment) mViewPager.getAdapter().instantiateItem(mViewPager, Constants.INT_LABRADOR);
                        feedFragment.setAdapter(list.getList());
                    }
                });
                break;
            case Constants.HOUND:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        feedFragment = (FeedFragment) mViewPager.getAdapter().instantiateItem(mViewPager, Constants.INT_HOUND);
                        feedFragment.setAdapter(list.getList());
                    }
                });
                break;
            case Constants.PUG:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        feedFragment = (FeedFragment) mViewPager.getAdapter().instantiateItem(mViewPager, Constants.INT_PUG);
                        feedFragment.setAdapter(list.getList());
                    }
                });
                break;
        }
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSnackBarError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
