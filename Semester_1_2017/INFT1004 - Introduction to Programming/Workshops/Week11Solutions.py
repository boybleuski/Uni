#######################################################################################################
## Programmer: Keith Nesbitt
## Date: 2 May, 2017
##
## This program contains solutions (or suggested solutions)
## for the problems from the tutorial in Week 11
## 
## Question 2  - testJoinSounds(), joinSounds(sound1, sound2)
## Question 3  - makeGreenBallMovie(), makeGreenBallFrames()
## Question 4  - makeSwapRowFrames(picture), swapPicture(picture, rowA, rowB), makeSwapMovie()
## 
#######################################################################################################


#######################################################################################################
####
#### 2. (M) Write a function called..
####                joinSounds(sound1, sound2)
####        This function takes two sounds and joins them into one long sound. 
####        There are no side effects.
####
#######################################################################################################

def testJoinSounds():

   # This function lets the user select two sounds files
   # These 2 files - are joined together to create a new sound 
   # which is explored to check the joinSound function worked 
   
   #select 2 wav files
   file1 = pickAFile()
   file2 = pickAFile()
   sound1 = makeSound(file1)
   sound2 = makeSound(file2)
   
   newSound = joinSounds(sound1, sound2)
   
   #explore the new sound to check it
   explore(newSound)
   

def joinSounds(sound1, sound2):
   # this function creates and returns a new sound which is 
   # the first sound joined to the second sound
   # There are no side effects (neither sound1 or sound2 is changed)
     
   longSound = makeEmptySound(getLength(sound1) + getLength(sound2))
   
   #copy first part
   for i in range(0, getLength(sound1)):
      value = getSampleValueAt(sound1, i)
      setSampleValueAt(longSound, i, value)

   #copy second part
   nextIndex =  getLength(sound1) #this is the next free location for the second part

   for i in range(0, getLength(sound2)):
      value = getSampleValueAt(sound2, i)
      setSampleValueAt(longSound, nextIndex, value)
      nextIndex = nextIndex + 1  #update the position for teh next sample
   
   return longSound
   
#######################################################################################################
#### 
#### 3.(T) Write a function that creates a short animation of a bouncing green ball.
#### 
#######################################################################################################


def makeGreenBallFrames():
   # This function makes and returns a short movie that shows a green ball bouncing
   # up and down - it's pretty simple movie and uses a while loop 
   # and down the screen. The frames for the movies consist of a  number of jpeg pictures.
   #
   # Note: I first created a directory called "RedRectangleMovie" and set this as the
   #       the media path - this ensures the picture files are placed in this directory.
   
   directory = getMediaPath()
   print("directory=" + directory)
   
   #set up some values to use in the animation
   xPosition = 100      # this will be constant - about middle of picture
   ballSize = 30        # size of the ball
   startYPosition = 50  # where the top of the ball starts from
   height = 300
   width = 200
   yPosition = startYPosition
   startYPosition = startYPosition + (startYPosition / 2)
   fallingDown = true

   
   for i in range(0,200): #200 frames - from frame000.jpg to frame199.jpg
      frame = makeEmptyPicture(width, height)          # create a new blank picture
      addOvalFilled(frame,xPosition, yPosition, ballSize,ballSize, green) 
      #printNow(yPosition)
      
      numberString=str(i)
      
      #set up the picture file name
      if i < 10:
         name = directory + "/gbFrame00" + numberString +".jpg"   #add a "0" to number
         writePictureTo(frame, name)
      elif i < 100:
         name = directory + "/gbFrame0" + numberString +".jpg"   #add a "0" to number
         writePictureTo(frame, name)
      else:
         name = directory + "/gbFrame" + numberString +".jpg"    #no "0" added
         writePictureTo(frame, name)
   
      # update the yPosition - check to see if it hits the bottom or gets back to the top
      if fallingDown:
         yPosition = yPosition + 6
         print("falling")
      else: #going up
         yPosition = yPosition - 4
         
      if yPosition + ballSize >= height:  #reached the bottom start going up
         fallingDown = false 
      elif yPosition <= startYPosition:  #reached the top - start going down
         fallingDown = true 

         
   #now make the movie
   movie=makeMovieFromInitialFile(directory + "/gbFrame000.jpg")
   print(movie) # just shows the name and path of movie
   
   return movie
   
def makeGreenBallMovie():
   # This function test the making of an avi movie/animation
   # the function "makeGreenBallFrames" creates a directory full of jpg pictures to be
   # used as frames in the animation 
   # the frames, like the movie, is created in the current media path - so you might want to
   # create a diurectory to store the movie and frames in and set this as the media path
   # before you run the function
   
   # setMediaPath()  # select the mediaPath
   movie = makeGreenBallFrames()
   playMovie(movie)
   
   #save movie to avi format
   movieFilename = getMediaPath() + "greenBall.avi"
   print (movieFilename)
   writeAVI(movie, movieFilename)  #save the avi file


#######################################################################################################
####        
#### 4. (T) Write a function that creates a movie shows a picture being turned upside down. 
####        Each frame should show two rows at a time being swapped over 
####        (for example one row at the top being swapped with one row at the bottom). 
####        Be careful - this could be a lot of frames if you choose a high picture. 
####         
#######################################################################################################

def makeSwapRowFrames(picture):
   # This function creates and returns a short movie that shows a picture
   # (passed as a parameter) that fades to black during the movie 
   # The frames for the movies consist of a  number of jpeg pictures.
   # Note: probably best to use a small picture as this might take a while
   #
   # Note: I first created a directory called "FadingMovie" and set this as the
   #       the media path - this ensures the picture files are placed in this directory.

   directory = getMediaPath()
   print("directory=" + directory)

   numberOfSwaps = getHeight(picture) / 2  
   rowA = 0
   rowB = getHeight(picture)-1
   
   #number frames depends on height of original picture
   # eg. 30 pixels high - you need to swap 15 rows
   # eg. 31 pixels high - still 15 swaps - but the middle row stays the same
   for n in range(0,numberOfSwaps): 
      swapPicture(picture, rowA, rowB)
      rowA = rowA + 1
      rowB = rowB - 1
    
      numberString=str(n)  #this is used to number the jpg frames from 00-50
      # these selection statements ensure that filenames have the same length
      # and will end up being sorted in alphabetical order from 
      # fpFrame00.jpg to fpFrame50.jpg
      if n < 10:
         writePictureTo(picture, directory + "/sfFrame00" + numberString + ".jpg")
      elif n < 100:
         writePictureTo(picture, directory + "/sfFrame0" + numberString + ".jpg")
      else:
         writePictureTo(picture, directory + "/sfFrame" + numberString + ".jpg")
         
   movie=makeMovieFromInitialFile(directory + "/sfFrame000.jpg")
   return movie

def swapPicture(picture, rowA, rowB):
   # This function alters the picture (side effect)
   # by swapping rowA pixel values with rowB pixel values
   
   for x in range(0,getWidth(picture)): #process all x values
      pixelA = getPixelAt(picture, x, rowA)
      pixelB = getPixelAt(picture, x, rowB)
      #save the current row A values
      redA   = getRed(pixelA)
      greenA = getGreen(pixelA) 
      blueA  = getBlue(pixelA)
      
      #reset the rowA to have rowB values
      setRed(pixelA, getRed(pixelB))
      setGreen(pixelA,  getGreen(pixelB))
      setBlue(pixelA, getBlue(pixelB))
      
      #now set the rowB values to the saved rowA values
      setRed(pixelB, redA)
      setGreen(pixelB,  greenA)
      setBlue(pixelB, blueA)
 

   
def makeSwapMovie():
   # This function test the making of an avi movie/animation
   # the function "makeSwapRowFrames" creates a directory full of jpg pictures to be
   # used as frames in the animation 
   # the frames, like the movie, is created in the current media path - so you might want to
   # create a diurectory to store the movie and frames in and set this as the media path
   # before you run the function
   file = pickAFile()
   picture = makePicture(file)
   movie = makeSwapRowFrames(picture)
   playMovie(movie)

   #save movie to avi format
   movieFilename = getMediaPath() + "swapRowFrames.avi"
   print (movieFilename)
   writeAVI(movie, movieFilename)  #save the avi file
