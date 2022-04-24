###  Programmer : Keith Nesbitt
###  Date: 10 May 2017
###  This program illustrates the use of the random module
###  and this includes some example of using random numbers
###
###  >>> rollDice()
###  >>> rollDiceSixTimes()
###  >>> pickNumber()
###  >>> pickLottoNumbers()
###
###

#import the random module
from random import *

def rollDice():
   # This function simulates a dice roll
   
   number = randint(1, 6)    
   print(number)   
   

def rollDiceSixTimes():
   # This function simulates six rolls of a dice  
   
   numberOfRolls = 6
   
   count = 1
   
   while count <= numberOfRolls:
      number = randint(1, 6)    
      printNow(number)  
      count = count + 1 


def pickNumber():
   # This function picks a number from a list
   
   listNumbers = [1,1,1,2,3,4,5,6]  #hey this dice looks loaded

   number = choice(listNumbers)    
   printNow(number)  


def pickLottoNumbers():
   # This function picks 6 lotto numbers 
   
   lottoNumbers = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44]  #hey this dice looks loaded

   pickedNumbers = [] # make a list to save the picked numbers
   
   numberToPick = 6 #hw many numbers you want to pick
   
   for i in range(0,numberToPick): 
      lengthLottoNumbers = len(lottoNumbers)       # this will reduce as numbers are picked
      pickIndex = randint(0, lengthLottoNumbers-1) # this picks the index (not theenumber)
      pickNumber = lottoNumbers[pickIndex]         # get the picked number from the list
      pickedNumbers.append(pickNumber)             # add the picked number to the picked list
      lottoNumbers.remove(pickNumber)              # remove the picked number from the original list (don't want to pick it twice)
   
   print(pickedNumbers)  
   return pickedNumbers
