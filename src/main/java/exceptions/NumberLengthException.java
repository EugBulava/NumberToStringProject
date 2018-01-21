package exceptions;

/**
 * Custom exception class. Throws when input number is bigger than algorithm can process
 *
 * @author Eugene Bulava
 */
public class NumberLengthException extends Exception {
    /**
     * Creates a new instance of NumberLengthException with the given error message.
     */
    public NumberLengthException(String massage){
        super(massage);

    }

}
