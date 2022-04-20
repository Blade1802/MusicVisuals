package ie.tudublin;

import example.*;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}

	public void circle2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new circle2());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.circle2();		
			
	}
}