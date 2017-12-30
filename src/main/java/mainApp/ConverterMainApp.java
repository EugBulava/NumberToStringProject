package mainApp;


import exceptions.CustomNumberFormatException;
import exceptions.NumberLengthException;
import converter.IntegerNumberToStringConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

/**
 * Main app - entry point
 *
 * @author Eugene Bulava
 */
public class ConverterMainApp {

    public static void main(String[] args) throws IOException {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please, enter the number:");
            IntegerNumberToStringConverter mo = new IntegerNumberToStringConverter(bufferedReader.readLine());
            String number_as_string = mo.convert();
            System.out.println(number_as_string);
        }
        catch (NumberLengthException ex) {
            System.out.println(ex.getMessage());
        }
        catch (CustomNumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
}
