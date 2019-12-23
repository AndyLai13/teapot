package com.google.developers.teapot.data;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Handles data sources and execute on the correct thread.
 */
public class DataRepository {

    private final TeaDao mDao;
    private final ExecutorService mIoExecutor;
    private static volatile DataRepository sInstance = null;
    private final static int PAGE_SIZE = 20;

    public static DataRepository getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    TeaDatabase database = TeaDatabase.getInstance(context);
                    sInstance = new DataRepository(database.teaDao(),
                            Executors.newSingleThreadExecutor());
                }
            }
        }
        return sInstance;
    }

    private DataRepository(TeaDao dao, ExecutorService executor) {
        mIoExecutor = executor;
        mDao = dao;
    }

    public LiveData<PagedList<Tea>> getSortedTeas(String sort, Boolean fileByFavorite) {
        SortUtils.TeaSortBy sortBy = SortUtils.TeaSortBy.valueOf(sort);
        DataSource.Factory<Integer, Tea> factory =
                mDao.getAll(SortUtils.getAllQuery(sortBy, fileByFavorite));
        return new LivePagedListBuilder<>(factory, PAGE_SIZE)
                .build();
    }

    public void insert(Tea tea) {
        mIoExecutor.execute(() -> mDao.insert(tea));
    }

    public void delete(Tea tea) {
        mIoExecutor.execute(() -> mDao.delete(tea));
    }

    public LiveData<Tea> getTea(String name) {
        return null;
    }

    public void updateFavorite(String name) {


    }

    public LiveData<Tea> getRecentTea() {
        return null;
    }

    @Nullable
    public Tea getRandomTea() {
        try {
            return mIoExecutor.submit(mDao::getRandomTea).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

}
