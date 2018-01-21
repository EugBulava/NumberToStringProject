package converter;

import exceptions.NumberLengthException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementation of {@link NumberConverter}
 * convert integer number to string format
 *
 * @author Eugene Bulava
 */
public class IntegerNumberToStringConverter implements NumberConverter<String> {
    private BigInteger amount;


    private static final String FILE_PATH = "src/main/resources/referenceBook.txt";


    public IntegerNumberToStringConverter(String s) throws NumberFormatException {
        this.amount = new BigInteger(s);
    }

    public IntegerNumberToStringConverter(BigInteger bigNumber) {
        this.amount = bigNumber;
    }

    public static String morph(long n, String from1, String from2, String from5) {

        n = Math.abs(n) % 100;
        long n1 = n % 10;
        if (n > 10 && n < 20) return from5;
        if (n1 > 1 && n1 < 5) return from2;
        if (n1 == 1) return from1;
        return from5;
    }

    public String convert() throws NumberLengthException {
        //kinds of gender
        String[][] kinds = {
                {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
                {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
        };

        String[] str100 = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        String[] str11 = {"", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать", "двадцать"};
        String[] str10 = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        //forms of type number
        String[][] forms = readFormsFromFile();

        BigInteger number = amount;

        //check that the number entered is less than the types in the dictionary
        String result = "";
        if(amount.toString().length()>forms.length*3)
            throw new NumberLengthException("add to reference book more examples");

        //check for zero
        if (number.equals(BigInteger.ZERO)) {
            result = "ноль";
        } else {
            //check for positive or negative number
            if (number.signum() == -1){
                result = "минус ";
                number = number.abs();
            }

            BigInteger tempNumber = number;

            ArrayList segments = new ArrayList();
            // segmented number and add to list
            while (tempNumber.compareTo(new BigInteger("999")) == 1) {
                BigInteger segment = tempNumber.divide(new BigInteger("1000"));
                segments.add(tempNumber.add(segment.multiply(new BigInteger("1000")).negate()));
                tempNumber = segment;
            }

            segments.add(tempNumber);
            Collections.reverse(segments);

            int level = segments.size() - 1;


            for (int i = 0; i < segments.size(); i++) {
                int kind = Integer.valueOf(forms[level][3].toString()); //check gender
                int currentSegment = Integer.valueOf(segments.get(i).toString()); //current segment

                if (currentSegment == 0 && level >= 1) {
                    level--;
                    continue;
                }

                String segmentString = String.valueOf(currentSegment);

                if (segmentString.length() == 1) segmentString = "00" + segmentString; // check length of segment and add "0"
                if (segmentString.length() == 2) segmentString = "0" + segmentString; // or "00"

                int r1 = Integer.valueOf(segmentString.substring(0, 1)); // divide the segment 1**
                int r2 = Integer.valueOf(segmentString.substring(1, 2)); // *2*
                int r3 = Integer.valueOf(segmentString.substring(2, 3)); // **3
                int r23 = Integer.valueOf(segmentString.substring(1, 3));// *23

                    if (currentSegment > 99) result += str100[r1] + " ";

                if (r23 > 20) {
                    result += str10[r2] + " ";
                    if(!"".equals(kinds[kind][r3])) {
                        result += kinds[kind][r3] + " ";
                    }
                } else {
                        if (r23 > 9) result += str11[r23 - 9] + " ";

                    else
                        result += kinds[kind][r3] + " ";
                }
                result += morph(currentSegment, forms[level][0], forms[level][1], forms[level][2]) + " ";
                level--;
            }
            //result
            result = result.substring(0, result.length()-2);
            if(result.endsWith(" ")){
                result = result.substring(0, result.length()-1);
            }
        }
        return result;
    }

    private String[][] readFormsFromFile(){
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH), "utf-8"));
            ArrayList<String> list = new ArrayList<String>();

            String line;

            while((line = bf.readLine()) != null){
                list.add(line);
            }
            String result[][] = new String[list.size()/3][4];

            result[0][0] = "";
            result[0][1] = "";
            result[0][2] = "";
            result[0][3] = "0"; //set gender

            //add forms from referenceBook
            int a = 0;
            for (int i = 1; i < list.size()/3; i++) {
                for (int j = 0; j < 3; j++) {
                    result[i][j] = list.get(a);
                    a++;
                }
                result[i][3] = "0"; //set gender
            }
            result[1][3] = "1"; //set gender
            bf.close();
            return result;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

}
