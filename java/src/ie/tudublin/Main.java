package ie.tudublin;

import creed.*;

public class Main
{	

	public void Audio()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio());		
	}
	public void MusicViz()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MusicViz());		
	}

	public void ripple()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new RippleEffect());		
	}

	public void tapePlayer()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new TapePlayer());		
	}


	public static void main(String[] args)
	{
		Main main = new Main();
		main.tapePlayer();			
	}
}