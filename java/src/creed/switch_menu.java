package creed;

//import statements
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.*;
import ddf.minim.*;
import processing.core.PApplet;

public class switch_menu extends PApplet {
    int mode = 0; // for keyPressed

    // audio minim
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    public void settings() {
        fullScreen(P3D, SPAN);
    }

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }

        if (keyCode == '0') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile("heroplanet.mp3", 1024);
        ap.play();
        ab = ap.mix;
    }

    public void draw() {
        //using cases

        switch(mode) {
            case 0: {

                break;
            }

            case 1: {
                break;
            }
            case 2: {
                break;
            }
        }
    }
}
