###  Programmer : Keith Nesbitt
###  Date: 20 April 2015
###  This  program illustrates some advanced picture processing
###  >>> testTransparentOverlay() - transparency(picture1, picture2, xStart, yStart, proportion)
###  >>> testIrradiate() -  irradiate(picture)
###  >>> testFade() - fadeDown(picture)

def testTransparentOverlay():

   # This function reads in two pictures and produces a new picture that
   # is made by copying picture 1 and then overlaying picture 2 on top
   # of picture 1. (It is placed in the centre of the image)
   # This requires the width and height of picture 2 to be less than 
   # the width and height of picture 2 (so it fits). So it checks 
   # that the size of each image is correct before creating the new picture.

   # 1. Get the two pictures and show them  
   file = pickAFile()
   picture1 = makePicture(file)
   file = pickAFile()
   picture2 = makePicture(file)
   show(picture1)
   show(picture2)
   
   #2. Test the size of the pictures - picture 2 needs to fit inside
   #   picture 1 (so check width and height)
   
   if getWidth(picture2) > getWidth(picture1):
      errorMessageWidth = "ERROR: The width of picture2 is greater than picture1"
      print(errorMessageWidth)
   elif getHeight(picture2) > getHeight(picture1):
      errorMessageWidth = "ERROR: The width of picture2 is greater than picture1"
      print(errorMessageWidth)
   else: #do the overlay and show the result
      #work out the position for overlaying first
      xMargin = (getWidth(picture1) - getWidth(picture2)) / 2
      yMargin = (getHeight(picture1) - getHeight(picture2)) / 2
      proportion = 60 #try 60% transparency
      picture3 = transparency(picture1, picture2, xMargin, yMargin, proportion)
      show(picture3)
   

def transparency(picture1, picture2, xStart, yStart, proportion):
     # overlays picture 2 in the centre of picture 1 with 
     # a transparency of proportion (%)
     # returns a new picture - copy of picture 1 with the overlay 
     # (there are no side effects)
     
     picture3 = duplicatePicture(picture1)
     
     # calculate the proportions of overlay and the base image
     # keep any decimal places until after calculations
     overlay = (proportion) / 100.0   # convert overlay from percentage to a fraction 
                                      # eg. 60% is 60/100 which is 0.6 
     base = (100-proportion) / 100.0  # convert base amount from percentage to a fraction 
                                      # eg. if overlap is 60% is (100-60) / 100 which is 0.5  
     
     #Copy all the pixels from picture 2 onto the new picture 3
     for x in range(0, getWidth(picture2)):
        for y in range(0, getHeight(picture2)):  
           pixel2 = getPixel(picture2, x, y)                # pixel to copy (and colours)
           pixel2Red   = getRed(pixel2)
           pixel2Green = getGreen(pixel2)
           pixel2Blue  = getBlue(pixel2) 
           
           pixel3 = getPixel(picture3, x+xStart, y+yStart)  # overlap pixel (and colours)
           pixel3Red   = getRed(pixel3)
           pixel3Green = getGreen(pixel3)
           pixel3Blue  = getBlue(pixel3)
           
           #combine the colours in correct proportions - convert back to integers
           newRed =   int(pixel2Red * overlay)   + int(pixel3Red * base)
           newGreen = int(pixel2Green * overlay) + int(pixel3Green* base)
           newBlue =  int(pixel2Blue * overlay)  + int(pixel3Blue * base)
           
           #set the new colours for picture3
           setRed(pixel3, newRed)
           setGreen(pixel3, newGreen)
           setBlue(pixel3, newBlue)

        
     return picture3

def testIrradiate():
   # This function test the irradiate function.
   # Note : you should try it with a picture with whiet in it - try swan.jpg
   file = pickAFile()
   picture = makePicture(file)
   show(picture)
   
   changedPicture = irradiate(picture)
   show(changedPicture)

def irradiate(picture):
   # Take pixels that are near enough to
   # white and make them very green
   # This function has a side efect - the picture is changed
   newPicture = duplicatePicture(picture)
   
   for pixel in getPixels(newPicture):
      pixelColour = getColor(pixel)

      if distance(pixelColour, white) < 270: # Found distance of 270 by trial & error
         setBlue(pixel, getBlue(pixel)/ 2)
         setRed(pixel, getRed(pixel) / 2)
         setGreen(pixel, 190)
         
   return newPicture
   
def testFade():
   # This function test the fadeDown function.
   
   file = pickAFile()
   picture = makePicture(file)
   show(picture)
   
   changedPicture = fadeDown(picture)
   show(changedPicture)

def fadeDown(picture):
   # Fade picture2 from top to bottom, 
   # rom 0 transparency at the top to 100% transparency at the bottom
   # Start at coordinates xstart, ystart in pic1
  
   pictureFade = duplicatePicture(picture) # start with a copy - avoid side effects
  
   # Starting with y in thsi nested loop 
   # this means we are going row by row and
   # so we only need to calculate the proportion of fading once for each row/
   for y in range(0, getHeight(pictureFade)):
      proportion = 100.0 * y / getHeight(pictureFade) #this will work out the percentage of fading
      whiteProportion = 100.0 - proportion            #this is how much white to add (as a percentage)
      for x in range(0, getWidth(pictureFade)):
         pixel = getPixel(pictureFade, x, y)
 
         # Blend the channels: proportion% of pic1 and (100-proportion)% of pic2
         # remember white is red=255, green=255, blue=255
         newRed =   (getRed(pixel)  * proportion / 100.0) + (255 * whiteProportion / 100.0)
         newGreen = (getGreen(pixel)* proportion / 100.0) + (255 * whiteProportion / 100.0)
         newBlue =  (getBlue(pixel) * proportion / 100.0) + (255 * whiteProportion / 100.0)
         newColour = makeColor(int(newRed), int(newGreen), int(newBlue))
         # And apply this new blended colour to the pixel of pic1
         setColor(pixel, newColour)

   return pictureFade