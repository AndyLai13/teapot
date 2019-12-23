package com.google.developers.teapot.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.developers.teapot.R;
import com.google.developers.teapot.data.DataRepository;
import com.google.developers.teapot.data.Tea;
import com.google.developers.teapot.paging.TeaAdapter;
import com.google.developers.teapot.paging.TeaViewHolder;
import com.google.developers.teapot.ui.SettingActivity;
import com.google.developers.teapot.ui.add.AddTeaActivity;

public class TeaListActivity extends AppCompatActivity {

    private TeaListViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    TeaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_tea_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);

        mViewModel = new TeaListViewModel(DataRepository.getInstance(this), "");


        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTeaActivity.class);
            startActivity(intent);
        });


        LiveData<PagedList<Tea>> teaList =  DataRepository.getInstance(this).getSortedTeas("DEFAULT", false);

        adapter = new TeaAdapter(teaList);
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    public void initAction() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                        @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Tea tea = ((TeaViewHolder) viewHolder).getTea();
                mViewModel.delete(tea);
            }
        });

        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                mViewModel.showFavorites();
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

