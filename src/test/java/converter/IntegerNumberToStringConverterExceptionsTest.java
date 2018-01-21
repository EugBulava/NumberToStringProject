package converter;

import exceptions.NumberLengthException;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by Eugene on 30.12.17.
 */
public class IntegerNumberToStringConverterExceptionsTest {

    @Test(expected = NumberLengthException.class)
    public void numberLengthExceptionCheck() throws NumberLengthException {
        BigInteger integer = new BigInteger("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");

        IntegerNumberToStringConverter converter = new IntegerNumberToStringConverter(integer);

        converter.convert();
    }

    @Test(expected = NumberFormatException.class)
    public void customNumberFormatExceptionCheck() throws NumberFormatException {

        new IntegerNumberToStringConverter("asdvasas");

    }

    @Test(expected = NumberFormatException.class)
    public void customNumberFormatExceptionCheck2() throws NumberFormatException {

        new IntegerNumberToStringConverter("12341234123b");

    }
}
