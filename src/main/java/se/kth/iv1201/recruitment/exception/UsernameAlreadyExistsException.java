package se.kth.iv1201.recruitment.exception;

/**
 * Exception for when a username already exists.
 */
public class UsernameAlreadyExistsException extends Exception {

    /**
     * Constructor that calls the superconstructor on the extended Exception class.
     * @param message the exception message.
     */
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
