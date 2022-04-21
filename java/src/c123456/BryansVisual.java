package c123456;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;



public class BryansVisual extends Visual 
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    int x, j;
    int mode = 0;
    float lerpedBuffer[];
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float borderx;
    float bordery;
    float halfH;
    float halfW;
    Viz1 viz1;
    

    public void settings()
    {
        size(500, 500, P3D);//min size for tv graphic is 500 x 500
        println("CWD: " + System.getProperty("user.dir"));//current working directory
        //fullScreen(P3D,SPAN);// full screen
    }//end settings

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

    public void setup()
    {
        minim = new Minim(this);
        colorMode(RGB);
        setFrameSize(256);
        startMinim();
        //startListening(); 
        ap = minim.loadFile("[YT2mp3.info] - Assassin_'s Creed II   Ezio_'s Family (Dubstep Remix) Remake (320kbps).mp3", 500);
        //ap.setGain(-10); // set volume
        ap.play();
        ab = ap.mix;
        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
        viz1 = new Viz1(width, height, lerpedBuffer, this);
        
    }//end setup

    public void draw()
    {
        //background
        background(100,100,100);
        noStroke();
        //border calculation
        borderx = width * 0.2f;
        bordery = height * 0.25f;
        float frame = 30f;
        float detail = 5f;
        halfH = height / 2;
        halfW = width/2;
        float average = 0;
        float sum = 0;
        
        //details --> minimum size for tv details
        if(height >= 500 && width >= 500)
        {
            colorMode(RGB);
            //desk
            fill(59, 32, 18);
            quad(width/24,height,width/8,height-height/3,width-width/8,height-height/3,width-width/24,height);
            //tv frame
            fill(150); 
            rect(borderx-frame, bordery-frame, width-(borderx*2)+(frame*2), height-(bordery*2)+(frame*4), detail*2);    
            //details
            fill(100);
            rect(borderx, (bordery + height-(bordery*2)) + detail, width-(borderx*2), detail, detail*2);//small thing indent
            strokeWeight(2);
            stroke(95);
            rect(borderx, (bordery + height-(bordery*2)) + detail*3, width-(borderx*2), frame*2, detail*2);//panel
            noStroke();
            //details --> input
            fill(60);
            rect(borderx + frame, (bordery + height-(bordery*2)) + (detail*3) + (frame / 2), frame*2, frame, detail);//power button
            rect(borderx + (frame*(float)2.5) + frame, (bordery + height-(bordery*2)) + (detail*3) + (frame / 2), frame - (frame/3), frame, detail);//channel button prev
            rect(borderx + (frame*(float)2.5) + frame*2, (bordery + height-(bordery*2)) + (detail*3) + (frame / 2), frame - (frame/3), frame, detail);//channel button prev
            strokeWeight(2);//proper stroke weight
            stroke(92, 145, 230);//light blue
            circle(borderx + (frame*(float)7.5),(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line in
            stroke(115, 199, 132);//light green
            circle(borderx + (frame*(float)7.5) + frame,(bordery + height-(bordery*2)) + (detail*3) + (frame),frame/2);//line out
        }//end if statement
        //tv screen
        noStroke();
        fill(20);
        
        rect(borderx, bordery, width-(borderx*2), height-(bordery*2), detail*2);
        switch (mode) {
            case 0:
            {   // iterate through the width of the screen
                viz1.render();
                break;
            }
        }//end switch
    }//end draw

    public void mouseClicked()
    {   
        float detail = 5f;
        if (mouseX >= borderx && mouseX <= width-borderx && mouseY >= (bordery + height-(bordery*2)) + detail*3 && mouseY <= ((bordery + height-(bordery*2)) + detail*3) + 60f)
        {
            if (ap.isPlaying())
            {
                ap.pause();
            }
            else
            {
                ap.play();
            }
        }
    }
}//end NameVisual