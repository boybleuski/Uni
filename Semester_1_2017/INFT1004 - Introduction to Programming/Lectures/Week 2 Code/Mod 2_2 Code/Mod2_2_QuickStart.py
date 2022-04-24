###  Programmer : Keith Nesbitt
###  Date: 1 Mar 2017
###  This program introduces some example code for working with strings 
###  To test try loading the program, and typing the following command(s):
  

def madlib():
   #This is a function that prints a simple story
   # to change the story  - change the variables - name, pet, verb, snack
   name = "Keith"
   pet = "Blackie"
   verb = "jumped"
   snack = "salt and vinegar chips"
   line1 = "Once upon a time, " + name + " was walking"
   line2 = " with " + pet + ", a trained dragon. "
   line3 = "Suddenly, " + pet + " stopped and announced, "
   line4 = "'I have a desperate need for " + snack + "'."
   line5 = name + " complained. 'Where I am going to get that?' "
   line6 = "Then " + name + " found a wizard's wand. "
   line7 = "With a wave of the wand, "
   line8 = pet + " got " + snack + ". "
   line9 = "Perhaps surprisingly, " + pet + " " + verb + " the " + snack + "."
   print line1 + line2 + line3 + line4
   print line5 + line6 + line7 + line8 + line9
   
def madlib2():
   #This is a function that prints a simple story
   # to change the story  - change the variables - name, pet, verb, snack
   name = "Bobbie"
   pet = "Felix"
   verb = "licked"
   snack = "tuna fish"
   line1 = "Once upon a time, " + name + " was walking"
   line2 = " with " + pet + ", a trained dragon. "
   line3 = "Suddenly, " + pet + " stopped and announced, "
   line4 = "'I have a desperate need for " + snack + "'."
   line5 = name + " complained. 'Where I am going to get that?' "
   line6 = "Then " + name + " found a wizard's wand. "
   line7 = "With a wave of the wand, "
   line8 = pet + " got " + snack + ". "
   line9 = "Perhaps surprisingly, " + pet + " " + verb + " the " + snack + "."
   print line1 + line2 + line3 + line4
   print line5 + line6 + line7 + line8 + line9
   
def madlib3(name, pet, verb, snack):
   #This is a function that prints a simple story
   # to change the story  - use the paramaters - name, pet, verb, snack
   # name = "Bobbie"
   # pet = "Felix"
   # verb = "licked"
   # snack = "tuna fish"
   line1 = "Once upon a time, " + name + " was walking"
   line2 = " with " + pet + ", a trained dragon. "
   line3 = "Suddenly, " + pet + " stopped and announced, "
   line4 = "'I have a desperate need for " + snack + "'."
   line5 = name + " complained. 'Where I am going to get that?' "
   line6 = "Then " + name + " found a wizard's wand. "
   line7 = "With a wave of the wand, "
   line8 = pet + " got " + snack + ". "
   line9 = "Perhaps surprisingly, " + pet + " " + verb + " the " + snack + "."
   print line1 + line2 + line3 + line4
   print line5 + line6 + line7 + line8 + line9

def testMadlib3():
   # This function is a convenient way to test the madlib functions
   madlib3("Keith", "Blackie", "jumped", "salt and vinegar chips")
   print " "
   madlib3("Bobbie", "Felix", "licked", "tuna fish")

def anotherTestMadlib3():
   myName = "Keith"
   myPet = "Blackie"
   myVerb = "jumped"
   mySnack = "salt and vinegar chips"
   
   madlib3(myName, myPet, myVerb, mySnack)
   print " " #just prints an empty space  
   
   yName = "Bobbie"
   myPet = "Felix"
   myVerb = "licked"
   mySnack = "tuna fish"
   madlib3(myName, myPet, myVerb, mySnack)


def pyramid(character):
   #This function prints a pyramid of the character provided
   space = " " # define a variable - (not really needed but makes the code clearer)
   print 4 * space, character 
   print 3 * space, 3 * character
   print 2 * space, 5 * character
   print space, 7 * character
   print 9 * character

def testPyramid():
    #test the pyramid function with a few different characters
    pyramid("=")
    pyramid("*")
    pyramid("0")
    
   
def parts(string):
## Takes a string and then prints each letter in the string
   for letter in string:
      print letter


def justVowels(aString):
    ## Takes a string and prints all the vowels in the string (one on each line)
    for letter in aString:
       if letter in "aeiou":
          print letter

def justVowelsFixed1(aString):
    ## Takes a string and prints all the vowels in the string (one on each line)
    for letter in aString:
       if letter in "AEIOUaeiou":
          print letter

def justVowelsFixed2(aString):
    ## Takes a string and prints all the vowels in the string (one on each line)
    for letter in aString:
       if letter.lower() in "aeiou":
          print letter



def notVowels(aString):
    ## Takes a string and prints all the vowels in the string (one on each line)
    for letter in aString:
       if not (letter.lower() in "aeiou"):
          print letter
       

def duplicate(sourceString):
    ### This function duplicates the sourceString
    ### It's not a very useful function really - it just prints the copied string
    
    duplicateString = ""   # start with an empty string
    
    # now add each letter in the source string onto the end of the duplicate
    for nextLetter in sourceString:  
       duplicateString = duplicateString + nextLetter
       
    print duplicateString # print the final result - this is outside the for loop

def duplicate2(sourceString):
    ### This function duplicates the sourceString
    ### It's not a very useful function really - it just prints the copied string
    ### Normally you would "return" the result 
    
    duplicateString = ""   # start with an empty string
    
    # now add each letter in the source string onto the end of the duplicate
    for nextLetter in sourceString:  
       duplicateString = duplicateString + nextLetter
       # print each step of the loop so you can see what's happening on each loop
       print duplicateString # this print is inside the for loop
       
    print duplicateString # print the final result - this is outside the for loop
    
    # return duplicateString # this would return the result

def duplicate3(sourceString):
    ### This function duplicates the sourceString and returns it
    ### Normally you would "return" the result 
    
    duplicateString = ""   # start with an empty string
    
    # now add each letter in the source string onto the end of the duplicate
    for nextLetter in sourceString:  
       duplicateString = duplicateString + nextLetter
       
    return duplicateString

def reverse(sourceString):
    ### This function prints the reverse of the sourceString
    
    reverseString = ""   # start with an empty string
    
    # now add each letter in the source string onto the end of the duplicate
    for nextLetter in sourceString:  
       reverseString =  nextLetter + reverseString # note the difference from duplicate
       
    print reverseString # print the final result 

def testIndex():
    ### This function shows how to access parts of a string
    
    phrase = "Hello World"
    print phrase[0]
    print phrase[4]
    print phrase[5]
    print phrase[10]
    print phrase[-1]
    print phrase[-3]

    # Uncomment either of these lines below - and see the error message
    # print phrase[11]  # Sequence index out of range.
    # print phrase[-12]   # Sequence index out of range.


def duplicateIndex(sourceString):
    ### This function duplicates the sourceString using an index and the range function
    ### It's not a very useful function really - it just prints the copied string
    
    duplicateString = ""   # start with an empty string
    numberLetters = 11;    # OK this only works if the sourceString has 11 letters 
    
    # now add each letter in the source string onto the end of the duplicate string
    for index in range(0,numberLetters):  
       duplicateString = duplicateString + sourceString[index]
       
    print duplicateString # print the final result - this is outside the for loop

def duplicateIndexFix(sourceString):
    ### This function duplicates the sourceString using an index and the range function
    ### It's not a very useful function really - it just prints the copied string
    
    duplicateString = ""   # start with an empty string
     
    # OK works when sourceString is any size as len() function returns the number of letters
    numberLetters = len(sourceString);    
    
    # now add each letter in the source string onto the end of the duplicate string
    for index in range(0,numberLetters):  
       duplicateString = duplicateString + sourceString[index]
       
    print duplicateString # print the final result - this is outside the for loop


def tryRange():
    #this function gives some examples of using the range function
    # If you don't provide three arguments, it makes it '1'
    
    print range(0,10)   # [0,1,2,3,4,5,6,7,8,9]  - note there is no 10
    print range(5,10)   # [5,6,7,8,9]
    print range(0,10,2) # [0,2,4,6,8] 
    print range(10,0,-1) # [10,9,8,7,6,5,4,3,2,1] - note there is no 0
    



