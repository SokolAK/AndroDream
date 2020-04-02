package com.dream.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.dream.MainActivity;
import com.dream.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class InsomniaTile extends TileService {

    public static PowerManager.WakeLock wl;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Tile tile = getQsTile();
        tile.setState(Tile.STATE_INACTIVE);
        tile.updateTile();
        return START_STICKY;
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();

        Tile tile = getQsTile();
        tile.setState(Tile.STATE_INACTIVE);
        tile.setLabel(getResources().getString(R.string.insomnia_tile_label));
        tile.updateTile();
    }

    @Override
    public void onClick() {
        super.onClick();

        Tile tile = getQsTile();
        if (tile.getState() == Tile.STATE_INACTIVE) {
            PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            wl = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
                    "MyApp::MyWakelockTag");
            wl.acquire();


            tile.setState(Tile.STATE_ACTIVE);

        } else if (tile.getState() == Tile.STATE_ACTIVE) {
            if (wl != null) {
                if (wl.isHeld()) {
                    wl.release();
                }
            }
            tile.setState(Tile.STATE_INACTIVE);
        }

        tile.updateTile();
    }


    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

}
