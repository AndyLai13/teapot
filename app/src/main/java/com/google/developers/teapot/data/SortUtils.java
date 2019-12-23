package com.google.developers.teapot.data;

import androidx.room.RawQuery;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQueryBuilder;

public class SortUtils {

    public enum TeaSortBy {
        CAFFEINE, TYPE, DEFAULT
    }

    /**
     * A raw query at runtime to oder by column for getting all the teas sorted.
     * @see RawQuery
     *
     * @param sortBy a value from preferences to order by.
     * @return SimpleSQLiteQuery with a correct order by column name.
     */
    public static SimpleSQLiteQuery getAllQuery(TeaSortBy sortBy, Boolean showOnlyFavorites) {
        SupportSQLiteQueryBuilder queryBuilder = SupportSQLiteQueryBuilder
                .builder(DataTeaNames.TABLE_NAME)
                .orderBy(getSortColumn(sortBy));
        if (showOnlyFavorites) {
            queryBuilder.selection(DataTeaNames.COL_FAVORITE, new String[]{"1"});
        }
        return new SimpleSQLiteQuery(queryBuilder.create().getSql());
    }

    /**
     * Get a column name in from a preference value.
     */
    private static String getSortColumn(TeaSortBy value) {
        switch (value) {
            case CAFFEINE:
                return DataTeaNames.COL_CAFFEINE;
            case TYPE:
                return DataTeaNames.COL_TYPE;
            default:
                return DataTeaNames.COL_NAME;
        }
    }
}