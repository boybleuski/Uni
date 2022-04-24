  import static org.junit.jupiter.api.Assertions.*;
  import org.junit.jupiter.api.Test;

 import java.math.BigInteger;

class BigIntegerTest {

    BigInteger zero = BigInteger.ZERO;
    BigInteger one = BigInteger.ONE;
    BigInteger ten = BigInteger.TEN;
    BigInteger negative = BigInteger.valueOf(-1L);
    BigInteger min = BigInteger.valueOf(Integer.MIN_VALUE);
    BigInteger max = BigInteger.valueOf(Integer.MAX_VALUE);
    //creates a BigInteger that is one greater than max value so that (words != null)
    BigInteger oneWord = BigInteger.valueOf((long) Integer.MAX_VALUE+1);
    //creates a BigInteger that is 2 times max value + 1 so that (words != null) and ival > 1
    BigInteger twoWords = BigInteger.valueOf((long) Integer.MAX_VALUE*2+1);
    //creates a BigInteger that is 1 less than min value, so that (words != null) and isNegative()
    BigInteger negOneWord = BigInteger.valueOf((long) Integer.MIN_VALUE-1);

    
     @Test
     public void conditionDecisionGCD(){
         assertEquals(one, zero.gcd(one));
         assertEquals(one, negative.gcd(negative));
         assertEquals(one, one.gcd(one));
         assertEquals(min.abs(), min.gcd(min));
         assertEquals(oneWord, oneWord.gcd(zero));
         assertEquals(oneWord, oneWord.gcd(oneWord));
     }

     @Test
     public void multipleConditionGCD(){
         assertEquals(one, one.gcd(min));
         assertEquals(one, min.gcd(one));
         assertEquals(one, one.gcd(oneWord));
         assertEquals(min.abs(), min.gcd(oneWord));
     }

     @Test
     public void conditionDecisionModInverse(){
         assertThrows(ArithmeticException.class, () -> zero.modInverse(negative));
         assertThrows(ArithmeticException.class, () -> zero.modInverse(zero));
         assertEquals(zero, zero.modInverse(one));
         assertEquals(one, one.modInverse(ten));
         assertEquals(one, negOneWord.modInverse(ten));
         assertEquals(one, ten.modInverse(BigInteger.valueOf(3L)));
         assertEquals(max, negative.modInverse(oneWord));
     }

     @Test
     public void conditionDecisionCompareTo(){
         assertEquals(-1, zero.compareTo(one));
         assertEquals(1, one.compareTo(zero));
         assertEquals(0, one.compareTo(one));
         assertEquals(-1, negative.compareTo(one));
         assertEquals(1, one.compareTo(negative));
         assertEquals(1, twoWords.compareTo(oneWord));
         assertEquals(-1, oneWord.compareTo(twoWords));
         assertEquals(0, oneWord.compareTo(oneWord));
     }

     @Test
    public void multipleConditionCompareTo(){
         assertEquals(1, negative.compareTo(negOneWord));
         assertEquals(-1, negOneWord.compareTo(negative));
     }

}