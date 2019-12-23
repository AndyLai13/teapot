package com.google.developers.teapot.data;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

/**
 * Room data access object.
 */
@Dao
public interface TeaDao {

    /**
     * Returns all data in table for Paging
     *
     * @param query a dynamic SQL query
     */
    @RawQuery(observedEntities = Tea.class)
    DataSource.Factory<Integer, Tea> getAll(SupportSQLiteQuery query);

    /**
     * Returns a Tea based on the tea name.
     *
     * @param name of a tea
     */
    @Query("SELECT * FROM 'tea' WHERE 'name' = :name")
    LiveData<Tea> getTea(String name);

    /**
     * Update tea if its favorite or not.
     *
     * @param name of a tea
     */
    @Query("UPDATE tea SET favorite='true' where name=:name")
    void updateFavorite(String name);

    /**
     * Returns a random Tea
     */
    @Query("SELECT * FROM 'tea' ORDER BY RANDOM() LIMIT 1")
    Tea getRandomTea();

    @Query("SELECT * FROM 'tea' WHERE 'id' = 1")
    LiveData<Tea> getRecentTea();

    @Insert
    void insert(Tea... tea);

    @Delete
    void delete(Tea tea);
}
