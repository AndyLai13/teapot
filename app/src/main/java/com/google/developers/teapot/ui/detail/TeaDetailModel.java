package com.google.developers.teapot.ui.detail;

import com.google.developers.teapot.R;

/**
 * Ui model for TeaDetailActivity.
 */
public class TeaDetailModel {

    private String mTeaType;

    public TeaDetailModel(String teaType) {
        mTeaType = teaType;
    }

    public int getImageId() {
        return getTeaImage(mTeaType);
    }

    /**
     * Supported background toolbar images
     *
     * @param type of tea e.g. "Black tea"
     * @return resource identifier for drawable
     */
    private int getTeaImage(String type) {
        switch (type) {
            case "Green Tea":
                return R.drawable.green_tea;
            case "Herbal Tea":
                return R.drawable.herbal_tea;
            default:
                return R.drawable.black_tea;
        }
    }
}
