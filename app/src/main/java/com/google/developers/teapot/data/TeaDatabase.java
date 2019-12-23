package com.google.developers.teapot.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.developers.teapot.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Database(entities = {Tea.class}, version = 1)
public abstract class TeaDatabase extends RoomDatabase {

    public abstract TeaDao teaDao();

    private static volatile TeaDatabase sInstance = null;

    /**
     * Returns an instance of Room Database
     *
     * @param context application context
     * @return The singleton TeaDatabase
     */
    @NonNull
    public static synchronized TeaDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance =  Room
                            .databaseBuilder(context, TeaDatabase.class, "TEA.db")
                            .build();
                    fillWithStartingData(context);
                }
            }
        }
        return sInstance;

    }

    /**
     * Load demo data into database.
     *
     * @param context to get raw data.
     */
    @WorkerThread
    private static void fillWithStartingData(Context context) {
        new Thread(() -> {
            TeaDao dao = getInstance(context).teaDao();
            JSONArray teas = loadJsonArray(context);
            try {

                for (int i = 0; i < teas.length(); i++) {
                    JSONObject tea = teas.getJSONObject(i);
                    dao.insert(new Tea(tea.getString("name"), tea.getString("type"),
                            tea.getString("origin"), tea.getLong("steep-time"),
                            tea.getString("description"), tea.getString("ingredients"),
                            tea.getString("caffeine-level")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static JSONArray loadJsonArray(Context context) {
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.sample_teas);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONObject json = new JSONObject(builder.toString());
            return json.getJSONArray("teas");

        } catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
