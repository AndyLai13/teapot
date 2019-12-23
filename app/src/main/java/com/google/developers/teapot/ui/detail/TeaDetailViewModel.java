package com.google.developers.teapot.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.developers.teapot.data.DataRepository;
import com.google.developers.teapot.data.Tea;

/**
 * ViewModel for Detail activity and steep timer activity.
 */
public class TeaDetailViewModel extends ViewModel {

    private final DataRepository mRepository;
    private final String mTeaName;
    private final LiveData<Tea> mTea;
    private final MutableLiveData<TeaDetailModel> mTeaDetail = new MutableLiveData<>();

    public TeaDetailViewModel(DataRepository dataRepository, String teaName) {
        mRepository = dataRepository;
        mTeaName = teaName;
        mTea = mRepository.getTea(teaName);
    }

    public LiveData<Tea> getTea() {
        return mTea;
    }

    public void setFavorite() {
        mRepository.updateFavorite(mTeaName);
    }

    public void setTeaImage(String teaType) {
        mTeaDetail.setValue(new TeaDetailModel(teaType));
    }

    public LiveData<TeaDetailModel> getTeaImage() {
        return mTeaDetail;
    }

}
