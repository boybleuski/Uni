package task2;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class HelloWorld {
    public static void main(String[] args) {
        testBI();
    }

    @Test
    public static void testBI(){
        //====BigIntegers with integer constructors are accessed through "valueOf()"

        //Initialize variables
        //__All-defs coverage__
        //  GCD
        BigInteger dgcd1x = BigInteger.valueOf(1L);
        BigInteger dgcd1y = BigInteger.valueOf(-2147483648L);

        BigInteger dgcd2x = BigInteger.valueOf(-1L);
        BigInteger dgcd2y = BigInteger.valueOf(-1L);

        //  compareTo
        BigInteger dcompareTo1x = new BigInteger("1");
        BigInteger dcompareTo1y = new BigInteger("2");


        //__All-uses coverage__
        //  GCD
        BigInteger ugcd1x = BigInteger.valueOf(0L);
        BigInteger ugcd1y = BigInteger.valueOf(0L);

        BigInteger ugcd2x = BigInteger.valueOf(-1L);
        BigInteger ugcd2y = BigInteger.valueOf(-1L);

        BigInteger ugcd3x = BigInteger.valueOf(1L);
        BigInteger ugcd3y = BigInteger.valueOf(1L);

        BigInteger ugcd4x = BigInteger.valueOf(1L);
        BigInteger ugcd4y = BigInteger.valueOf(-2147483648L);

        BigInteger ugcd5x = new BigInteger("1");
        BigInteger ugcd5y = new BigInteger("1");

        //  compareTo
        BigInteger ucompareTo1x = BigInteger.valueOf(1);
        BigInteger ucompareTo1y = BigInteger.valueOf(2);

        BigInteger ucompareTo2x = new BigInteger("1");
        BigInteger ucompareTo2y = new BigInteger("2");

        BigInteger ucompareTo3x = new BigInteger("-1");
        BigInteger ucompareTo3y = new BigInteger("2");

        BigInteger ucompareTo4x = new BigInteger("1");
        BigInteger ucompareTo4y = new BigInteger("1");

        //Run tests
        //All-defs coverage
        //GCD
        assertEquals(BigInteger.valueOf(1), dgcd1x.gcd(dgcd1y));
        assertEquals(BigInteger.valueOf(1), dgcd2x.gcd(dgcd2y));

        //compareTo
        assertEquals(-1, dcompareTo1x.compareTo(dcompareTo1y));

        //All-uses coverage
        //GCD
        assertEquals(BigInteger.valueOf(0), ugcd1x.gcd(ugcd1y));
        assertEquals(BigInteger.valueOf(1), ugcd2x.gcd(ugcd2y));
        assertEquals(BigInteger.valueOf(1), ugcd3x.gcd(ugcd3y));
        assertEquals(BigInteger.valueOf(1), ugcd4x.gcd(ugcd4y));
        assertEquals(BigInteger.valueOf(1), ugcd5x.gcd(ugcd5y));

        //compareTo
        assertEquals(-1, ucompareTo1x.compareTo(ucompareTo1y));
        assertEquals(-1, ucompareTo2x.compareTo(ucompareTo2y));
        assertEquals(-1, ucompareTo3x.compareTo(ucompareTo3y));
        assertEquals(0, ucompareTo4x.compareTo(ucompareTo4y));
    }
}
