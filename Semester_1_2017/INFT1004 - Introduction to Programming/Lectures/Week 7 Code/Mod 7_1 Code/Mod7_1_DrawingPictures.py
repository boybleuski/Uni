###  Programmer : Keith Nesbitt
###  Date: 5 April 2017
###  This  program illustrates some basic drawing functions in JES
###  >>> testDrawRegular() 
###  >>> testSimpleFlags() 

def drawRegularVerticalLines(width):
   # This function creates and returns a yellow picture with 
   # black grid lines - spaced at regular intervals 
   # The last space may not be quite regular- this depends on width
   # The height of the picture is roughly 1/4 of the width
   
   height = int(width / 4)  #needs to be an integer number of pixels
   
   # make an empty yellow picture
   newPicture = makeEmptyPicture(width, height, yellow)
   
   #Use the range function (with 3 arguments) to
   #draw a line at regular intervals - I choose every 5 spaces
   #note that the spacing needs to be an integer
   space = 5
   
   for x in range(0, width, space):  #start at 0, don't go past width
      # only x changes each time through the loop, the y coordinate is from 0 to height-1
      # you need the -1 because pixels are numbered from 0 to height-1 
      addLine(newPicture, x, 0, x, height-1, black)
   
   return newPicture

def testDrawRegular():
   ### This function is used to test the function
   ### drawRegularVerticalLines(width)
   myPicture1 = drawRegularVerticalLines(300)
   show(myPicture1)
   
   myPicture2 = drawRegularVerticalLines(334) #not divisible by 5
   show(myPicture2)

   myPicture3 = drawRegularVerticalLines(200)
   show(myPicture3)


def testSimpleFlags():
   # This function draws some simple flags - allows the width of the flag to be specified
   # So far it draws the flags of Sweden, Germany, France, Japan, and the Aboriginal Flag

   germanFlag = createGermanFlag(200) 
   show(germanFlag)
  
   germanFlag = createGermanFlag(500) 
   show(germanFlag)
  
   swedishFlag = createSwedenFlag(280)
   show(swedishFlag)

   swedishFlag = createSwedenFlag(450)
   show(swedishFlag)

   #frenchFlag = createFrenchFlag(300)
   #show(frenchFlag)      

   #aboriginalFlag = createAboriginalFlag(700) 
   #show(aboriginalFlag)


def createGermanFlag(width):
   #creates and returns a picture of the German flag with the specified "width" 
   #the height of the flag is determined based on the flag specifications
   #Specifications for this flag come from 
   # http://en.wikipedia.org/wiki/Flag_of_Germany
   # Proportion	3:5
   # Adopted	23 May 1949
   # Design	         A horizontal tricolour of black, red, and gold.
   
   #set up the colours I need 
   colorBlack = makeColor(0,0,0)
   colorRed = makeColor(216,30,5)
   colorGold = makeColor(230,196,20)
  
   #calculate the height of the flag (3/5 of width)
   # and height of the stripes (1/3 height)
   # I use floating point arithmetic (by making sure one part is a float - like 5.0)
   # This makes sure I avoid rounding errors.
   # Eventually I need integers as all the drawing functions use integers (not floats)
   height = (width * 3) / 5.0      #use float arithmetic to avoid rounding errors
   thirdHeight = int(height / 3)   #height is still a float but I need to convert answer to int
   height = int(height)            #Now convert height to integer so I can use it in drawing functions

   #mqke  the flag as an empty picture (all gold - save drawing this strip later)
   flag = makeEmptyPicture(width, height, colorGold)
  
   #add the black and red stripes
   addRectFilled(flag, 0, 0, width, thirdHeight, colorBlack)
   addRectFilled(flag, 0, thirdHeight, width, thirdHeight, colorRed)
  
   return flag


def createSwedenFlag(width):
   #creates and returns a picture of the Swedish flag with the specified "width" 
   #the height of the flag is determined based on the flag specifications
   #Specifications for this flag come from
   # http://en.wikipedia.org/wiki/Flag_of_Sweden
   # Proportion	5:8 or 10:16
   # Adopted	June 22, 1906
   # Design	         Blue with a yellow/gold Scandinavian cross that extends to the edges of the flag. 
   # Dimensions:     5:2:9 horizontally and 4:2:4 vertically.  
   # for colurs see http://commons.wikimedia.org/wiki/File_talk:Flag_of_Sweden.svg
   cBlue  = makeColor(0, 91, 153)       
   cYellow = makeColor(252, 209, 22)  
  
   height = int((width / 16.0) * 10) 
   flag = makeEmptyPicture(width, height, cBlue)
  
   oneSixtenth = width / 16.0 
   stripeSize = int(2 * oneSixtenth)
   xPos = int(oneSixtenth * 5) 
   yPos = 0
  
   addRectFilled(flag, xPos , yPos, stripeSize, height, cYellow)

   oneTenth = height / 10.0 
   xPos = 0
   yPos = int(oneTenth *4)
   addRectFilled(flag, xPos, yPos, width, stripeSize, cYellow)
  
   return flag

