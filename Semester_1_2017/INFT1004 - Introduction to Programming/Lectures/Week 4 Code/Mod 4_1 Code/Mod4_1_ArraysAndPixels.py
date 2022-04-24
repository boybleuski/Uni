###  Programmer : Keith Nesbitt
###  Date: 23 March 2015
###  This  program illustrates the use of the getPixels function
###  with the for statement
###  >>> testHalfRed(1) 
###  >>> testHalfRed(2)
###  >>> testCyanBlock() 
###  >>> cyanBlock(picture)

def testHalfRed(option):
   # This function can be used to test the half red function
   # option is an integer with a value of either 1 or 2
   # the first option processes all the pixels with getPixels
   # the second option uses the range function to do the same thing
 
   myFile = pickAFile()
   myPicture = makePicture(myFile)
   show(myPicture)
   
   if option == 1:
      # process all the pixels - half the red value
      # use the getPixels function
      for myPixel in getPixels(myPicture):
         value = getRed(myPixel)
         setRed(myPixel, value * 0.5)
   else: # (option == 2):  
      # process all the pixels - half the red value
      # use the range function
      arrayPixels = getPixels(myPicture) 
      numPixels = len(arrayPixels)
      for i in range(0, numPixels):
         myPixel = arrayPixels[i]
         value = getRed(myPixel)
         setRed(myPixel, value * 0.5)
      
   repaint(myPicture) 
   
def testCyanBlock():
    ## this function tests the cyanBlock function 

   myFile = pickAFile()
   myPicture = makePicture(myFile)
   show(myPicture)
   
   cyanPicture = cyanBlock(myPicture)
   repaint(cyanPicture) 

def cyanBlock(picture):
    ## this function returns a copy of the picture
    ## with half the picture coloured cyan
    
    copyPicture = duplicatePicture(picture)
    pixelArray = getPixels(copyPicture)
    numPixels = len(pixelArray)
    
    #process only the first half of the pixels 
    for i in range(0, numPixels/2):
       setColor(pixelArray[i], cyan)

    return copyPicture 
    
def doubleGreen(picture):
   # This function doubles the current green value of each pixel
   #
   # This all assumes the Modulo pixel value is not selected
   #
   # It has no side effects - so picture is not changed
   # it returns a copy of the picture with the green channel changed.

   newPicture = duplicatePicture(picture)
   
   myPixels = getPixels(newPicture)
   for pixel in myPixels:
      greenValue = getGreen(pixel)
      setGreen(pixel, greenValue * 2)

   return newPicture

def adjustBlue(picture, amount):
   # this function adjusts the current blue value of each pixel
   # it changes the current value by multiplying by "amount"
   # this it can be used to increase bliue (if amount > 1)
   # or reduce the amount of blue (if amount < 1)
   #
   # This all assumes the Modulo pixel value is not selected
   #
   # It has no side effects - so picture is not changed
   # it returns a copy of the picture with the green channel changed.

   newPicture = duplicatePicture(picture)
   
   pixels = getPixels(newPicture)
   for pixel in pixels:
      blueVal = getBlue(pixel)
      setBlue(pixel, blueVal * amount)
      
   return newPicture


def negatePicture(picture):
   # this function returns a negative image of the picture
   #
   # It doesn't matter if the Modulo pixel value is selected or not (why?)
   #
   # It has no side effects - so picture is not changed
   # it returns a copy of the picture with the green channel changed.

   negativePicture = duplicatePicture(picture)
  
   pixels = getPixels(negativePicture)
   
   #just subtract current value from 255 for each channel
   for pixel in pixels:
     setRed(pixel, 255  - getRed(pixel))
     setGreen(pixel,255 - getGreen(pixel))
     setBlue(pixel, 255 - getBlue(pixel))
     
   return negativePicture
