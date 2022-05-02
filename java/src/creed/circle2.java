package creed;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;

public class circle2 extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    BeatDetect beat;

    // circle particle effect
    float n = 0;
    float n3 = 0;
    float n5 = 0;
    float speed2 = 0;

    int c = 0;
    int[][] RGBval = { {255, 0, 0}, {255, 165, 0}, {255, 255, 0}, {0, 128, 0}, {0, 0, 255}, {75, 0, 130}, {238, 130, 238} };
    int c1 = 51;
    int c2 = 204;
    int c3 = 255;

    public void settings() {
        // size(1024, 800, P3D);
        fullScreen(P3D,SPAN);
        smooth();
        // frameRate(24);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("creed.mp3", 2048);
        ap.play();
        ab = ap.mix;
        beat = new BeatDetect();
        beat.setSensitivity(100);
        colorMode(HSB);

    }

    public void draw() {
        beat.detect(ab);
        fill(0, 20);
        noStroke();
        rect(0, 0, width, height);
        translate(width / 2, height / 2);

        for (int i = 0; i < ap.bufferSize() - 1; i++) {

            //float angle = sin(i + (n - 2)) * 40;
            //float x = sin(radians(i)) * (n / angle);

            float leftLevel = ap.left.level() * 20;
            ellipse(i, i, leftLevel, leftLevel);
            rotateZ((float) (n * -PI / 3 * 0.05));

            
            //fill(51,204,255); // Light Blue
            //fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]); // Light Blue
            // float color1 = map(i, 0, ap.bufferSize(), 0, 255);
            //fill(color1, 255, 255);
            
            if(beat.isOnset())
            {
                c = (c + 1) % 7;
                //fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]);
                //fill(c1++%256,c2++%256,c3++%256);
                float c = map(i, 0, ap.bufferSize(), 0, 255);
                fill(c,255,255);
                
                
            }
            // c = (c + 1) % 7;
            // fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]);
            fill(c1++%256,c2++%256,c3++%256);
            

        }

        n += 0.008;
        n3 += speed2;
        n5 += speed2;

    }
}