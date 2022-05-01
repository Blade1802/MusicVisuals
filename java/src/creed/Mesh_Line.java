package creed;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
// import ddf.minim.signals.*;//for case 2
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Mesh_Line extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    BeatDetect beat;
    FFT fft;

    // circle particle effect
    float n4 = 0;
    float n6 = 0;

    // circle particle effect
    float n = 0;
    float n3 = 0;
    float n5 = 0;
    float speed2 = 0;

    int c = 0;
    int[][] RGBval = { { 255, 0, 0 }, { 255, 165, 0 }, { 255, 255, 0 }, { 0, 128, 0 }, { 0, 0, 255 }, { 75, 0, 130 },
            { 238, 130, 238 } };
    int c1 = 51;
    int c2 = 204;
    int c3 = 255;
    // arrays for water effect
    int cols = 200;
    int rows = 200;
    // float[][] current = new float[cols][rows];
    // float[][] previous = new float[cols][rows];

    float[][] current;
    float[][] previous;

    float dampening = (float) 0.93;

    int x; // used to make the circle spin
    int radius = 200;// radius in pixels of the circle

    int mode = 0;

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
        // size(1024, 800, P3D);
        fullScreen(P3D, SPAN);
    }

    public void setup() {
        background(0);
        noCursor();

        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile(
                "creed.mp3", 2048);
        ap.play();
        ab = ap.mix;

        // Initialize audio analyzer
        fft = new FFT(ap.bufferSize(), ap.sampleRate());

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[2048];
        x = 0;
        beat = new BeatDetect();
        beat.setSensitivity(50);

        cols = width;
        rows = height;

        current = new float[cols][rows];
        previous = new float[cols][rows];

    }

    float off = 0;

    public void mousePressed() {
        // previous[mouseX][mouseY] = 255;
        current[mouseX][mouseY] = 255;

    }

    public void draw() {
        // background(0);
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

        float cx = width / 2;
        float cy = height / 2;

        switch (mode) {
            case 0: {
                background(0);
                colorMode(HSB);

                strokeWeight(2);
                for (int i = 0; i < ab.size(); i += 10) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    float r = map(i, 0, 0.5f, 100, 2000);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    line(0, i, lerpedBuffer[i] * halfH * 4, i);
                    line(width, i, width - (lerpedBuffer[i] * halfH * 4), i);
                    line(i, 0, i, lerpedBuffer[i] * halfH * 4);
                    line(i, height, i, height - (lerpedBuffer[i] * halfH * 4));
                    // circle(cx, cy, r);
                    circle(i, halfH + f, 5);
                    circle(i, halfH - f, 5);
                }
                break;
            }
            case 1: {
                // form mesh effect
                background(0);
                colorMode(HSB);

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
                circle(0, 0, 120); // White circle
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
            }

                break;
            case 2: {
                background(0);
                colorMode(RGB);

                translate(width / 2, height / 2);

                // Create circle interior
                noStroke();
                fill(200, 0, 0);
                circle(0, 0, 120); // White circle
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
            }

            case 4: {
                colorMode(RGB);

                fill(0, 50);
                noStroke();
                rect(0, 0, width, height);
                translate(width / 2, height / 2);

                for (int i = 0; i < ap.bufferSize() - 1; i++) {

                    float angle1 = sin(i + n4) * 10;
                    float angle2 = sin(i + n6) * 300;

                    float x = sin(radians(i)) * (angle2 + 30);
                    float y = cos(radians(i)) * (angle2 + 30);

                    float x3 = sin(radians(i)) * (500 / angle1);
                    float y3 = cos(radians(i)) * (500 / angle1);

                    // fill(255, 255, 0); // yellow color
                    // fill(255, 69, 0);// orange color
                    fill(255, random(255), 255);

                    ellipse(x, y, ap.left.get(i) * 10, ap.left.get(i) * 10);

                    fill(255);// white color
                    rect(x, y, ap.right.get(i) * 10, ap.left.get(i) * 10);

                    // fill(255, 69, 0);// orange color
                    // fill(124, 252, 0); //grass green color
                    // fill(255, 0, 0);//red color code
                    fill(random(0), 255, random(255));// aqua blue color code
                    rect(x, y, ap.right.get(i) * 10, ap.left.get(i) * 10);

                    fill(random(255), random(255), random(255));// white color
                    rect(x3, y3, ap.right.get(i) * 10, ap.right.get(i) * 20);

                }

                n4 += 0.008;
                n6 += 0.04;
                break;
            }
            case 5: {
                beat.detect(ab);
                fill(0, 20);
                noStroke();
                rect(0, 0, width, height);
                translate(width / 2, height / 2);

                for (int i = 0; i < ap.bufferSize() - 1; i++) {

                    // float angle = sin(i + (n - 2)) * 40;
                    // float x = sin(radians(i)) * (n / angle);

                    float leftLevel = ap.left.level() * 20;
                    ellipse(i, i, leftLevel, leftLevel);
                    rotateZ((float) (n * -PI / 3 * 0.05));

                    // fill(51,204,255); // Light Blue
                    // fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]); // Light Blue
                    // float color1 = map(i, 0, ap.bufferSize(), 0, 255);
                    // fill(color1, 255, 255);

                    if (beat.isOnset()) {
                        c = (c + 1) % 7;
                        // fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]);
                        // fill(c1++%256,c2++%256,c3++%256);

                    }
                    // c = (c + 1) % 7;
                    // fill(RGBval[c][0], RGBval[c][1], RGBval[c][2]);
                    fill(c1++ % 256, c2++ % 256, c3++ % 256);
                    // fill(c1++%256,c2++%256,c3++%256);

                }

                n += 0.008;
                n3 += speed2;
                n5 += speed2;
                break;

            }
        }

    }

}
