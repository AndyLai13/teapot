package com.google.developers.teapot.data;

/**
 * Database naming schema
 * more information {@see app/schemas/tea.json}
 */
public final class DataTeaNames {

    public static final String TABLE_NAME = "tea";

    /**
     * Column name for Tea id and used as primary
     */
    public static final String COL_ID = "id";

    /**
     * Column name for Tea name
     */
    public static final String COL_NAME = "name";

    /**
     * Column name for Tea type
     */
    public static final String COL_TYPE = "type";

    /**
     * Column name for Tea origin
     */
    public static final String COL_ORIGIN = "origin";

    /**
     * Column name for steep time Tea
     */
    public static final String COL_STEEP_TIME = "steep-time";

    /**
     * Column name for Tea description
     */
    public static final String COL_DESCRIPTION = "description";

    /**
     * Column name for Tea ingredients
     */
    public static final String COL_INGREDIENTS = "ingredients";

    /**
     * Column name for Tea caffeine level
     */
    public static final String COL_CAFFEINE = "caffeine";

    /**
     * Column name for Tea favorite
     */
    public static final String COL_FAVORITE = "favorite";
}
