#######################################################################################################
## Programmer: Keith Nesbitt
## Date: 1 May, 2017
##
## This program contains solutions (or suggested solutions)
## for the problems from the tutorial in Week 9
##
## Question 2  - copySound(aSound), testCopySound()
## Question 3  - shortenSoundTenPercent(longSound), testShortenSound()
## Question 4  - halveSound(originalSoundFile), testHalveSound()
## Question 5  - joinSounds(sound1, sound2), testJoinSound()
## 
#######################################################################################################


#######################################################################################################
#### 2. (E) Write a function that takes a sound as an argument and makes a copy
####    of that sound. The function will "return" the copied sound. 
####    Make sure you test the function works as required.
#######################################################################################################

def copySound(aSound):
   # This function copies the sound argument and then returns the copy
   # Of course this functionality is already available as 
   # the JES function "duplicateSound() - but the point is for us to 
   # practice working with sound and arrays, loops etc.
   length = getNumSamples(aSound)
   copySound = makeEmptySound(length)
   
   # Just loop through copying each sample from the original
   # remember to copy "values" and not "samples" as 
   # "values" are integers (simple types)  while "samples" are 
   # complex things (objects)- you may just end up copying references
   # This can lead to all sorts of problems if you are not careful
   # Worth reviewing the module(4.1) about reference and value copying
   # if this difference between value and reference copying is not clear 
   for i in range(0, length): 
       originalValue = getSampleValueAt(aSound, i)
       setSampleValueAt(copySound, i, originalValue)
   
   #return the sound as requested
   return copySound
         
def testCopySound():
   # This function tests the copySound(aSound) function   
   soundFile = pickAFile() 
   testSound = makeSound(soundFile)
   
   newSound = copySound(testSound) # test the copy function
   blockingPlay(testSound) #play the original sound - block other sounds from playing
   blockingPlay(newSound)  #play the copied sound - again block other sounds from playing

   # NOTE: Only one sound can play at a time.
   # So if you use "play" instead of "blockingPlay" you will only hear the
   # second sound - as the second call to play will interupt (stop) the first play.


#######################################################################################################
#### 3.(M) Write a function that takes a sound as an argument and returns a new sound 
####       that is 10% shorter than the original sound. It does this by removing every 
####       tenth sample from the original. Make sure you test the function works as required.
#######################################################################################################

def shortenSoundTenPercent(longSound):

   # just set up an empty sound with 10% less Samples than the original
   # these will all have 0 in them but the second step will be to copy the correct 
   # values across to the shorter sound
   originalNumberSamples = getNumSamples(longSound)

   # be careful of integer division - 9/10 is zero
   ninetyPercentSampleLength = originalNumberSamples * 0.9

   #because I've created a floting point value now I will need to turn this
   #back into an integer to make it work with the "makeEmptySound" function
   shortSound = makeEmptySound(int(ninetyPercentSampleLength))
   
   #OK ready to do some copying
   #I'll need to keep a seperate pointer for the short sound as this
   #will get out of step with the index of the longer sound as we skip samples
   shortIndex = 0 
   
   # To skip I'm just going to use the mod function and ignore
   # every 10th sample - (as well as the first one) as (0 mod 10) is also 0
   
   for longIndex in range(0, originalNumberSamples):  #process every sample long original sound
      if (longIndex % 10) <> 0 : #skip first sample and every tenth one using mod function
          originalValue = getSampleValueAt(longSound, longIndex)
          setSampleValueAt(shortSound, shortIndex, originalValue)
          shortIndex = shortIndex + 1 #update the position index for the short sound
   
   #remember to return the sound (as requested)
   return shortSound

def testShortenSound():
   # This function tests the copySound(aSound) function   
   soundFile = pickAFile() 
   testSound = makeSound(soundFile)
   
   newSound = shortenSoundTenPercent(testSound) 
   
   # Test the shorten function
   # When I play the sounds they probably sound about the same
   blockingPlay(testSound) #play the original sound - block other sounds from playing
   blockingPlay(newSound)  #play the shorter sound

   # These print statements also help me test the results - I expect
   # the shorter sound to be 10% (one tenth) shorter than the original
   # So 90% the original length - I could do all this on a calculator
   originalNumberSamples = getNumSamples(testSound)
   shorterNumberSamples = getNumSamples(newSound)
   ninetyPercent = originalNumberSamples * 0.9 # be careful of integer division - 9/10 is zero
   
   print("Original Sound has " + str(originalNumberSamples) + " samples")
   print("90% of " + str(originalNumberSamples) + " = " + str(ninetyPercent))
   print("Shorter Sound has " + str(shorterNumberSamples) + " samples")
   
   # As another check I can compare the old and new by using 
   # "explore" with both sounds - here I would check that the tenth
   # sample is missing from the shorter sound - otherwise they are the same
   explore(testSound)
   explore(newSound)
    

#######################################################################################################
#### 4. (M) Write a function that takes a "file" as an argument and makes this into 
####        a sound. Process this sound so that every second sample is set to half 
####        it's current value. Make sure you test the function works as required. 
#######################################################################################################


def halveSound(originalSoundFile):
   # BE careful - this was a trick requirement in the question
   # the parameter is a sound file - NOT a sound
   # so first I have to make the sound from the file - tricky!
   originalSound = makeSound(originalSoundFile) 
   
   # Then I made a new duplicate empty sound to work with 
   # - as I like to avoid side effects as much as I can
   # (Although you were not specifically asked to do this)
   # So you could just change the original sound - but you should certainly
   # comment this side effect very carefully in your function definition 
   halfSound = duplicateSound(originalSound)
      
   # I just use the third (optional) argument of the range function - pass "2"
   # this just lets me process every second sample value of the range
   # If you are not sure how this works play around some more with the range function
   for i in range(0, getNumSamples(halfSound), 2):
      originalValue = getSampleValueAt(halfSound, i)
      newValue = originalValue / 2 
      setSampleValueAt(halfSound, i, newValue)

      #remember to return the sound (as requested)
   return halfSound
   

def testHalveSound():
   # This function tests the copySound(aSound) function   
   soundFile = pickAFile() 

   newSound = halveSound(soundFile)  #Be careful this should be a file - not a sound
      
   #I'll need to make the original sound here as well to test things
   originalSound = makeSound(soundFile)
   blockingPlay(originalSound)  #play the shorter sound
   blockingPlay(newSound)       #play the shorter sound
      
   # As another check I can compare the old and new by using 
   # "explore" with both sounds - here I would check every second
   # sample is halve the original sound - well just a few of them
   explore(originalSound)
   explore(newSound)

#######################################################################################################
#### 5.(M) Write a function called joinSounds(sound1, sound2)
####       This function takes two sounds and joins them into one long sound. 
####       There are no side effects (so the original sounds are not changed)
#######################################################################################################

def joinSounds(sound1, sound2):

   # 1. Work ou how long the new sound needs to be
   newLength = getNumSamples(sound1) + getNumSamples(sound2)
   
   # 2. Create a new sound that is the length you need
   joinedSound = makeEmptySound(newLength)
   
   #3. Copy all teh sample values in the first sound into the joinedSound
   #   I like to copy "values" so I don't accidently mess with the "sample" references
   for i in range(0, getNumSamples(sound1)):
      value = getSampleValueAt(sound1, i)
      setSampleValueAt(joinedSound, i, value)

   #4. Now copy the second sound into the joinedSound
   #   I have to be a bit careful to keep a different index for joinedSound
   currentI = getNumSamples(sound1) #where I'm up to after the first copy
   
   for i in range(0, getNumSamples(sound2)):  #copy all samples in sound 2
      value = getSampleValueAt(sound2, i)
      setSampleValueAt(joinedSound, currentI, value)
      currentI = currentI + 1  #update teh index of the joinedSound
   
   # As requested in requirement - "return" the new sound
   return joinedSound


def testJoinSound():
   # This function tests the copySound(aSound) function   
   soundFile1 = pickAFile() 
   soundFile2 = pickAFile() 

   sound1 = makeSound(soundFile1)
   sound2 = makeSound(soundFile2)
   
   # I tried it both ways
   newJoinSound1 = joinSounds(sound1, sound2) 
   newJoinSound2 = joinSounds(sound2, sound1) 
   
   blockingPlay(sound1)  #play the shorter sound
   blockingPlay(sound2)  #play the shorter sound
   blockingPlay(newJoinSound1)  #play the shorter sound
   blockingPlay(newJoinSound2)  #play the shorter sound
