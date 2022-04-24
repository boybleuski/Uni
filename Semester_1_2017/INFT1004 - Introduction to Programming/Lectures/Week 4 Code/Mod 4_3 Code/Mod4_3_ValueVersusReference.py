###  Programmer : Keith Nesbitt
###  Date: 20 Mar 2015
###  This  program demonstrates value and reference copying in JES
###  and also side effetcs when using objects as arguments
###
###  >>> testValueReferenceCopying() 
###  >>> testSideEffect() 
###  >>> testNoSideEffect() 


def testValueReferenceCopying():
   # Original code - Simon, August 2012
   # Illustrate the difference between value and reference copying
   #
   # Modifications in variable names and comments etc - Keith Nesbitt, March 2015

   # VALUE COPYING -------------------------------------------------------   
   # Value copying: the 'copy' gets the value of the original, 
   # but they remain different things
   # Value copying happens with simple data types 
   # This example uses 2 integers a and b
   a = 3
   b = a      # copy value of a into b
   b = 5      # change the value of b (a is not changed)
   print a    # We haven't altered a, so it retains it original value
   print b    # But we have altered b - and b and a are different things


   # REFERENCE COPYING ---------------------------------------------------  
   # Reference copying: the 'copy' is another name for the same object
   # Reference copying happens with object data types 
   # This example uses 2 pictures - picture1 and picture2
   # it also demonstrates reference copying with 2 pixels - pixel1 & pixel2

   pictureFile = pickAFile()
   picture1 = makePicture(pictureFile)
   show(picture1)
  
   # Objects like pictures are copied by reference; 
   # picture1 & picture1 are now exactly the same object
   picture2 = picture1 
  
   # pixel1 is a pixel of picture1 (and therefore picture2)
   pixel1 = getPixel(picture1, 0, 0) 
   setColor(pixel1, green)
   repaint(picture2) 
  
   #pixels are also objects so it also uses reference copying
   pixel2 = pixel1         # pixel2 is now another name for pixel1
   setColor(pixel2, blue)  # change color of pixel2
   print pixel1            # Yes, we made pixel1 green originally but now it is blue
   print pixel2            # So pixel1 is blue, because it's the same object as pixel2
  
  
def testSideEffect():
   ### This function demonstrates
   ### side effects in python
   pictureFile = pickAFile()
   myPicture = makePicture(pictureFile)
   explore(myPicture)  

   ### this function has a side effect
   ### It changes the argument
   ### The explore is used to 
   drawRedSquare(myPicture)
   explore(myPicture) #now has a red square

def testNoSideEffect():
   ### This function demonstrates
   ### how to avoid side effects in python
   pictureFile = pickAFile()
   myPicture = makePicture(pictureFile)
   explore(myPicture)  

   ### this function has no side effects
   ### the argument remains unchanged
   ### the function returns a changed copy of the argument
   newPicture =  drawRedSquareFix(myPicture)
   explore(myPicture)   #no red square on original
   explore(newPicture)  #copy of picture with a red square


def drawRedSquare(picture):
   ### This function draws a red square
   ### of 4 pixels on the picture
   ### It has a side effect
   ### The original picture argument is changed
   ###
   ### Assumes the pictures width is greater than 12 
   ### and that its height is greater than 12
   
   #draw the 2 x 2 red square 
   pixel1 = getPixel(picture, 10, 10)
   setColor(pixel1 , red)
   pixel2 = getPixel(picture, 11, 10)
   setColor(pixel2 , red)
   pixel3 = getPixel(picture, 10, 11)
   setColor(pixel3 , red)
   pixel4 = getPixel(picture, 11, 11)
   setColor(pixel4 , red)
   

def copyDrawRedSquare(picture):
   ### This function returns a copy of 
   ### picture with a red square on it
   ### There are no side effects
   ### The original picture argument is NOT changed
   ### 
   ### Assumes the pictures width is greater than 12 
   ### and that its height is greater than 12

   #copy the original picture
   resultPicture = duplicatePicture(picture)
   
   #draw the red square as 2 x 2 pixels
   #on the copied picture
   pixel1 = getPixel(resultPicture, 10, 10)
   setColor(pixel1, red)
   pixel2 = getPixel(resultPicture, 11, 10)
   setColor(pixel2, red)
   pixel3 = getPixel(resultPicture, 10, 11)
   setColor(pixel3, red)
   pixel4 = getPixel(resultPicture, 11, 11)
   setColor(pixel4, red)
   
   return resultPicture
