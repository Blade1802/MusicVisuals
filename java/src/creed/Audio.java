package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 1;

    float[] lerpedBuffer;
    float lerpedAverage = 0;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float theta = 0.0f;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings() {
        size(600, 600);
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile("heroplanet.mp3", 600);
        
        ab = ap.mix;
        colorMode(HSB);
        c
        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    float off = 0;

    public void toggle(){
        if(ap.isPlaying()){
            ap.play();
        }else{
            ap.pause();
        }
    }

    public void draw() {
        // background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        theta += 0.02;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        float cx = width / 2;
        float cy = height / 2;
        

        switch (mode)
        {
            case 0: {
                background(0);
                strokeWeight(2);
                for (int i = 0; i < ab.size(); i+= 10) {
                    float c = map(i, 0, ab.size(), 0, 255);
                    float r = map(i, 0, 0.5f, 100, 2000);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);  
                    circle(i, halfH + f, 5);
                    circle(i, halfH - f, 5);
                }        
                break;
            }

        case 1:
            background(0);
            float angleOne = theta;
            float angleTwo = theta;
            for (int x = 0; x <= width; x += 10) {
                // Calculate y value based off of sine function
                float y = map(sin(angleTwo), -1, 1, halfH, halfH+100    );
                // Draw an ellipse
                ellipse(x, y, 10, 10);
                // Increment angle
                angleTwo -= 0.1;
              }
            for (int x = 0; x <= width; x += 10) {
                // Calculate y value based off of sine function
                float y = map(sin(angleOne), -1, 1, halfH, 100);
                // Draw an ellipse
                ellipse(x, y, 10, 10);
                // Increment angle
                angleOne += 0.1;
              }
              
            break;

        case 2:
              background(0);
              break;
        }

    }
}