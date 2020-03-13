package br.com.vw.myvwautomotive.VWPollingApplication.threads;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import br.com.vw.myvwautomotive.VWPollingApplication.interfaces.PollingInterface;
import br.com.vw.myvwautomotive.models.ContentModel;
import br.com.vw.myvwautomotive.models.MessageContentModel;
import br.com.vw.myvwautomotive.models.MessageObjectModel;

/**
 * @author Roberto Mandolesi Vilas Boas - rvilas@br.ibm.com
 */
public class PollingThread extends Thread {

    private PollingInterface mPollingInterface;
    private Context mAppContext;
    private final int INTERVAL_THREAD = 5500;
    private final String INTENT_EXTRA_NAME = "MY_VW_MSG",
            INTENT_APP_PACKAGE = "br.com.vw.myvwautomotive.receiverappsample",
            INTENT_APP_RECEIVER="br.com.vw.myvwautomotive.receivers.PollingReceiver";
    private Gson mGson;

    public boolean mShouldStop = false;

    public PollingThread(PollingInterface pollingInterface, Context context) {
        mPollingInterface = pollingInterface;
        mAppContext = context;
        mGson = new Gson();
    }

    @Override
    public void run() {
        super.run();
        int count=0,id = 0;
        while (!mShouldStop) {
            try {

                final Intent myVWIntent =new Intent();

                // EXAMPLE MESSAGE
                MessageObjectModel messageObjectModel = new MessageObjectModel();
                MessageContentModel messageContentModel = new MessageContentModel();
                ContentModel contentModel = new ContentModel();

                contentModel.setMessage("HELLO WORLD !");
                contentModel.setUrl("htttps://youtube.com");

                messageContentModel.setContent(contentModel);
                messageContentModel.setGroup("Sample group or contact");
                messageContentModel.setTimestamp(String.valueOf(count));
                messageContentModel.setId(String.valueOf(id));

                messageObjectModel.setType("text");
                messageObjectModel.setMessage(messageContentModel);

                id += 1;
                count += INTERVAL_THREAD;

                myVWIntent.setAction(mAppContext.getPackageName());
                myVWIntent.putExtra(INTENT_EXTRA_NAME,mGson.toJson(messageObjectModel));
                myVWIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                myVWIntent.setComponent(new ComponentName(INTENT_APP_PACKAGE,INTENT_APP_RECEIVER));

                Log.v("POLLING BROADCAST",myVWIntent.getAction());
                Log.v("POLLING BROADCAST",myVWIntent.getExtras().getString(INTENT_EXTRA_NAME));
                Log.v("POLLING BROADCAST","sending broadcast");

                mAppContext.sendBroadcast(myVWIntent);

                Thread.sleep(INTERVAL_THREAD);

            } catch (InterruptedException e) {
                Log.v("POLLING BROADCAST",e.getMessage());
                mPollingInterface.pollingError(e.getMessage());
            }
        }
    }

    public void setToStop() {
        this.mShouldStop = true;
    }
}
