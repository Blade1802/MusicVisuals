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

    // case 1 - Music Viz
    int r = 200;
    float rad = 0;

    AudioMetaData meta;

    // case 2
    // Mesh - Tape Recorder Effect
    int x; // Used to make the circle spin
    int radius = 200; // Radius in pixels of the circle

    // case 2 - Mesh Line effect with Tape Recorder
    float[] lerpedBuffer;
    // float[] lerpedBuffer2;
    float lerpedAverage = 0;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

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
        size(1024, 600, P3D);
        // fullScreen(P3D);
        smooth();
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile(
                "[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 2048);
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

        // case 2
        // Tape recorder effect
        // Initialize audio analyzer
        fft = new FFT(ap.bufferSize(), ap.sampleRate());
        x = 0;

    }

    // case 2 - Mesh Line with tape recorder
    float off = 0;

    // case 3
    // LuYu effect
    int n = 0;
    float c1 = 51;
    float c2 = 204;
    float c3 = 255;

    // for white
    float w1 = 255;

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
                colorMode(RGB);
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
                // Mesh Line effect with Tape recorder
                background(0);
                colorMode(HSB);

                float halfH = height / 2;
                float average = 0;
                float sum = 0;
                off += 1;
                // Calculate sum and average of the samples
                // Also lerp each element of buffer;
                for (int i = 0; i < ab.size(); i++) {
                    sum += abs(ab.get(i));
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
                    // lerpedBuffer2[i] = lerp(lerpedBuffer2[i], ab.get(i), 0.05f);

                }
                average = sum / (float) ab.size();

                smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

                strokeWeight(2);
                for (int i = 0; i < ab.size(); i += 10) {

                    float c = map(i, 0, ab.size(), 0, 255);

                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    // lerpedBuffer2[i] = lerp(lerpedBuffer2[i], ab.get(i), 0.3f);

                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(0, i, f, i);
                    line(width, i, width - f, i);
                    line(i, 0, i, f);// upper
                    line(i, height, i, height - f);// lower
                    // circle(cx, cy, r);
                    // circle(i, halfH + f, 5);
                    // circle(i, halfH - f, 5);
                }

                // --RECORD PLAYER Effect
                // record player effect
                colorMode(RGB);

                translate(width / 2, height / 2);
                // translate(mouseX, mouseY);

                // Create circle interior
                noStroke();
                fill(200, 0, 0);
                circle(0, 0, 100); // White circle
                fill(255);
                circle(cos(radians(x)) * 5, sin(radians(x)) * 5, 80); // Red circle
                fill(0);
                circle(0, 0, 10); // Black circle
                if (ap.isPlaying())
                    x += 2; // Circle only rotates while music is playing

                // Audio Visualization
                fft.forward(ap.mix);
                float bands = fft.specSize();

                for (int i = 0; i < bands * 2; i++) {

                    // Starting positions of line
                    float start_x = radius * cos(PI * (i + x) / bands);
                    float start_y = radius * sin(PI * (i + x) / bands);

                    // Draw line based on sound
                    stroke(255);
                    // stroke(random(255));
                    strokeWeight(5);
                    if (i < bands) {
                        // Line based on band length
                        line(start_x, start_y, start_x + fft.getBand(i) * 7 * cos(PI * (i + x) / bands),
                                start_y + fft.getBand(i) * 7 * sin(PI * (i + x) / bands));
                    } else {
                        // Line based on frequency
                        line(start_x, start_y, start_x + fft.getFreq(i) * 5 * cos(PI * (i + x) / bands),
                                start_y + fft.getFreq(i) * 5 * sin(PI * (i + x) / bands));
                    }
                }

                break;

            }
            case 3: {
                // LuYu effect
                background(0);
                translate(width / 2, height / 2);
                for (int i = 0; i < ap.bufferSize() - 1; i++) {
                    rotateX((float) (n * -PI / 4 * 0.05));
                    // rotateY(n*-PI/3*0.05);
                    // fill(255, 150);
                    // fill(255, 105, 180);// hot pink
                    fill(255, 20, 147);// dark pink
                    // fill(random(w1++ % 255), random(w1++ % 255));

                    float leftLevel = ap.left.level() * 100;
                    ellipse(i, i, leftLevel, leftLevel);
                    rotateZ((float) (n * -PI / 5 * 0.05)); // 3 -> 1
                    // fill(0, 150);
                    // fill(255, 0, 69);
                    // fill(34, 139, 34);// green
                    fill(124, 252, 0);// lawn green
                    // fill(255, 140, 0);

                    float rightLevel = ap.right.level() * 100;
                    rect(i, i, rightLevel, rightLevel);
                }
                n++;

            }
                break;

        }// end switch case
    }// end draw()

    public void showMeta() {
        int time = meta.length();
        textSize(50);
        textAlign(CENTER);
        text((int) (time / 1000 - millis() / 1000) / 60 + ":" + (time / 1000 - millis() / 1000) % 60, -7, 21);
    }

    public void stop() {
        ap.close();
        minim.stop();
        super.stop();
    }

}// end class switch_menu
