package learn.chukimmuoi.com.voidlistenerdemo.ui.animators;

import java.util.ArrayList;
import java.util.List;

import learn.chukimmuoi.com.voidlistenerdemo.ui.SpeechBar;

public class RmsAnimator implements BarParamsAnimator {
    final private List<BarRmsAnimator> barAnimators;


    public RmsAnimator(List<SpeechBar> speechBars) {
        this.barAnimators = new ArrayList<>();
        for (SpeechBar bar : speechBars) {
            barAnimators.add(new BarRmsAnimator(bar));
        }
    }

    @Override
    public void start() {
        for (BarRmsAnimator barAnimator : barAnimators) {
            barAnimator.start();
        }
    }

    @Override
    public void stop() {
        for (BarRmsAnimator barAnimator : barAnimators) {
            barAnimator.stop();
        }
    }

    @Override
    public void animate() {
        for (BarRmsAnimator barAnimator : barAnimators) {
            barAnimator.animate();
        }
    }

    public void onRmsChanged(float rmsDB) {
        for (BarRmsAnimator barAnimator : barAnimators) {
            barAnimator.onRmsChanged(rmsDB);
        }
    }
}
