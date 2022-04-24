###  Programmer : Keith Nesbitt
###  Date: 7 April 2015
###  This  program illustrates the use of nested loops in pictures
###  >>> testShowLoop(true) OR testShowLoop(false) 
###  >>> testNestedLoop()
###  >>> testCentreBlock()
###  >>> testHorizontalReflection()

def testShowLoop(rowOrder):
   # This function lets the user select a picture 
   # and then uses nested for loops to show the picture being processed (turned yellow) 
   # it has 1 boolean parameter, called rowOrder
   # if rowOrder is true it uses a nested loop - processing one row at a time
   # This means all the x values for each y are processed before moving to the next row (y)
   # If rowOrder is false it works in column order - processing one column at a time
   # This means all y values for each x are processed before moving to the next column(x)
   # rowOrder and columnOreruses teh same code except the for loops are swapped around
 
   file = pickAFile()
   picture = makePicture(file)
   show(picture)

   if rowOrder:
      for y in range(0, getHeight(picture)):      # for each y (row)
         for x in range(0, getWidth(picture)):    # change all the x values before doing next row
            aPixel = getPixel(picture, x, y)
            setColor(aPixel, yellow)
            repaint(picture)
   else: #columnOrder
      for x in range(0, getWidth(picture)):      # for each x (column)
         for y in range(0, getHeight(picture)):  # change all the y values before doing next column
            aPixel = getPixel(picture, x, y)
            setColor(aPixel, yellow)
            repaint(picture)


def testNestedLoop():
   # This function shows the use of a nested loops
   for x in range(0, 4):      #[0,1,2,3]
      for y in range(0, 8):   #[0,1,2,3,4,5,6,7]
         print("(x=" + str(x) + ",y=" + str(y) + ")")


def testCentreBlock():
   # A function that test the centreBlock function
   file = pickAFile()
   picture = makePicture(file)
   
   show(picture)
   centreBlock(picture)
   repaint(picture)


def centreBlock(picture):
  # This function has a side effect
  # It draws a cyan block on the picture passes as an argument
  # This function assumes the picture is larger than 41 pixels wide 
  # and 41 pixels high   
  midx = getWidth(picture) / 2
  midy = getHeight(picture) / 2
  for x in range(midx-20, midx+20):
     for y in range(midy-20, midy+20):
        pixel = getPixel(picture, x, y)
        setColor(pixel, cyan)

def testHorizontalReflection():
   # A function that tests the vertical reflection function
   file = pickAFile()
   picture = makePicture(file)
   
   show(picture)
   reflection = horizontalReflect(picture)
   show(reflection)


def horizontalReflect(picture):
  # This function creates and returns a new picture that 
  # is twice the height of the original
  # the top half of the new picture contains the original picture
  # the bottom half contains the reflection of the original picture

  newPicture = makeEmptyPicture(getWidth(picture), getHeight(picture) * 2)
  
  #copy the original to top half of new picture
  startX = 0
  endX = getWidth(picture)
  startY = 0
  endY = getHeight(picture)
  
  for x in range(startX, endX):
     for y in range(startY, endY):
        oldPixel = getPixel(picture, x, y)     #get the original pixel
        newPixel = getPixel(newPicture, x, y)  #get the pixel to be changed
        # don't copy the pixel itself - because of reference copying
        # use the colours of the pixel which are just integers
        # - so simple types which use value copying
        oldRed   = getRed(oldPixel)
        oldGreen = getGreen(oldPixel)
        oldBlue  = getBlue(oldPixel)
        setRed(newPixel, oldRed)
        setGreen(newPixel, oldGreen)
        setBlue(newPixel, oldBlue)

  #copy the the reflection of original to bottom half 
  newHeight = getHeight(newPicture) #use this to help with reflection
    
  for x in range(startX, endX):
     for y in range(startY, endY):
        oldPixel = getPixel(picture, x, y)     #get the original pixel
        reflectedY = newHeight - 1 - y         #reflected y value - watch out there is no y at newHeight, only newHeight-1
        newPixel = getPixel(newPicture, x, reflectedY)  #get the pixel to be changed
        # don't copy the pixel itself - because of reference copying
        # use the colours of the pixel which are just integers
        # - so simple types which use value copying
        oldRed   = getRed(oldPixel)
        oldGreen = getGreen(oldPixel)
        oldBlue  = getBlue(oldPixel)
        setRed(newPixel, oldRed)
        setGreen(newPixel, oldGreen)
        setBlue(newPixel, oldBlue)

  return newPicture


