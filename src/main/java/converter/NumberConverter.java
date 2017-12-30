package converter;

import exceptions.NumberLengthException;

/**
 * Number converter interface
 *
 * @author Eugene Bulava
 */
public interface NumberConverter<T> {

    T convert() throws NumberLengthException;
}
