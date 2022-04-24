#######################################################################################################
## Programmer: Keith Nesbitt
## Date: 3 April, 2017
##
## This program contains solutions (or suggested solutions)
## for the problems from the tutorial in Week 7 / Week 8
##
## Question 2  - splitString()
## Question 3  - testSimpleFlags(), createFrenchFlag(width)
## Question 4  - testSimpleFlags(), createAboriginalFlag(width)
## Question 5  - testDrawShips(), drawShips(shipList)
## Question 6  - testPrintBuildings(), printBuildings(tallBuidlingFiename, informationType) 
## 
#######################################################################################################

####-------------- Week 7 - Q2 -------------------------------------------------
def splitString():
    # This functions splits a string using the split() function defined for strings
    # In this case the string is psly around the "." character.
    # The list produced is printed to check the result
    
    testString = "abc.def.ghi.jkl.mno.pqr.stu.vwx.yz"
    stringList = testString.split(".")
    
    print "stringList = " + str(stringList) # note that the str function also works for lists
    
    #print each element of the list
    for item in stringList:
       print item
       
       

####-------------- Week 7 - Q3 and Q4 ------------------------------------------
def testSimpleFlags():
   # This function helps test my flag drawing functions
   # It allows the width of the flag to be specified
   # So far it draws the French flag of France the Aboriginal Flag

   #test Q3 0 French flag drawing - width 300
   frenchFlag = createFrenchFlag(300) 
   show(frenchFlag)

   #test Q3 0 French flag drawing - width 600
   frenchFlag = createFrenchFlag(600)
   show(frenchFlag)

   #test Q4 - Aboriginal flag drawing - width 180
   aboriginalFlag = createAboriginalFlag(180) 
   show(aboriginalFlag)

   #test Q4 - Aboriginal flag drawing - width 700
   aboriginalFlag = createAboriginalFlag(700) 
   show(aboriginalFlag)

#----------------------------------------------------------------------------------------------------------
#creates and returns a picture of the French flag with the specified "width" 
#the height of the flag is determined based on the flag specifications
#Specifications for this flag come from
# http://en.wikipedia.org/wiki/Flag_of_France
# Proportion	2:3
# Adopted	First as ensign : 15 February 1794 ; As Napoleon army flag : 1812 Readopted July 1830
# Design	         A vertical tricolour of blue, white, and red.
#----------------------------------------------------------------------------------------------------------
def createFrenchFlag(width):

  # Notice the colours are specified as correctly as possible 
  colourWhite = makeColor(255,255,255)
  colourBlue  = makeColor(0,85,164)    # (0,85,164)
  colourRed   = makeColor(250,60,50)   # (250,60,50)
  
  # calculate height using width - ignore small rounding errors
  height = int((width / 3.0) * 2) #use float division for the 3
  
  #create the flag - I used the white so I didn't have to draw it later 
  flag = makeEmptyPicture(width, height, colourWhite)
  
  thirdWidth = width / 3
  addRectFilled(flag, 0,0, thirdWidth, height, colourBlue)
  addRectFilled(flag, width-thirdWidth, 0, thirdWidth, height, colourRed)
  
  return flag


#-------------------------------------------------------------------------------------------------------------
#creates and returns a picture of the Aboriginal flag with the specified "width" 
#the height of the flag is determined based on the flag specifications
#Specifications for this flag come from
# http://en.wikipedia.org/wiki/Australian_Aboriginal_Flag
#Proportion	2:3 [1]
# Design	         It is horizontally divided into a black region (above)
#                 and a red (Pantone 179) region (below). 
#                 A yellow (Pantone 123) disc (half the height) is superimposed over the centre of the flag.
#-------------------------------------------------------------------------------------------------------------
def createAboriginalFlag(width):
  colourBlack = makeColor(0,0,0)
  colourRed = makeColor(216,30,5)      #Pantone 179 = (216,30,5)
  colourYellow = makeColor(230,196,20) #Pantone 123 = (230,196,20)
  
  # calculate height using width - ignore small rounding errors
  height = int((width / 3.0) * 2) #use float division for the 3
  
  #create the flag - I used the black so I didn't have to draw it later 
  flag = makeEmptyPicture(width, height, colourBlack)
  
  #draw the red bottom part of flag
  halfHeight = height /2
  addRectFilled(flag, 0, halfHeight, width, halfHeight, colourRed)
  
  #draw the yellow circle
  #calculate diameter and the top left corner position (X, Y)
  circleWidth = halfHeight
  circleX = (width / 2) - (circleWidth/2)
  circleY = (height/2) - (circleWidth/2)
  addOvalFilled(flag, circleX, circleY, circleWidth,circleWidth,colourYellow)

  return flag

####-------------- Week 7 - Q5 ------------------------------------------
def testShipDraw():
   # This function tests the ship list drawing function.
   # drawShips(shipList)
   
   listOfShips = [["Seawise Giant",  "Oil tanker", 458],  ["Barzan",  "Container ship", 400], ["Pioneering Spirit", "Crane vessel", 382], ["Valemax", "Bulk carrier", 360], ["Oasis of the Seas",  "Cruise ship", 362], ["Queen Mary 2", "Ocean liner", 345]]
   
   print listOfShips
   
   testPrintList(listOfShips)  # this function I just write to help test the list 
   
   pictureShips = drawShips(listOfShips)
   show(pictureShips)


def testPrintList(listOfItems):
    ### This test function is just used to print a few random elements of the list
    ### It will only work if the list is made up of lists (sublists) with at least 3 elements
    ### It cam help you understand how to access varopius elements in a list of lists
    print "list[0][0] = " + str(listOfItems[0][0])
    print "list[0][1] = " + str(listOfItems[0][1])
    print "list[0][2] = " + str(listOfItems[0][2])
    print "list[4][2] = " + str(listOfItems[4][2])
    print "list[5][1] = " + str(listOfItems[5][1])


def drawShips(shipList):
    # this function draws a lot of ships diagram
    # I set up some sizes as constant-like variables
    # These were just worked out on paper and a bit of trial and error
    # Later I will adapt this code to use a width parameter - see drawTrainDiagramWidth(listTrains, width)
 
    DIAGRAM_SHIPMAX = 600          # allows 600 pixels wide for drawing - seems like a reaonable size 
    DIAGRAM_BORDER = 20            # allow a 20 pixels border around the ship drawings
    DIAGRAM_WIDTH = DIAGRAM_SHIPMAX + (2 * DIAGRAM_BORDER)      # picture width - note border on left and right 
    
    SHIP_HEIGHT = 60           # height of each ship
    SHIP_GAP = 50              # gap between each ship (plus one at top and one at bottom)

    numberOfShips = len(shipList)   # how many ships need drawing
    DIAGRAM_HEIGHT = (numberOfShips * SHIP_HEIGHT) +  (numberOfShips + 1) *  SHIP_GAP    
    
    # now create the blank diagram - make it white
    diagram = makeEmptyPicture(DIAGRAM_WIDTH, DIAGRAM_HEIGHT)
    
    # First find the biggest ship so we use up the full width to draw it
    # Note: I wrote another function to solve this step
    maximumShipSize = calculateMaximumShip(shipList)
    
    # make sure the maximum ship size only takes up what ever the width is  
    #calculate how many pixels represent 1 metre 
    #the longest ship should take up all pixels (DIAGRAM_SHIPMAX)
    unitLength = float(DIAGRAM_SHIPMAX) / maximumShipSize  #force to float arithmetic to avoid rounding errors
    
    x = DIAGRAM_BORDER
    y = SHIP_GAP
    shipColour = makeColor(0, 0, 0)  #black 
    
    #draw all the bars - largest ship takes up the full width (DIAGRAM_SHIPMAX)
    for ship in shipList:
       shipWidth = unitLength * ship[2]
       shipWidth = int(shipWidth)  #now turn back to an integer for drawing the ship (floats don't work)
       addOvalFilled(diagram, x, y, shipWidth, SHIP_HEIGHT, shipColour)  # I decide to use ovals as they look a bit shippy
       addText(diagram, DIAGRAM_BORDER + int(shipWidth/2), y + int(SHIP_HEIGHT/2)+ 8, ship[0], white) # I put text kind of in middle
       y = y + SHIP_HEIGHT + SHIP_GAP #where the next ship starts
       
    return diagram
       
def calculateMaximumShip(listShips):
   ### This function calculates the maximum ship size in the list of ships
   ### It assumes the list has at least one ship
   ### as it uses the first ship in the list as the default maximum
   
   maxShipSize = int(listShips[0][2])  #make sure it is an integer
   
   for ship in listShips:
      if int(ship[2]) > maxShipSize:
          maxShipSize = int(ship[2])

   print "Max Ship Size = " + str(maxShipSize)
   
   return maxShipSize

####-------------- Week 7 - Q6 ------------------------------------------
def testPrintBuildings():
   ### This function is used to test the printBuildings function
   ### Note that the building information is read from a csv file
   ### csv (comma sepearated values) is a common excel data format
   
   file = pickAFile() # select the csv file 

   # now test the various type sof information required

   printBuildings(file, 1) # type 1 is building names and their city and country in rank order
   
   printBuildings(file, 2) # type 2 is building names and their height in rank order
   
   printBuildings(file, 3) # type 3 is building names and number of floors in rank order
   
   printBuildings(file, 4) # type 4 is year of building and building name in year order
   

def printBuildings(tallBuildingFilename, informationType):
   ### this function prints the buildings in the file in different ways
   ### the different ways depend on the "informationType" required...
   ###    type 1 is building names and their city and country in rank order
   ###    type 2 is building names and their height in height order
   ###    type 3 is building names and number of floors in number floor order
   ###    type 4 is year of building and building name in year order
   ###
   ### The function assumes the information in the csv file is in a certain order
   ###
   
   # first read the file
   file = open(tallBuildingFilename, "rt")  # open the text file for reading
   lineList = file.readlines()     # read the text as a list - each element is a line
   
   
   #3. Copy all the buildings into the list  (one per line)
   buildingList =[] #start with an empty list
   
   #print "length=" + str(len(lineList))  #used for debugging to check how many things in list - 11
   
   skip = 0
   for line in lineList:
      lineString = line.replace("\n","")   # I had to remove newlines on my Mac 
      lineString = lineString.replace("\r","")   # I had to remove carriage returns on my Mac
      if skip <> 0: #skip the first line as it is just the header
         building = lineString.split(",") # - split by commas - this creates a list for the building
         buildingList.append(building) #add this list to the building list 
      else: #skip is 0 so change to 1 - I guess I could have used a boolean here
         skip = 1 # OK don't skip anymore
   
  
   #print("buildingList = " + str(buildingList)) #print the list just to check
   
   file.close()                 # close the file
   
   # based on the type do the printing - I found it easier to do all this in separate functions
   if informationType == 1:
      printType1Buildings(buildingList)
   elif informationType == 2:
      printType2Buildings(buildingList)
   elif informationType == 3:
      printType3Buildings(buildingList)
   else: # informationType == 4:  #Note: This shouldn't be anything else but 4
      printType4Buildings(buildingList)


def printType1Buildings(listBuildings):
    # this function print information about buildings 
    # it assumes the buildings are in rank order in the list
    print "-------- type 1 ----------------------------"
    
    for building in listBuildings:
       print building[1] + ", " + building[2] + ", " + building[3]

    print "--------------------------------------------"

def printType2Buildings(listBuildings):
    # this function print information about buildings 
    # it first sorts the buildings are in height order in the list
    
    print "-------- type 2 ----------------------------"
    for building in listBuildings:
       print building[1] + ", height=" + building[4] + " m" 

    print "--------------------------------------------"

def printType3Buildings(listBuildings):
    # this function print information about buildings 
    # it assumes the buildings are in rank order in the list
    
    print "-------- type 3 ----------------------------"
        
    for building in listBuildings:
       print building[1] + ", floors=" + building[5] 
       
    print "--------------------------------------------"
        

def printType4Buildings(listBuildings):
    # this function print information about buildings 
    # the buildings are not stored in date order so I first create a new list that I sort
    # note the sorting works on teh first element of list so I create a list of [year, buildingName]
    
    print "-------- type 4 ----------------------------"
    yearBuildingList = [] # start with an empty list
    
    for building in listBuildings:
       listElement = []   # this is just teh current element for the list
       listElement.append(building[6]) #- this is year
       listElement.append(building[1]) #- this is name of building
       yearBuildingList.append(listElement) # ok add this list to the bigger list

    yearBuildingList.sort() # this should sort the list based on the first bit - year
    
    for building in yearBuildingList:
       print str(building[0]) + ", " + building[1]   # year followed by name

    print "--------------------------------------------"

    
    