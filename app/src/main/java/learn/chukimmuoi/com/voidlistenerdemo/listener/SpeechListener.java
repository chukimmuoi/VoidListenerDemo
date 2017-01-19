package learn.chukimmuoi.com.voidlistenerdemo.listener;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import learn.chukimmuoi.com.voidlistenerdemo.service.SpeechService;

import static learn.chukimmuoi.com.voidlistenerdemo.service.SpeechService.ACTION_START;

/**
 * @author:Hanet Electronics
 * @Skype: chukimmuoi
 * @Mobile : +84 167 367 2505
 * @Email: muoick@hanet.com
 * @Website: http://hanet.com/
 * @Project: VoidListenerDemo
 * Created by CHUKIMMUOI on 1/13/2017.
 */

public class SpeechListener implements RecognitionListener {

    private static final String TAG = SpeechListener.class.getSimpleName();

    private Context mContext;

    private AudioManager mAudioManager;

    private TextView mTextVoice;

    private TextView mTextMessage;

    private int mStreamVolume;

    public SpeechListener(Context context, AudioManager audioManager, int streamVolume, TextView textVoice, TextView textMessage) {
        mContext      = context;
        mAudioManager = audioManager;
        mStreamVolume = streamVolume;
        mTextVoice    = textVoice;
        mTextMessage  = textMessage;
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Log.e(TAG, "onReadyForSpeech");
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mStreamVolume, 0);

//        mTextView.setText("");
        mTextMessage.setText("Listening...");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.e(TAG, "onBeginningOfSpeech");
        mTextMessage.setText("Listening...");
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.e(TAG, "onRmsChanged");

    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.e(TAG, "onBufferReceived");

    }

    @Override
    public void onEndOfSpeech() {
        Log.e(TAG, "onEndOfSpeech");

    }

    @Override
    public void onError(int error) {
        Log.e(TAG, "onError");
        actionStart(mContext);
    }

    @Override
    public void onResults(Bundle results) {
        Log.e(TAG, "onResults");

        String str = new String();
        ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (int i = 0; i < data.size(); i++) {
            Log.e(TAG, "result: " + data.get(i));
            str += ((i != 0) ? ", " : "") + data.get(i);
        }
        mTextVoice.setText(str);
        mTextMessage.setText("Success");
        actionStart(mContext);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.e(TAG, "onPartialResults");

    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Log.e(TAG, "onEvent");

    }

    private void actionStop(Context context) {
        Intent intent = new Intent(context, SpeechService.class);
        intent.setAction(SpeechService.ACTION_STOP);
        context.startService(intent);
    }

    private void actionStart(Context context) {
        Intent intent = new Intent(context, SpeechService.class);
        intent.setAction(ACTION_START);
        context.startService(intent);
    }
}
