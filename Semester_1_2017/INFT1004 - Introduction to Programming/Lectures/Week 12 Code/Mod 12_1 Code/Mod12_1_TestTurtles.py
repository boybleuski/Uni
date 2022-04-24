###  Programmer : Keith Nesbitt
###  Date: 17 May 2017
###  This program illustrates how to use Turtles in JES
###  It also uses the SmartTurtle class that inherits from the Turtle class  
###  Everythings is coded using dot notation - standard for object oriented programming
###
###  >>> testTurtles()                # Turtle class
###  >>> testTurtlesSmart()           # SmartTurtle class (need to load Mod12_1_SmartTurtleClass.py first)
###  >>> testTurtlesSmartSwirl()      # SmartTurtle class (need to load Mod12_1_SmartTurtleClass.py first
### 
def testTurtles():
   # This function creates a turtle (and a World) for the Turtle
   # It then calls some turtle commands

   earth = World()         # make a World for the Turtle to live in (World constructor)
   liner = Turtle(earth)   # create a Turtle in that World  (Turtle constructor)
   
   #These commands draw a red triangle
   liner.setColor(red)
   liner.turn(135)
   liner.forward()
   liner.turn(135)
   liner.forward(141)
   liner.turn(135)
   liner.forward()
   
   # move the Turtle with it's pen up to a new position
   liner.penUp()
   liner.turn(-80)
   liner.forward(80)
   liner.penDown()
   
   #draw a little green square
   liner.setColor(green)
   liner.forward(50)
   liner.turnLeft()
   liner.forward(50)
   liner.turnLeft()
   liner.forward(50)
   liner.turnLeft()
   liner.forward(50)

   # move the turtle with it's pen up to a new position
   liner.penUp()
   liner.forward(80)


def testTurtlesSmart():
   # This function creates a SmartTurtle (and a World) for the SmartTurtle
   # It then calls some SmartTurtle commands   
   # Note: You will also need to load the class code from Mod12_1_SmartTurtleClass.py
   # before you try to run this

   myWorld = World()               # make a World for the SmartTurtle to live in (World constructor)
   leonardo = SmartTurtle(myWorld) # create a SmartTurtle in that World  (SmartTurtle constructor)
   
   # this command draw a square
   leonardo.drawSquare()

   # this command draw a 12 sided blue star (after moving forward a bit)
   leonardo.penUp()        #lift up pen so it doesn't leave a line
   leonardo.forward()
   leonardo.forward()
   leonardo.setColor(blue) #make the pen blue
   leonardo.penDown()      #put pen down so it does leave a line
   leonardo.drawStar(12)

 
def testTurtlesSmartSwirl():
   # This function creates a SmartTurtle (and a world) for the SmartTurtle
   # It then calls the SmartTurtle pictureSwirl command
   # Note: You will also need to load the class code from Mod12_1_SmartTurtleClass.py
   # before you try to run this

   myWorld = World()               # make a World for the SmartTurtle to live in (World constructor)
   leonardo = SmartTurtle(myWorld) # create a SmartTurtle in that world  (SmartTurtle constructor)
   
   # this command reads a picture for the SmartTurtle to swirl it
   file = pickAFile()
   picture = makePicture(file)
   leonardo.pictureSwirl(picture)

 
   

   