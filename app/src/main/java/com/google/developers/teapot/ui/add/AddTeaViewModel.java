package com.google.developers.teapot.ui.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.developers.teapot.data.DataRepository;
import com.google.developers.teapot.data.Tea;

import java.util.concurrent.TimeUnit;

public class AddTeaViewModel extends ViewModel {

    private final DataRepository mRepository;
    private final MutableLiveData<Boolean> mTeaSaved = new MutableLiveData<>();

    public AddTeaViewModel(DataRepository repository) {
        mRepository = repository;
    }

    public LiveData<Boolean> isSaved() {
        return mTeaSaved;
    }

    public void save(Tea tea) {
        mRepository.insert(tea);
    }

    public void save(String name, String type, String origin, String steepTime, String description,
                     String ingredients, String caffeine) {
        if (name.isEmpty()) {
            mTeaSaved.setValue(false);
            return;
        }
        Long steepMs = parseLong(steepTime);
        steepMs = TimeUnit.MINUTES.toMillis(steepMs);
        Tea tea = new Tea(name, type, origin, steepMs, description, ingredients, caffeine);
        save(tea);
        mTeaSaved.setValue(true);
    }

    /**
     * If string does not have integers then return 0.
     *
     * @param string to parse are int
     * @return a parse int or default value 0
     */
    private Long parseLong(String string) {
        return string.matches("\\d+") ? Long.parseLong(string) : 0L;
    }
}
