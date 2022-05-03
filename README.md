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
The main function calls the visualiser.java class that later calls each render function in each individual .java files for the respetive key pressed and switch condition satisfied. Our program works on the cases. Each cases has a different effect. On every beats drop and silence, we change between the cases <ins>manually</ins>. We used the fading effect to avoid any distortion and making the changes smooth. This visualiser uses the concepts of polymorphism by classifying seven different classes and then creating an object of each individual class in the visualiser.java class and inistialises in setup and lastly, calling the render function that will then draw the expected sketch. Inside each individual class we again create a visualiser object that we later use in render function.


# What we are most proud of in the assignment
We are proud of the overall project it was a bit challenging however, we had fun acquiring new processing and java skills. The challenges we overcame in the descending order are as follows:
- Water ripple effect - This was the most challenging part of the assignment was creating the ripple effect. Firstly we had to create the effect that we did using mousePressed() function later added the fading effect to it. Secondly, we made the effect automated and added to a background song. Lastly, the most crucial part was to create each water drop effect that can respond to the beat of the sound because multiple ripple effect were being drawn to the same beat as a result of multiple instruments being played at the same time.
- Transition between each case was done smoothly by using a fading effect. This was done by using a rectangle with an alpha fill value.
- Continuous change in colors in circle.java. This creates a vibrant RGB color palette. 
- Creating the octopus effect using circles for LUYU.java. 
- For oscilloscope.java create two distinct lines which represents the frequency band of the song and in addition to creation of two concentric circles that change their radii on the amplitude and frequency as well.
- Strings vibrating with the music, in every effect there is unique motion and transition. 
- Our song is from the Assassins Creed Video games. So, we wanted to add blur background images after spending some time on implementing that part we observed that  if we add background image there is a lag for drawing the sketch. As a result, we did not use background images for our Project. We learnt a lot about pixels and image processing. Our images are saved in Data folder as well as we have a java file that uses the pixel brightness effect to create an outline effect. Please checkout some cool images for assassins creed as well the the code it MusicViz.java file in 'Khushboo' and 'AyanAudio branch'. 


# Emphasis
Our motto for this project was to create something unique as a result we decided to develop the visualisation and transition on beats instead of creating the sketch and adding the music just as aa background. The color and effect changes on beats in addition to vibrant color patterns. We had an idea of transition effect that will change with every bit. Everytime when beats drops and then it goes up, we see a new effect in that effect, with a differeent color. 

# Project management
- We all work part-time jobs hence it was a bit difficult to get together at the same time hence we devoted the weekend nights as well as few weekdays nights for OOP project. Our study sessions streached for 10 hours  till 6:00 or 7:00 am.
- We used ONENOTE to brainstorm ideas and were able to come up with different cases to be implemented in java. 


# Youtube video:

[![YouTube](https://img.youtube.com/vi/NW0Q_yS2Hpo/0.jpg)](https://youtu.be/NW0Q_yS2Hpo)

