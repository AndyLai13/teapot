package com.google.developers.teapot.ui.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.developers.teapot.data.DataRepository;
import com.google.developers.teapot.data.Tea;

/**
 * ViewModel for the List of teas
 */
public class TeaListViewModel extends ViewModel {

    private final DataRepository mRepository;
    private final MutableLiveData<Boolean> mFavoriteFilter = new MutableLiveData<>();
    private final SingleLiveEvent<Tea> mUndoDelete = new SingleLiveEvent<>();

    public TeaListViewModel(DataRepository repository, String sortBy) {
        mFavoriteFilter.setValue(false);
        mRepository = repository;
    }

    /**
     * Show only show list of favorite teas.
     */
    public void showFavorites() {
        Boolean showFavorite = mFavoriteFilter.getValue();
        if (showFavorite == null) {
            return;
        }
        mFavoriteFilter.setValue(!showFavorite);
    }

    public void delete(Tea tea) {
        mRepository.delete(tea);
        mUndoDelete.setValue(tea);
    }

}
