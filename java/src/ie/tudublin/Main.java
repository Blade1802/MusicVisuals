package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;
import team_Aayush.*;
import example.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}

	public void Circle_Effect()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new test_audio_1());		
	}

	public void Circle_Pattern()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new test_audio_circle_pattern());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		// main.startUI();
		// main.Circle_Effect();
		main.Circle_Pattern();			
	}
}