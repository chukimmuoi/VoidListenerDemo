package learn.chukimmuoi.com.voidlistenerdemo.speech;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.TextView;

import learn.chukimmuoi.com.voidlistenerdemo.constanst.IConstanst;
import learn.chukimmuoi.com.voidlistenerdemo.listener.SpeechListener;

/**
 * @author:Hanet Electronics
 * @Skype: chukimmuoi
 * @Mobile : +84 167 367 2505
 * @Email: muoick@hanet.com
 * @Website: http://hanet.com/
 * @Project: VoidListenerDemo
 * Created by CHUKIMMUOI on 1/14/2017.
 */


public class SpeechManager implements IConstanst {

    private static final String TAG = SpeechManager.class.getSimpleName();

    private static SpeechManager ourInstance;

    private SpeechRecognizer mSpeechRecognizer;

    private Intent mIntent;

    public static SpeechManager getInstance() {
        if (ourInstance == null) {
            synchronized (SpeechManager.class) {
                ourInstance = new SpeechManager();
            }
        }
        return ourInstance;
    }

    public SpeechManager onCreate(Context context, TextView textVoice, TextView textMessage) {
        initialSpeech(context, textVoice, textMessage);
        return ourInstance;
    }

    public void onStart(){
        startListener();
    }

    public void onDestroy() {
        if (mSpeechRecognizer != null) {
            stopListener();
            mSpeechRecognizer.destroy();
            mSpeechRecognizer = null;
        }

        if (ourInstance != null) {
            ourInstance = null;
        }
    }

    private void initialSpeech(Context context, TextView textVoice, TextView textMessage) {
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        mSpeechRecognizer.setRecognitionListener(new SpeechListener(context, textVoice, textMessage));

        mIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getClass().getPackage().getName());

        mIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAX_RESULTS_VOID_SEARCH);
        mIntent.putExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES, EXTRA_CONFIDENCE_SCORES);
        mIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS);

        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, LANGUAGES_VI);
        mIntent.putExtra(EXTRA_ADDITIONAL_LANGUAGES, new String[]{LANGUAGES_VI});
    }

    private void startListener() {
        if (mSpeechRecognizer != null) {
            mSpeechRecognizer.startListening(mIntent);
        }
    }

    private void stopListener() {
        if (mSpeechRecognizer != null) {
            mSpeechRecognizer.stopListening();
            mSpeechRecognizer.cancel();
        }
    }
}
