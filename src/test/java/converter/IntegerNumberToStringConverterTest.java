package converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Eugene on 28.12.17.
 */
@RunWith(Parameterized.class)
public class IntegerNumberToStringConverterTest {

    private String bigNumber;
    private String expectedResult;

    public IntegerNumberToStringConverterTest(String number, String expectedString){
        this.bigNumber = number;
        this.expectedResult = expectedString;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { "232444", "двести тридцать две тысячи четыреста сорок четыре"},
                { "232442", "двести тридцать две тысячи четыреста сорок два"},
                { "2001030234","два миллиарда один миллион тридцать тысяч двести тридцать четыре"},
                { "1000000000000000000000000000000000", "один дециллион"},
                { "991199882200000000000000991122999999555550000000000000000000000000000000000000000000000", "девятьсот девяносто один септемвигинтиллион сто девяносто девять сексвигинтиллионов восемьсот восемьдесят два квинвигинтиллиона двести  кватторвигинтиллионов девятьсот девяносто один новемдециллион сто двадцать два октодециллиона девятьсот девяносто девять септемдециллионов девятьсот девяносто девять сексдециллионов пятьсот пятьдесят пять квиндециллионов пятьсот пятьдесят кваттордециллионов"},
                { "4672376345157346", "четыре квадриллиона шестьсот семьдесят два триллиона триста семьдесят шесть миллиардов триста сорок пять миллионов сто пятьдесят семь тысяч триста сорок шесть"},
                { "120", "сто двадцать"},
                { "956000043200456349900", "девятьсот пятьдесят шесть квинтиллионов сорок три триллиона двести  миллиардов четыреста пятьдесят шесть миллионов триста сорок девять тысяч девятьсот"},
                { "2980", "две тысячи девятьсот восемьдесят"},
                { "2002560000110000055500000055500", "два нониллиона два октиллиона пятьсот шестьдесят септиллионов сто десять квинтиллионов пятьдесят пять триллионов пятьсот  миллиардов пятьдесят пять тысяч пятьсот"},
                { "9947836", "девять миллионов девятьсот сорок семь тысяч восемьсот тридцать шесть"},
                { "10", "десять"},
                { "0", "ноль"}
        });
    }

    @Test
    public void ConvertJUnitTest() throws Exception {
        BigInteger integer = new BigInteger(bigNumber);

        IntegerNumberToStringConverter converter = new IntegerNumberToStringConverter(integer);

        assertEquals(expectedResult, converter.convert());
    }

}