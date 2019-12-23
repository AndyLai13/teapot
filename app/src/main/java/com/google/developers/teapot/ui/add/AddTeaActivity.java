package com.google.developers.teapot.ui.add;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.developers.teapot.R;

/**
 * Save a Tea to local data source.
 */
public class AddTeaActivity extends AppCompatActivity {

    private AddTeaViewModel mAddTeaViewModel;
    private Spinner mTeaType;
    private TextInputEditText mTeaName;
    private TextInputEditText mDescription;
    private TextInputEditText mOrigin;
    private TextInputEditText mIngredients;
    private TextInputEditText mSteep;
    private TextInputEditText mCaffeine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tea);

        AddTeaViewModelFactory teaViewModelFactory = AddTeaViewModelFactory.createFactory(this);
        mAddTeaViewModel = ViewModelProviders.of(this, teaViewModelFactory)
                .get(AddTeaViewModel.class);

        mTeaType = findViewById(R.id.tea_type);
        mTeaName = findViewById(R.id.tea_name);
        mDescription = findViewById(R.id.description);
        mOrigin = findViewById(R.id.origin);
        mIngredients = findViewById(R.id.ingredients);
        mSteep = findViewById(R.id.steep);
        mCaffeine = findViewById(R.id.caffeine);

        mAddTeaViewModel.isSaved().observe(this, this::hasSaved);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            saveTea();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Save all fields.
     */
    private void saveTea() {
        String[] teaTypes = getResources().getStringArray(R.array.tea_types);
        String type = teaTypes[(int) mTeaType.getSelectedItemId()];
        String name = mTeaName.getText().toString().trim();
        String description = mDescription.getText().toString();
        String origin = mOrigin.getText().toString();
        String ingredients = mIngredients.getText().toString();
        String steepTime = mSteep.getText().toString();
        String caffeine = mCaffeine.getText().toString();

        mAddTeaViewModel.save(name, type, origin, steepTime, description, ingredients, caffeine);
    }

    /**
     * If input has no errors and Tea is saved close activity.
     *
     * @param saved if TRUE Tea is saved and activity should be closed if FALSE
     *              show an error.
     */
    private void hasSaved(Boolean saved) {
        if (!saved) {
            mTeaName.setError(getString(R.string.required_text_tea_name));
            return;
        }
        finish();
    }
}
