package br.com.vw.myvwautomotive.VWPollingApplication.services;

import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import br.com.vw.myvwautomotive.VWPollingApplication.interfaces.PollingInterface;
import br.com.vw.myvwautomotive.VWPollingApplication.threads.PollingThread;

/**
 * @author Roberto Mandolesi Vilas Boas - rvilas@br.ibm.com
 */
public class PollingService extends Service {

    private PollingThread mPollingThread;

    public PollingService() {
        super();
    }




    @Override
    public void onCreate() {
        super.onCreate();
        PollingInterface pollingInterface = new PollingInterface() {
            @Override
            public void pollingError(String error) {
                stopSelf();
            }
        };
        mPollingThread = new PollingThread(pollingInterface, this.getApplicationContext());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPollingThread.start();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        mPollingThread.start();
        return super.startForegroundService(service);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPollingThread.setToStop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
