###  Programmer : Keith Nesbitt
###  Date: 19 Mar 2015
###  This  program introduces colors and pictures in JES
###  including the show(), explore(), makePicture(), 
###  getWidth(), getHeight(), getPixel(), setPixel() functions
###
###  >>> testShowPicture() 
###  >>> testExplorePicture() 
###  >>> testPlayWithPixels()
###  >>> playWithPixels(aPicture)   NOTE: Requires a picture as an argument
###  >>> yellowLine(picture)        NOTE: Requires a picture as an argument
###  >>> testShowPictureDetails()

def testShowPicture():

   # This function selects a picture file and shows it.
   # It also demonstrates the use of the makePicture() function

   #select a jpg file from JES Media Source
   pictureFile = pickAFile()  
    
   #You now have to turn the file path into a Picture
   myPicture = makePicture(pictureFile)
   
   #Note: the "Picture" type is a special class in JES - try printing it
   #Note: 
   
   #it doesn't actually print the picture - just some information about it
   #      the class (or type), the path/filename and the height and width
   print(myPicture)
   
   #now show it - this displays it on screen
   show(myPicture)

def testExplorePicture():

   # This function selects a picture file and explores it.
   # It also demonstrates the use of the makePicture() function

   #select a jpg file from JES Media Source
   pictureFile = pickAFile()  
    
   #You now have to turn the file path into a Picture
   myPicture = makePicture(pictureFile)
   
   #Note: the "Picture" type is a special class in JES - try printing it
   #Note: it doesn't actually print the picture - just some information about it
   #      the class (or type), the path/filename and the height and width
   print(myPicture)
   
   #now show it - this displays it on screen
   explore(myPicture)


def testPlayWithPixels():
   ### This function reads a picture from a file
   ### and is uses it to test the playWithPixels function
   pictureFile = pickAFile()
   picture1 = makePicture(pictureFile)
   playWithPixels(picture1)
   
   
def playWithPixels(aPicture):
   ### This function plays with some pixels
   ### by setting their color
   ### It uses explore() function to test the outcomes
   
   #get 2 pixels
   pixel1 = getPixel(aPicture, 32, 32)
   pixel2 = getPixel(aPicture, 33, 33)
   
   #set their color - one yellow, one blue
   setColor(pixel1, yellow)
   setColor(pixel2, blue)
   
   #use explore to check the outcome
   explore(aPicture)


def yellowLine(picture):
   ### This functiopn draws a yellow line of pixels on the picture
   ### by setting the color of 5 pixels to yellow
   aPixel = getPixel(picture, 40, 30)
   setColor(aPixel , yellow)
   aPixel = getPixel(picture, 41, 30)
   setColor(aPixel , yellow)
   aPixel = getPixel(picture, 42, 30)
   setColor(aPixel , yellow)
   aPixel = getPixel(picture, 43, 30)
   setColor(aPixel , yellow)
   aPixel = getPixel(picture, 44, 30)
   setColor(aPixel , yellow)
   
   
def testShowPictureDetails():

   # This function selects a picture file and shows it.
   # It also shows the use of the makePicture(), getWidth(), getHeight()
   # Two useful JES functions that work with pictures

   pictureFile = pickAFile()  #select a jpg file from JES Media Source
   
   # pictureFile is a string - it has the pathname for the file
   # try printing it
   print("pictureFile = " + pictureFile)
   print("\n")  #just print a new line to make the output neater
   
   #You now have to turn the file path into a Picture
   myPicture = makePicture(pictureFile)
   
   #Note: the "Picture" type is a special class in JES - try printing it
   #Note: it doesn't actually print the picture - just some information about it
   #      the class (or type), the path/filename and the height and width
   print(myPicture)
   print("\n")  #just print a new line to make the output neater

   # These two functions - getWidth, getHeight are quite useful
   # for finding out the size of a picture
   width = getWidth(myPicture)
   height = getHeight(myPicture)
   print("height=" + str(height) + "   width=" + str(width))
   print("\n")  #just print a new line to make the output neater

