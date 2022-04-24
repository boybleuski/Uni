###  Programmer : Simon
###  Date: 2014
###  Modifications: Keith Nesbitt
###  May 2015, May 2016, May 2017
###  This  program illustrates some more sophisticated sound functions
###
###  Make sure you set the mediaPath before using spliceSentence() and spliceSentence2()
###  Its expects to find thw wav file "NowGoodMen.wav" in this path
###  spliceSentence2() also creates new wav files in this path
###  >>> spliceSentence()
###  >>> spliceSentence2()
###
###  Note some of these functions are used by other functions 
###  >>> normalise()
###  >>> clip()
###  >>> copy()
###
###  Some more fancy sound processing - check definitions for parameters and return values
###  >>> reverse()
###  >>> mirror()
###  >>> mix()
###  >>> echo()
###  >>> frequencyShift()

def spliceSentence():
  # Make a new sentence or two from a highly specific existing recording.
  # Note that we get the recording straight from the MediaPath -
  # which therefore has to be set correctly.
  
  sentence = makeSound(getMediaPath("NowGoodMen.wav"))

  normalise(sentence)
  play(sentence)
  
  # This sentence says "Now is the time for all good men to come to the aid of the party."
  
  # Make a new empty sound of the same length
  newSentence = makeEmptySound(getLength(sentence))
  
  # Copy bits of the sentence to the new sentence
  # All the numbers were found by using explore
  
  # We use for loops to iterate through the samples we're copying from.
  # For the samples we're copying to, we start a counter at the beginning and
  # just add to it every time we copy a sample, so that it remains sequential.
  targetIndex = 0
  
  # "time"
  for i in range(35000, 45000):
    setSampleValueAt(newSentence, targetIndex, getSampleValueAt(sentence, i))
    targetIndex = targetIndex + 1

  # "to"
  for i in range(75000, 79000):
    setSampleValueAt(newSentence, targetIndex, getSampleValueAt(sentence, i))
    targetIndex = targetIndex + 1

  # "party"
  for i in range(105000, 118000):
    setSampleValueAt(newSentence, targetIndex, getSampleValueAt(sentence, i))
    targetIndex = targetIndex + 1
    
  # And normalise and return the new sound
  normalise(newSentence)
  return newSentence
  # End of spliceSentence


def normalise(aSound):
  # Increase the amplitude of aSound as much as possible without clipping
  # First we have to find the biggest (positive or negative) value
  biggest = 0 # The biggest is surely going to be bigger than this!
  for samp in getSamples(aSound):
    if abs(getSampleValue(samp)) > biggest: # Remember absolute value?
      biggest = abs(getSampleValue(samp))
  
  # After that loop, biggest is the biggest (positive or negative) amplitude
  # Multiplier will multiply it up to the maximum amplitude of 32767
  multiplier = 32767.0 / biggest
  # It's a fraction; if we didn't make it a float, it would be zero!
  
  # In the next loop, we multiply every sample value by the same multiplier
  for samp in getSamples(aSound):
    setSampleValue(samp, multiplier * getSampleValue(samp))
  # That's it - aSound has been normalised
  # End of normalise (this comment is because the end is so far from the start)


def spliceSentence2():
  # Make a new sentence or two from an existing recording.
  # Note that we get the recording straight from the MediaPath -
  # which therefore has to be set correctly.
  
  sentence = makeSound(getMediaPath("NowGoodMen.wav"))
  # This sentence says "Now is the time for all good men to come to the aid of the party."
  
  # Clip all the bits and save them in separate files
  # All the numbers were found by using explore
  #NOTE: clip function is defined below
  clip(sentence, 35000, 45000, "time")
  clip(sentence, 75000, 79000, "to")
  clip(sentence, 105000, 118000, "party")
  
  # Load all the files into new sounds
  part1 = makeSound(getMediaPath("time.wav"))
  part2 = makeSound(getMediaPath("to.wav"))
  part3 = makeSound(getMediaPath("party.wav"))
  
  # Copy the files as required into a new empty sound - we even know what length to make it
  newSentence = makeEmptySound(getLength(part1) + getLength(part2) + getLength(part3))
  # Each copy indicates the source, the target, and the starting index in the target
  #NOTE: copy function is defined below
  copy(part1, newSentence, 0)
  copy(part2, newSentence, getLength(part1))
  copy(part3, newSentence, getLength(part1) + getLength(part2))
        
  # And return the new sound
  normalise(newSentence)
  return newSentence
  # End of spliceSentence2


def clip(source, start, end, name):
  # Make a new sound with the samples from start to end of the source sound,
  # and save it as name.wav (in the media path folder)
  target = makeEmptySound(end - start)
  # Here's a different approach to the two indexes - one is always 'start' more than the other
  for index in range(0, end - start):
    setSampleValueAt(target, index, getSampleValueAt(source, start + index))
  writeSoundTo(target, getMediaPath(name + ".wav"))


def copy(source, target, start):
  # Copy all of the source file to the target file from index 'start'
  for i in range(0, getLength(source)):
    setSampleValueAt(target, i + start, getSampleValueAt(source, i))


def reverse(aSound):
  # Make and return a copy of aSound reversed
  length = getLength(aSound)
  target = makeEmptySound(length)
  for index in range(0, length):
    setSampleValueAt(target, index, getSampleValueAt(aSound, length - index - 1))
  return target


def mirror(aSound):
  # Make a copy of aSound but with its second half a reversed copy of the first half
  length = getLength(aSound)
  target = makeEmptySound(length)
  for index in range(0, length / 2):
    setSampleValueAt(target, index, getSampleValueAt(aSound, index))
    setSampleValueAt(target, length - 1 - index, getSampleValueAt(aSound, index))
  return target


def mix(sound1, sound2, start, percent):
  # Mix sound 1 and sound 2, starting at index 'start' of sound 1 (which is the longer one)
  # Percent indicates the strength of sound 2 compared with sound 1 in the mix.
  length1 = getLength(sound1)
  length2 = getLength(sound2)
  # A new empty file the length of sound 1
  target = makeEmptySound(length1)
  # Copy the whole of sound 1 to it
  copy(sound1, target, 0)
  # Now for every sample in sound 2 . . .
  for i in range(0, length2):
    # Make the target sample the specified mix of itself and the sample from sound 2
    newVal = getSampleValueAt(target, start + i) * (100 - percent) / 100 +  getSampleValueAt(sound2, i) * percent / 100
    setSampleValueAt(target, start + i, newVal)
  # That's it: normalise and return the new sound
  return target


def echo(sound, delay, percent):
  # Mix sound with a copy of itself, 'delay' samples further on, and weakened by 'percent'
  newSound = makeEmptySound(getLength(sound) + delay) # Length of sound plus end of echo
  copy(sound, newSound, 0) # Copy the whole of sound to newSound
  # Now mix in a weaker copy of sound, after 'delay' samples
  return mix(newSound, sound, delay, percent)


def frequencyShift(source, factor):
  # Shift the frequency of a sound by factor
  # Factor < 1, frequency drops; factor > 1, frequency rises
  target = makeEmptySound(int(getLength(source) / factor))
  # Notice that we choose the length of the new sound according to the factor
  sourceIndex = 0
  
  # Loop through the samples in the target
  for i in range(0, getLength(target)):
    sourceVal = getSampleValueAt(source, int(sourceIndex))
    setSampleValueAt(target, i, sourceVal)
    sourceIndex = sourceIndex + factor
    # Note that if factor < 1, it will take several additions to move to the next
    # sample, so each sample will be repeated several times in the target; while
    # if factor > 1, each addition will move more than one sample, so samples in
    # the source will be skipped.
  
  return(target)
  # End of freqShift
  

