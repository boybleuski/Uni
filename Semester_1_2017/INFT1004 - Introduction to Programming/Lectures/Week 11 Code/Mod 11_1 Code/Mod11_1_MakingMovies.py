###  Programmer : Keith Nesbitt
###  Date: 11 May 2017
###  This program illustrates the how to make movies and simple animations in JES
###
###  You will need to set the mediaPath (setMediaPath() to the
###  directory where you want the movie frames created
###  Use a different directory for each movie (so the frame names don't get confused)
###  >>> makeTickerTapeMovie() 
###  >>> makeRedRectangleMovie() 
###  >>> makeFadedMovie()
###
def makeRedRectangleFrames():
   # This function makes and returns a short movie that shows a red rectangle moving across 
   # and down the screen. The frames for the movies consist of a  number of jpeg pictures.
   #
   # Note: I first created a directory called "RedRectangleMovie" and set this as the
   #       the media path - this ensures the picture files are placed in this directory.
   
   directory = getMediaPath()
   print("directory=" + directory)
   
   for i in range(0,30): #30 frames - from frame00.jpg to frame29.jpg
      frame = makeEmptyPicture(300,200)          # create a new blank picture
      addRectFilled(frame,i*10, i*5, 50,50, red) # draw a red rectangle - (x,y) position depends on i
      numberString=str(i)
      
      #set up the picture file name
      if i < 10:
         name = directory + "/rrFrame0" + numberString +".jpg"   #add a "0" to number
         writePictureTo(frame, name)
      else:
         name = directory + "/rrFrame" + numberString +".jpg"    #no "0" added
         writePictureTo(frame, directory + "/rrFrame" + numberString +".jpg")
   
    
   #now make the movie
   movie=makeMovieFromInitialFile(directory + "/rrFrame00.jpg")
   print(movie) # just shows the name and path of movie
   
   return movie
   
def makeRedRectangleMovie():
   # This function test the making of an avi movie/animation
   # the function "makeRedRectangleFrames" creates a directory full of jpg pictures to be
   # used as frames in the animation 
   # the frames, like the movie, is created in the current media path - so you might want to
   # create a diurectory to store the movie and frames in and set this as the media path
   # before you run the function
   
   # setMediaPath()  # select the mediaPath
   movie = makeRedRectangleFrames()
   playMovie(movie)
   
   #save movie to avi format
   movieFilename = getMediaPath() + "redRectangle.avi"
   print (movieFilename)
   writeAVI(movie, movieFilename)  #save the avi file

def makeTickerTapeFrames(aString):
   # This function creates and returns a short movie that shows a ticker tape animation
   # It consists of a "string" moving across the screen from left to right 
   # The frames for the movies consist of a  number of jpeg pictures.
   #
   # Note: I first created a directory called "TickerTapeMovie" and set this as the
   #       the media path - this ensures the picture files are placed in this directory.

   directory = getMediaPath()
   print("directory=" + directory)

   for n in range(0,120): #120 frames - from frame000.jpg to frame119.jpg
      frame = makeEmptyPicture(300,100)
      addText(frame, 300-(n*4), 50, aString)
      endString=str(n) + ".jpg"  #this is used to number the jpg frames from 000-119
      # these selection statements ensure that filenames have the same length
      # and will end up being sorted in alphabetical order from 
      # ttFrame000.jpg to ttFrame119.jpg
      
      if n < 10:
         writePictureTo(frame, directory + "/ttFrame00" + endString)
      elif n < 100:
         writePictureTo(frame, directory + "/ttFrame0" + endString)      
      else:
         writePictureTo(frame, directory + "/ttFrame" + endString)
         
   movie=makeMovieFromInitialFile(directory + "/ttFrame000.jpg")
   return movie

def makeTickerTape():
   # This function test the making of an avi movie/animation
   # the function "makeTickerTapeFrames" creates a directory full of jpg pictures to be
   # used as frames in the animation 
   # the frames, like the movie, is created in the current media path - so you might want to
   # create a diurectory to store the movie and frames in and set this as the media path
   # before you run the function

   message=requestString("Enter the message to use in ticker tape")
   movie = makeTickerTapeFrames(message)
   playMovie(movie)

   #save movie to avi format
   movieFilename = getMediaPath() + "tickerTape.avi"
   print (movieFilename)
   writeAVI(movie, movieFilename)  #save the avi file


def makeFadingFrames(picture):
   # This function creates and returns a short movie that shows a picture
   # (passed as a parameter) that fades to black during the movie 
   # The frames for the movies consist of a  number of jpeg pictures.
   # Note: probably best to use a small picture as this might take a while
   #
   # Note: I first created a directory called "FadingMovie" and set this as the
   #       the media path - this ensures the picture files are placed in this directory.

   directory = getMediaPath()
   print("directory=" + directory)

   for n in range(0,51): #50 frames - from frame00.jpg to frame50.jpg
      frame =fadePicture(picture,n*2) #this function creates the new frame
      
      numberString=str(n)  #this is used to number the jpg frames from 00-50
      # these selection statements ensure that filenames have the same length
      # and will end up being sorted in alphabetical order from 
      # fpFrame00.jpg to fpFrame50.jpg
      if n < 10:
         writePictureTo(frame, directory + "/fpFrame0" + numberString + ".jpg")
      else:
         writePictureTo(frame, directory + "/fpFrame" + numberString + ".jpg")
         
   movie=makeMovieFromInitialFile(directory + "/fpFrame00.jpg")
   return movie

def fadePicture(picture, fadePercentage):
   # This function creates and returns a new picture that is the picture
   # with a percentage of black (fadePercentage) added to the original image
   # (passed as a parameter) that fades to black during the movie 
   
   colorAmount = fadePercentage / 100   
   pictureAmount = (100 - fadePercentage) /100.0  #how much of the picture to include
   print("fadePercent="+str(fadePercentage))
   
   # when pictureAmount = 0 then the picture will be black
   # black is red=0, green=0, blue=0 - so no need to add anything really
   # but I put these here as you could fade to another colour - such as white or blue etc.
   redAdd   = 0 * colorAmount
   greenAdd = 0 * colorAmount
   blueAdd  = 0 * colorAmount
   
   fadedPicture = duplicatePicture(picture)
   for pixel in getPixels(fadedPicture): #treat all pixels the same
      newRed   = int((getRed(pixel) * pictureAmount) + redAdd)
      newGreen = int((getGreen(pixel) * pictureAmount) + greenAdd)
      newBlue  = int((getBlue(pixel) * pictureAmount) + blueAdd)
      setRed(pixel, newRed)
      setGreen(pixel, newGreen)
      setBlue(pixel, newBlue)
       
   return(fadedPicture)
   
def makeFadedMovie():
   # This function test the making of an avi movie/animation
   # the function "makeTickerTapeFrames" creates a directory full of jpg pictures to be
   # used as frames in the animation 
   # the frames, like the movie, is created in the current media path - so you might want to
   # create a diurectory to store the movie and frames in and set this as the media path
   # before you run the function
   file = pickAFile()
   picture = makePicture(file)
   movie = makeFadingFrames(picture)
   playMovie(movie)

   #save movie to avi format
   movieFilename = getMediaPath() + "FadedMovie.avi"
   print (movieFilename)
   writeAVI(movie, movieFilename)  #save the avi file

   