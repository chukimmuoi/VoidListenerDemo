package learn.chukimmuoi.com.voidlistenerdemo;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import learn.chukimmuoi.com.voidlistenerdemo.broadcast.SpeechBroadcast;
import learn.chukimmuoi.com.voidlistenerdemo.constanst.IConstanst;
import learn.chukimmuoi.com.voidlistenerdemo.speech.SpeechManager;

import static learn.chukimmuoi.com.voidlistenerdemo.service.SpeechService.ACTION_START;
import static learn.chukimmuoi.com.voidlistenerdemo.service.SpeechService.ACTION_STOP;

public class MainActivity extends AppCompatActivity implements IConstanst {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTvVoid;

    private TextView mTvMessage;

    private SpeechManager mSpeechManager;

    private SpeechBroadcast mSpeechBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialUI();

        mSpeechManager = SpeechManager.getInstance().onCreate(MainActivity.this, mTvVoid, mTvMessage);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mSpeechBroadcast = new SpeechBroadcast(mSpeechManager);
        mSpeechBroadcast.handleActionStart();

        registerReceiver(mSpeechBroadcast, new IntentFilter(ACTION_START));
        registerReceiver(mSpeechBroadcast, new IntentFilter(ACTION_STOP));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mSpeechBroadcast);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSpeechManager != null) {
            mSpeechManager.onDestroy();
        }
    }

    private void initialUI() {
        mTvVoid    = (TextView) findViewById(R.id.tv_void);
        mTvMessage = (TextView) findViewById(R.id.tv_message);
    }
}
