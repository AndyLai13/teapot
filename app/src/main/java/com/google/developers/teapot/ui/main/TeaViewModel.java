package com.google.developers.teapot.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.developers.teapot.data.DataRepository;
import com.google.developers.teapot.data.Tea;
import com.google.developers.teapot.ui.detail.TeaDetailModel;

import java.util.Objects;

public class TeaViewModel extends ViewModel {

    private final DataRepository mRepository;
    private final LiveData<Tea> mTea;
    private final MutableLiveData<TeaDetailModel> mTeaDetail = new MutableLiveData<>();

    public TeaViewModel(DataRepository repository) {
        mRepository = repository;
        mTea = mRepository.getRecentTea();
    }

    public LiveData<Tea> getTea() {
        return mTea;
    }

    public void setTeaImage(String teaType) {
        mTeaDetail.setValue(new TeaDetailModel(teaType));
    }

    public LiveData<TeaDetailModel> getTeaImage() {
        return mTeaDetail;
    }

    public void setFavorite() {
        mRepository.updateFavorite(Objects.requireNonNull(mTea.getValue()).getName());
    }
}
