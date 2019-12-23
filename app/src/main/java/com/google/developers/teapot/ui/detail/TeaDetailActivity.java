package com.google.developers.teapot.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.developers.teapot.R;
import com.google.developers.teapot.data.Tea;

import java.util.concurrent.TimeUnit;

/**
 * Activity that displays all the information about a particular Tea
 */
public class TeaDetailActivity extends AppCompatActivity {

    public static final String TEA_NAME = "TEA_NAME";
    private CollapsingToolbarLayout mCollapsingToolbar;
    private TeaDetailViewModel mTeaDetailViewModel;
    private ImageView mTeaImage;
    private TextView mDescription;
    private TextView mOrigin;
    private TextView mSteep;
    private TextView mIngredients;
    private TextView mTeaType;
    private TextView mCaffeine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String teaName = getIntent().getStringExtra(TEA_NAME);
        mCollapsingToolbar = findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TeaDetailViewModelFactory viewModelFactory = TeaDetailViewModelFactory
                .createFactory(this, teaName);
        mTeaDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TeaDetailViewModel.class);

        mTeaImage = findViewById(R.id.tea_image);
        mDescription = findViewById(R.id.description);
        mOrigin = findViewById(R.id.origin);
        mSteep = findViewById(R.id.steep_time);
        mIngredients = findViewById(R.id.ingredients);
        mTeaType = findViewById(R.id.tea_type);
        mCaffeine = findViewById(R.id.caffeine_level);

        mTeaDetailViewModel.getTeaImage().observe(this, teaDetail -> {
            mTeaImage.setImageDrawable(getResources()
                    .getDrawable(teaDetail.getImageId(), null));
        });
        mTeaDetailViewModel.getTea().observe(this, this::displayTea);
    }

    /**
     * Attach the Tea to the view.
     */
    private void displayTea(Tea tea) {
        if (tea == null) {
            return;
        }
        mCollapsingToolbar.setTitle(tea.getName());

        long steepMinute = tea.getSteepTimeMs();
        mTeaDetailViewModel.setTeaImage(tea.getType());
        mDescription.setText(tea.getDescription());
        mOrigin.setText(tea.getOrigin());
        mIngredients.setText(tea.getIngredients());
        mTeaType.setText(tea.getType());
        mSteep.setText(getString(R.string.steep_time_minutes,
                TimeUnit.MILLISECONDS.toMinutes(steepMinute)));
        mCaffeine.setText(getString(R.string.caffeine, tea.getCaffeineLevel()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}