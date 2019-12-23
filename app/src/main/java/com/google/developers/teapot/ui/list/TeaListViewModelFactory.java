package com.google.developers.teapot.ui.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.developers.teapot.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

public class TeaListViewModelFactory implements ViewModelProvider.Factory {

    private final DataRepository mRepository;
    private final String mSortBy;

    private TeaListViewModelFactory(DataRepository repository, String sortBy) {
        mRepository = repository;
        mSortBy = sortBy;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class, String.class)
                    .newInstance(mRepository, mSortBy);
        } catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }
}
