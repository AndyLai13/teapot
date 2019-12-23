package com.google.developers.teapot.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * A Model class that holds information about the tea.
 * This class defines table for the Room database with primary key {@see #mCode}
 */
@Entity
public class Tea {
    @PrimaryKey
    private Long mId;

    @ColumnInfo(name = "name")
    private final String mName;

    @ColumnInfo(name = "type")
    private final String mType;

    @ColumnInfo(name = "origin")
    private final String mOrigin;

    @ColumnInfo(name = "steep-time")
    private final Long mSteepTimeMs;

    @ColumnInfo(name = "description")
    private final String mDescription;

    @ColumnInfo(name = "ingredients")
    private final String mIngredients;

    @ColumnInfo(name = "caffeine")
    private final String mCaffeineLevel;

    @ColumnInfo(name = "favorite")
    private final Boolean mFavorite;

    @Ignore
    public Tea(@NonNull String name, @NonNull String type, String origin, Long steepTimeMs,
               String description, String ingredients, String caffeineLevel) {
        this(null, name, type, origin, steepTimeMs, description, ingredients, caffeineLevel, false);
    }

    public Tea(Long id, @NonNull String name, @NonNull String type, String origin, Long steepTimeMs,
               String description, String ingredients, String caffeineLevel,
               @NonNull Boolean favorite) {
        this.mId = id;
        this.mName = name;
        this.mType = type;
        this.mOrigin = origin;
        this.mSteepTimeMs = steepTimeMs;
        this.mDescription = description;
        this.mIngredients = ingredients;
        this.mCaffeineLevel = caffeineLevel;
        this.mFavorite = favorite;
    }

    @NonNull
    public Long getId() {
        return mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getType() {
        return mType;
    }

    @Nullable
    public String getOrigin() {
        return mOrigin;
    }

    public Long getSteepTimeMs() {
        return mSteepTimeMs;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getIngredients() {
        return mIngredients;
    }

    @Nullable
    public String getCaffeineLevel() {
        return mCaffeineLevel;
    }

    @NonNull
    public Boolean isFavorite() {
        return mFavorite;
    }

}
