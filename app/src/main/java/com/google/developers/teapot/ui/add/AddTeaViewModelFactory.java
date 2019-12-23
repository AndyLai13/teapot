package com.google.developers.teapot.ui.add;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.developers.teapot.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

public class AddTeaViewModelFactory implements ViewModelProvider.Factory {

    private final DataRepository mRepository;

    public static AddTeaViewModelFactory createFactory(Activity activity) {
        Context context = activity.getApplicationContext();
        if (context == null) {
            throw new IllegalStateException("Not yet attached to Application");
        }

        return new AddTeaViewModelFactory(DataRepository.getInstance(context));
    }

    private AddTeaViewModelFactory(DataRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class).newInstance(mRepository);
        } catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }
}
