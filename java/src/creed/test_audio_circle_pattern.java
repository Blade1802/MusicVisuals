package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import processing.core.PApplet;

public class test_audio_circle_pattern extends PApplet {

    public void settings() {
        size(800, 800, P3D);
        // fullScreen(P3D);

    }

    public void setup() {
        
        // fullScreen(P3D);
        // smooth();
        // frameRate(24);
        surface.setLocation(987, 70);
        noLoop();
        // noStroke();
        // colorMode(HSB);
        mouseX = 10;
    }

    public void draw() {
        background(0);

        translate(width / 2, height / 2);

        float rStep = 30;
        float rMax = 400;

        for (float r = 0; r < rMax; r += rStep) {

            float c = 2 * PI * r; // circumference
            float cSegment = map(r, 0, rMax, rStep*3/4, rStep / 2);
            float aSegment = floor(c / cSegment); // angle Segment
            float ellipeSize = map(r, 0, rMax, rStep * 3 / 4 - 1, rStep / 4);

            for (float a = 0; a < 360; a += 360 / aSegment) {
                fill(random(255), random(255), random(255));

                pushMatrix();
                rotate(radians(a));
                ellipse(r, 0, ellipeSize, ellipeSize);
                popMatrix();
            }
        }
    }

    public void keyPressed() {
        if (key == ' ') {
            background(0);
            redraw();
        }
    }
}
