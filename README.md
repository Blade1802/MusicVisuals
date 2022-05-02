# Music Visualiser Project

Student Name1: 		Aayush Gaur </br>
Student Number1: 	C20396243</br>

Student Name2: 		Ayan Abedin</br>
Student Number2: 	D19125792</br>

Student Name3: 		Raghav Bansal</br>
Student Number3: 	D20123625</br>

Student Name4: 		Khushboo Jayan</br>
Student Number4: 	D20123668</br>

## Instructions 
- Fork this repository.
- Clone it on your PC and enjoy the audio visuliser.

# Description of the assignment
This project uses .java files and processing to add an animated component to the audio file for visualization. It uses the beats, and frequency of the audio file to change the sketch and color effect.

# How it works
The main function calls the visualiser function that later calls each individual render function for the respetive key pressed and switch condition satisfied.
Our program works on the cases. Each cases has a different effect. On every beats and drop and silence, we change between the cases manually. To avoid any distortion it changes very smoothly.
This is an audio visualiser that uses the concepts of polymorphism by classifying seven different for classes and then creating an object of each individual class in the visualiser class the inistialising in setup and lastly, calling the render function that will the draw the expected sketch. 
Inside each individual class we again create a visualiser object that we later use in render function.


# What we are most proud of in the assignment
We are proud of the overall project it was a bit challenging however, we had fun acquiring new processing and java skills. The challenges we overcame in the descending order are as follows:
- We had an idea of transition in effect of changing it with every bit. Everytime when beats drops and then it goes up, we see a new effect in that effect, with a differnt color. We achieved what our group wanted.
- Water ripple effect - This was the most challenging part of the assignment was creating the ripple effect. Firstly we had to create the effect that we did using mousePressed() function later added the fading effect to it. Secondly, we made the effect automated and added to a background song. Lastly, the most crucial part was to create each water drop effect that can respond to the beat of the sound because multiple ripple effect were being drawn to the same beat as a result of multiple instruments being played at the same time.
- Using the map function  to create a vibrant RGB color palette for circle2.java. 
- Creating the octopus effect using circles for LUYU.java. 
- For oscilloscope.java create two distinct lines which responds to every bit and amplitude of the song in their own nature in addition to creation of two concentric circles that change their radii on the amplitude and frequency as well.
- Strings vibrating with the music, in every effect there is unique motion and transition. 
- Our song is from the Assassins Creed Video games. So, we wanted to add blur background images that 


# EMPHASIS
Our motto for this project was to create something unique as a result we decided to develop the visualisation and transition on beats instead of creating the sketch and adding the music just as aa background. The color and effect changes on beats in addition to vibrant color patterns. 


This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings

##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

