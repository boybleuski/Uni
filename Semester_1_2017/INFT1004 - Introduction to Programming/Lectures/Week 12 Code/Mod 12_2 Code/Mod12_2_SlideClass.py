###  Programmer : Simon
###  Date: October 2012
###  Modifications: Keith Nesbitt
###  May 2017
###  This  program defines a new class called "Slide"
###  This class can be used to give a slide show.
###  Each slide contains a picture and sound  
###  
###  You can test this code but you will need to make sure you set the media path 
###  to where all the pictures and sounds are..
###  >>> playSlideShow()


class Slide:
# Slide class isn't a subclass of anything - it's a brand new class

   def __init__(self, pictureFile = None, soundFile = None):
      # This method is a constructor; given a picFile and a soundFile,
      # it creates a new Slide
      # To call it, we don't use the method name, __init__; we use the class name, Slide
      # If no first argument is provided, picFile will be given the special value of None
      # If no second argument is provided, soundFile will be given the special value of None
      if pictureFile == None:  # If no picture file was provided, use this one
         self.setPicture(makePicture(getMediaPath("church.jpg")))
      else:
         self.setPicture(makePicture(pictureFile))

      if soundFile == None:  # If no sound file was provided, use this one
         self.setSound(makeSound(getMediaPath("church.wav")))
      else:
         self.setSound(makeSound(soundFile))

     
      
   # Set and get the Slide's picture property
   def setPicture(self, newPicture):

      self.picture = newPicture
  
   def getPicture(self):
      return self.picture
  
   # Set and get the Slide's sound property
   def setSound(self, newSound):
      self.sound = newSound
  
   def getMySound(self):
      return self.sound
  
   # Display the slide by showing its picture and playing its sound
   # Note that this is called show(), and the Picture class also has a show() method.
   # This is an example of method overloading or polymorphism.
   def show(self):
      show(self.getPicture())
      blockingPlay(self.getMySound())
  
   # This is the end of class Slide


# Now we have some functions that use class Slide

def playSlideShow():
# This function makes four Slides and plays them in a slide show
# It does assume the mediaPath is set so that JEs can find the pictures and files


   slide = {}  # This makes slide an empty list
  
   # Now we make four Slides
   # When we call Slide(somePicFile, someSoundFile) Python executes the Slide constructor,
   # the Slide method called __init__
   slide[0] = Slide(getMediaPath("barbara.jpg"), getMediaPath("bassoon-c4.wav"))
   slide[1] = Slide(getMediaPath("beach.jpg"), getMediaPath("bassoon-e4.wav"))
   slide[2] = Slide(getMediaPath("church.jpg"), getMediaPath("bassoon-g4.wav"))
   slide[3] = Slide(getMediaPath("jungle2.jpg"), getMediaPath("bassoon-c4.wav"))
  
   # Now we display each Slide in the list
   for i in range(0,4):
      slide[i].show()

