#######################################################################################################
## Programmer: Keith Nesbitt
## Date: 2 May, 2017
##
## This program contains solutions (or suggested solutions)
## for the problems from the tutorial in Week 10
##
## Question 2  - 
## Question 3  - 
## Question 4  - shiftForward(sound, numberSamples), testShiftForward()
## Question 5  - shiftBack(sound, numberSamples), testShiftBack(), testShiftBack2()
## 
#######################################################################################################


#######################################################################################################
####
#### 2. (M) Write a function that takes a sound as an argument and makes a copy of 
####        that sound. It then reverses the sound and returns it. Make sure you test 
####        the function works as required.
####
#######################################################################################################

def reverse(aSound):
  # Make and return a copy of aSound reversed
  length = getLength(aSound)
  target = makeEmptySound(length)
  for index in range(0, length):
    setSampleValueAt(target, index, getSampleValueAt(aSound, length - index - 1))
  return target


def testReverse():
# Tests the reverse function (hard to do by listening so use explore to check)
   file = pickAFile()
   sound = makeSound(file)
   
   # tests the reverse function
   reverseSound = reverse(sound)
   
   #use some prints and explore to check this all works OK
   print sound
   print reverseSound
   explore(sound)
   explore(reverseSound)

#######################################################################################################
#### 
#### 3.(H) Write a function that takes a sound as an argument and makes a mirror copy 
####       of that sound and returns it. This problem was also discussed in the lecture.
#### 
#######################################################################################################

def mirror(aSound):
  # Make a copy of aSound but with its second half a reversed copy of the first half
  length = getLength(aSound)
  target = makeEmptySound(length)
  for index in range(0, length / 2):
    setSampleValueAt(target, index, getSampleValueAt(aSound, index))
    setSampleValueAt(target, length - 1 - index, getSampleValueAt(aSound, index))
  return target
  
def testMirror():
# Tests the mirror function (hard to do by listening so use explore to check)
   file = pickAFile()
   sound = makeSound(file)
   
   # tests the mirror function
   mirrorSound = mirror(sound)
   
   #use some prints and explore to check this all works OK
   print sound
   print mirrorSound
   explore(sound)
   explore(mirrorSound)


#######################################################################################################
####        
#### 4. (H) Write a function that has the following definition line:
####              def shiftForward(sound, numberSamples):
####        The function is required to make and return a new sound that is the original 
####        sound shifted forward by the specified number of samples (numberSamples). 
####        For example, if the sound is shifted forward by 1000 samples, the 1000th sample 
####        of the new sound will be same as the first sample of the original sound.
####        Once the shifting reaches the end, it should start again at the beginning. 
####        For example, if the sound is shifted forward by 1000 samples, the first 1000 samples
####        of the new sound will be the same as the last 1000 samples of the original sound.
####        Write the function so that it performs the shift as described, and returns the 
####        new sound.
####        Write a test function that reads in a sound and performs the shifting as well 
####        as saves the new sound to a new file. Make sure the function works as expected.
####         
#######################################################################################################

def shiftForward(sound, numberSamples): 
 
   # The function makes and return a new sound that is the original sound shifted
   # forward by the specified number of samples (numberSamples).
   # There are NO side effects - original sound is not changed.
   #
   # NOTE: The sound is assummed to be at least as long as "numberSamples" 
   #       There is no check for this in this function

   #first make an empty sound the correct length (same number samples as the original sound)
   lengthSound = getNumSamples(sound)
   shiftedSound = makeEmptySound(lengthSound) 
   
   # Loop through the original copying the sound values of the original to 
   # the shifted index locations - just add on the number of samples to shift by
   # But be careful not to go over the end of the sound - the mod function "%" is a simple way to do this - 
   for index in range(0, lengthSound):
      value = getSampleValueAt(sound, index)                   #get the original sample value (integer)
      shiftedIndex = (index + numberSamples) % lengthSound     #use the mod function to help work out shifted position
      setSampleValueAt(shiftedSound, shiftedIndex, value)      #Ok - set the value at the new location
      
   #return the new shofted sound
   return shiftedSound


def testShiftForward():
   # This function reads in a sound and tests the shiftForward function
   file = pickAFile()
   sound = makeSound(file)
   
   # tests the shiftForward function - assumes the sound is at least 1000 samples long
   adjustedSound = shiftForward(sound, 1000)
   
   #use some prints and explore to check this all works OK
   print sound
   print adjustedSound
   explore(sound)
   explore(adjustedSound)
   
#######################################################################################################
#### 
#### 5.(H) 1.	Write a function that has the following definition line:
####          def shiftBack(sound, numberSamples):
#### The function is required to make and return a new sound that is the original sound  
#### shifted backward by the specified number of samples (numberSamples). 
#### For example, if the sound is shifted backward by 1000 samples, the first sample of  
#### the new sound will be same as the 1000th sample of the original sound.
#### Once the shifting reaches the end, it should start again at the beginning. 
#### For example, if the sound is shifted backward by 1000 samples, the last 1000 samples 
#### of the new sound will be the same as the first 1000 samples of the original sound.
#### Write the function so that it performs the shift as described, and returns the new sound.
#### Write a test function that reads in a sound and performs the shifting as well as saves 
#### the new sound to a new file. Make sure the function works as expected.
#### 
#######################################################################################################

def shiftBack(sound, numberSamples): 
 
   # The function makes and return a new sound that is the original sound shifted
   # backward by the specified number of samples (numberSamples).
   # There are NO side effects - original sound is not changed.
   #
   # NOTE: The sound is assummed to be at least as long as "numberSamples" 
   #       There is no check for this in this function

   #first make an empty sound the correct length (same number samples as the original sound)
   lengthSound = getNumSamples(sound)
   shiftedSound = makeEmptySound(lengthSound) 
   
   # Loop through the original copying the sound values of the original to 
   # the shifted index locations - 
   # The new shift locations is obtained by first working out 
   # what the shift back means in terms of a shift forward
   # just add to teh index the (lengthSound - leftBackSize) to get the shift forward
   # But be careful not to go over the end of the sound - the mod function "%" is a simple way to do this - 
   for index in range(0, lengthSound):
      value = getSampleValueAt(sound, index)                  # get the original sample value (integer)
      shiftForward = lengthSound - numberSamples              # This works because we are circling around
      shiftedIndex = (index + shiftForward) % lengthSound     # use the mod function to help work out shifted position
      setSampleValueAt(shiftedSound, shiftedIndex, value)     # Ok - set the value at the new location
      
   #return the new shofted sound
   return shiftedSound


def testShiftBack():
   # This function reads in a sound and tests the shiftBack function
   file = pickAFile()
   sound = makeSound(file)
   
   # tests the shiftForward function - assumes the sound is at least 1000 samples long
   adjustedSound = shiftBack(sound, 1000)
   
   #use some prints and explore to check this all works OK
   print sound
   print adjustedSound
   explore(sound)
   explore(adjustedSound)
   
def testShiftBack2():
   # This function reads in a sound and tests the shiftBack function
   file = pickAFile()
   sound = makeSound(file)
   
   # tests the shiftForward function - assumes the sound is at least 1000 samples long
   
   # since we treat the sound as a circular array - we can turn the shiftBack problem into
   # a shift forward problem - just need to work out what a shiftBack
   # means in terms of a shift forward
   shiftBackNumberSamples = 1000
   adjustedSoundBackward = shiftBack(sound,shiftBackNumberSamples)

   shiftForwardNumberSamples = getNumSamples(sound) - shiftBackNumberSamples 
   adjustedSoundForward = shiftForward(sound,shiftForwardNumberSamples)
   
   #use some prints and explore to check this all works OK
   #Note these two sounds should end up the same
   print adjustedSoundBackward
   print adjustedSoundForward
   explore(adjustedSoundBackward)
   explore(adjustedSoundForward)
