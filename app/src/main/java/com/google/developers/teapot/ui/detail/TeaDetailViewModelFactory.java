package com.google.developers.teapot.ui.detail;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.developers.teapot.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

/**
 * Factory for creating a ViewModel
 */
public class TeaDetailViewModelFactory implements ViewModelProvider.Factory {

    private final String mTeaName;
    private final DataRepository mRepository;

    public static TeaDetailViewModelFactory createFactory(Activity activity, String name) {
        Context context = activity.getApplicationContext();
        if (context == null) {
            throw new IllegalStateException("Not yet attached to Application");
        }
        return new TeaDetailViewModelFactory(DataRepository.getInstance(context), name);
    }

    private TeaDetailViewModelFactory(DataRepository instance, String name) {
        mRepository = instance;
        mTeaName = name;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class, String.class)
                    .newInstance(mRepository, mTeaName);
        } catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }
}
