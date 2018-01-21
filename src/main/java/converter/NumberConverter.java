package converter;

import exceptions.NumberLengthException;

/**
 * Number converter interface
 *
 * @author Eugene Bulava
 */
public interface NumberConverter<T> {
    /**
     * This method converts number to parameterized format T.
     */
    T convert() throws NumberLengthException;
}
