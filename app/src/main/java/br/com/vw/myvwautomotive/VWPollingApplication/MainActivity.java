package br.com.vw.myvwautomotive.VWPollingApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import br.com.vw.myvwautomotive.VWPollingApplication.services.PollingService;

/**
 * @author Roberto Mandolesi Vilas Boas - rvilas@br.ibm.com
 */
public class MainActivity extends AppCompatActivity {

    private Intent pollingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pollingIntent = new Intent(this,PollingService.class);
    }

    @Override
    protected void onResume() {
        startService(pollingIntent);
        super.onResume();

    }
}
