# Music Visualiser Project

Student Name1: 		Aayush Gaur </br>
Student Number1: 	C20396243</br>

Student Name2: 		Ayan Abedin</br>
Student Number2: 	D19125792</br>

Student Name3: 		Raghav Bansal</br>
Student Number3: 	</br>

Student Name4: 		Khushboo Jayan</br>
Student Number4: 	D20123668</br>

## Instructions 
- Fork this repository.

# Description of the assignment
This project uses .java files and processing to add an animated component to the audio file for visualization. 

# How it works
This is an audio visualiser that uses the concepts of polymorphism by classifying seven different for classes and then creating an object of each individual class in the visualiser class the inistialising in setup and lastly, calling the render function that will the draw the expected sketch. 
Inside each individual class we again create a visualiser object that we later use in render function.

# What I am most proud of in the assignment
We are proud of the overall project it was a bit challenging however, we had fun acquiring new processing and java skills. The challenges we overcame in the descending order are as follows:
- Water ripple effect - This was the most challenging part of the assignment. was really difficult to create each water drop effect that can respond to the beat of the sound. mouse click, added to the song, added on the beats, multiple ripple effect to the same beat bcuz multiple instruments were playing at the same time.
- The most crucial part was using the map function  to create a vibrant RGB color palette. 
- Secondly, it was a bit difficult in creating the LUYU effect octopus effect using circles. 
- Oscilloscope - able to creat two distinct lines which responds to every bit and amplitude of the song in their own nature. also, there is a circle behind it which does.



# Markdown Tutorial

This is *emphasis* - 
create something unique 
- beat based
- color vibrant pattern
- color and effect changes on beats eg- circle.java

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
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

