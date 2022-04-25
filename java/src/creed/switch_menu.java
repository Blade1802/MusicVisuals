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
    BeatDetect beat;
    FFT fft;

    // case 0
    // water ripple effect
    // arrays for water effect
    int cols = 200;
    int rows = 200;

    float[][] current;
    float[][] previous;

    float dampening = (float) 0.93;

    boolean flag = false;

    // water ripple effect
    public void mousePressed() {
        // previous[mouseX][mouseY] = 255;
        current[mouseX][mouseY] = 255;

        // case 1 - Music Viz
        if (dist(mouseX, mouseY, width / 2, height / 2) < 150)
            flag = !flag;
    }

    // case 1
    int r = 200;
    float rad = 0;

    AudioMetaData meta;

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

    public void settings() {
        // size(800, 600, P3D);
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile(
                "[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 1024);
        ap.play();
        ab = ap.mix;
        beat = new BeatDetect();
        beat.setSensitivity(50);
        meta = ap.getMetaData();
        ap.loop();
        noCursor();

        // case 0
        // water ripple effect
        cols = width;
        rows = height;

        current = new float[cols][rows];
        previous = new float[cols][rows];

    }

    public void draw() {
        // using cases

        switch (mode) {
            case 0: {
                // water ripple effect
                beat.detect(ab);
                background(0);
                loadPixels();
                if (beat.isOnset()) {
                    int x = 200 + (int) random((float) cols - 400);
                    int y = 100 + (int) random((float) rows - 200);
                    current[x][y] = 255;
                }
                for (int i = 1; i < cols - 1; i++) {
                    for (int j = 1; j < rows - 1; j++) {
                        //
                        current[i][j] = (previous[i - 1][j] +
                                previous[i + 1][j] +
                                previous[i][j - 1] +
                                previous[i][j + 1]) / 2 -
                                current[i][j];

                        current[i][j] = current[i][j] * dampening; // Gradually decreases the color value by 0.93%
                        int index = i + j * cols;
                        pixels[index] = color(current[i][j] * 255);
                    } // end inner for loop
                } // end outer for loop
                updatePixels();

                float[][] temp = previous;
                previous = current;
                current = temp;

                break;
            } // end case 0

            case 1: {
                // Music Viz - center white circle pattern

                background(0);
                // float t = map(mouseX, 0, width, 0, 1);
                beat.detect(ap.mix);
                fill(26, 31, 24);
                // fill(0);

                noStroke();
                rect(0, 0, width, height);
                translate(width / 2, height / 2);
                noFill();
                // fill(-1, 10);
                // fill(random(255), random(255), random(255));
                fill(255, 255, 255);
                if (beat.isOnset())
                    rad = (float) (rad * 0.9);
                else
                    rad = 70;
                ellipse(0, 0, 2 * rad, 2 * rad);
                stroke(-1, 50);
                int bsize = ap.bufferSize();

                for (int i = 0; i < bsize - 1; i += 5) {
                    float x = (r) * cos(i * 2 * PI / bsize);
                    float y = (r) * sin(i * 2 * PI / bsize);
                    float x2 = (r + ap.left.get(i) * 100) * cos(i * 2 * PI / bsize);
                    float y2 = (r + ap.left.get(i) * 100) * sin(i * 2 * PI / bsize);
                    line(x, y, x2, y2);
                }

                beginShape();
                noFill();
                stroke(-1, 50);
                for (int i = 0; i < bsize; i += 30) {
                    float x2 = (r + ap.left.get(i) * 100) * cos(i * 2 * PI / bsize);
                    float y2 = (r + ap.left.get(i) * 100) * sin(i * 2 * PI / bsize);
                    vertex(x2, y2);
                    pushStyle();
                    stroke(-1);
                    strokeWeight(2);
                    point(x2, y2);
                    popStyle();
                }
                endShape();
                // if (flag)
                // showMeta();

                break;
            }
            case 2: {

                //
                break;
            }
        }// end switch case
    }

    public void showMeta() {
        int time = meta.length();
        textSize(50);
        textAlign(CENTER);
        text((int) (time / 1000 - millis() / 1000) / 60 + ":" + (time / 1000 - millis() / 1000) % 60, -7, 21);
    }


}
