package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;

public class BeatListener extends PApplet {
    Minim minim;
    AudioPlayer song;
    BeatDetect beat;

    float kickSize, snareSize, hatSize;

    public void settings() {
        //size(1024, 800, P3D);
        size(512, 200, P3D);
        // fullScreen(P3D);
        //smooth();
        // frameRate(24);
    }

    public void setup() {
        

        minim = new Minim(this);

        song = minim.loadFile("[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 1024);
        song.play();
        // a beat detection object that is FREQ_ENERGY mode that
        // expects buffers the length of song's buffer size
        // and samples captured at songs's sample rate
        beat = new BeatDetect(song.bufferSize(), song.sampleRate());
        // set the sensitivity to 300 milliseconds
        // After a beat has been detected, the algorithm will wait for 300 milliseconds
        // before allowing another beat to be reported. You can use this to dampen the
        // algorithm if it is giving too many false-positives. The default value is 10,
        // which is essentially no damping. If you try to set the sensitivity to a
        // negative value,
        // an error will be reported and it will be set to 10 instead.
        // note that what sensitivity you choose will depend a lot on what kind of audio
        // you are analyzing. in this example, we use the same BeatDetect object for
        // detecting kick, snare, and hat, but that this sensitivity is not especially
        // great
        // for detecting snare reliably (though it's also possible that the range of
        // frequencies
        // used by the isSnare method are not appropriate for the song).
        beat.setSensitivity(300);
        kickSize = snareSize = hatSize = 16;
        // make a new beat listener, so that we won't miss any buffers for the analysis
        
        textFont(createFont("Helvetica", 16));
        textAlign(CENTER);
    }

    public void draw() {
        background(0);

        // draw a green rectangle for every detect band
        // that had an onset this frame
        float rectW = width / beat.detectSize();
        for (int i = 0; i < beat.detectSize(); ++i) {
            // test one frequency band for an onset
            if (beat.isOnset(i)) {
                fill(0, 200, 0);
                rect(i * rectW, 0, rectW, height);
            }
        }

        // draw an orange rectangle over the bands in
        // the range we are querying
        int lowBand = 5;
        int highBand = 15;
        // at least this many bands must have an onset
        // for isRange to return true
        int numberOfOnsetsThreshold = 4;
        if (beat.isRange(lowBand, highBand, numberOfOnsetsThreshold)) {
            fill(232, 179, 2, 200);
            rect(rectW * lowBand, 0, (highBand - lowBand) * rectW, height);
        }

        if (beat.isKick())
            kickSize = 32;
        if (beat.isSnare())
            snareSize = 32;
        if (beat.isHat())
            hatSize = 32;

        fill(255);

        textSize(kickSize);
        text("KICK", width / 4, height / 2);

        textSize(snareSize);
        text("SNARE", width / 2, height / 2);

        textSize(hatSize);
        text("HAT", 3 * width / 4, height / 2);

        
    }
}
