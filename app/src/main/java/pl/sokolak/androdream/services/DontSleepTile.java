package pl.sokolak.androdream.services;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import androidx.annotation.RequiresApi;

import pl.sokolak.androdream.Globals;
import pl.androdream.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class DontSleepTile extends TileService {

    PowerManager.WakeLock wakeLock;
    PowerManager pm;

    @Override
    public void onTileAdded() {
        super.onTileAdded();

        Tile tile = getQsTile();
        tile.setState(Tile.STATE_INACTIVE);
        tile.setLabel(getResources().getString(R.string.tile_label));
        tile.updateTile();
    }

    @Override
    public void onClick() {
        super.onClick();
        Tile tile = getQsTile();
        if (tile.getState() == Tile.STATE_INACTIVE) {
            acquireWakeLock();
            tile.setState(Tile.STATE_ACTIVE);
        } else if (tile.getState() == Tile.STATE_ACTIVE) {
            if (Globals.wakelock != null) {
                Globals.wakelock.release();
                Globals.wakelock = null;
            }
            tile.setState(Tile.STATE_INACTIVE);
        }
        tile.updateTile();
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    public void acquireWakeLock() {
        if (Globals.wakelock != null) {
            Globals.wakelock.release();
            Globals.wakelock = null;
        }
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE,
                "myapp::==KeepScreenOn==");
        wakeLock.acquire();
        Globals.wakelock = this.wakeLock;
    }
}
