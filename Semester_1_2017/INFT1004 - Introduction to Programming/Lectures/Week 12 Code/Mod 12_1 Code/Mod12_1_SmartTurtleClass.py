###  Programmer : Simon
###  Date: October 2012
###  Modifications: Keith Nesbitt
###  May 2017
###  This  program illustrates the definnition of a class
###  In this example the SmartTurtle class is created as a 
###  subclass of the Turtle class
###  Thus it inherits all functionality of the Turtle class
###  it also defines three new methods for the Smartturtle class
###  drawSquare()
###  drawStar(points)
###  picSwirl(picture)

class SmartTurtle(Turtle):
  # SmartTurtle class is a subclass of Turtle
  # It 'inherits' all the features of Turtle, 
  # such as the forward() and turn() methods,
  # and the position and colour properties

   def drawSquare(self):
   # The SmartTurtle draws a square; self is the necessary parameter
      for i in range(0,4):
         self.turnRight()
         self.forward()

   def drawStar(self, points):
   # The SmartTurtle draws a star with the specified number of points -
   # at least, it does for some numbers, such as 4, 12, and 20
      for i in range(0, points):
         self.turn(180 - 360 / points)
         self.forward(150)
  
   def pictureSwirl(self, picture):
   # Drop a picture a number of times, turning and moving after each drop
   # I think 360 times, as in the book, is slight overkill
      for i in range(0, 20):
         self.drop(picture)
         self.forward(10)
         self.turn(20)
  
   # Note that this is the end of class SmartTurtle - the indentation makes it so

