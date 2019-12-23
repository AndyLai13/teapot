package com.google.developers.teapot.notification;

import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.developers.teapot.data.DataRepository;

public class NotificationWorker extends Worker {

    private static final int NOTIFICATION_ID = 22;
    private static final String CHANNEL_ID = "notify-tea";

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        NotificationManager notificationManager = getApplicationContext()
                .getSystemService(NotificationManager.class);
        DataRepository repository = DataRepository.getInstance(getApplicationContext());

        return Result.failure();
    }
}
