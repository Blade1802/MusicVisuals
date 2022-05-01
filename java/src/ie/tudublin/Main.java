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

	public void rotating()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new RotatingAudioBands());		
	}

	public void circle2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new circle2());		
	}

	public void BeatListener()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new BeatListener());		
	}

	public void waterRipple()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new waterRipple());		
	}

	public void Audio2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio2());		
	}

	public void Audio1()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());		
	}

	public void LuYu()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new LuYu());		
	}

	public void Mesh_Line()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Mesh_Line());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.circle2();
		// main.waterRipple();
		// main.rotating();
		// main.BeatListener();
		// main.Audio2();
		// main.Audio1();
		// main.LuYu();
		// main.Mesh_Line();
	}
}