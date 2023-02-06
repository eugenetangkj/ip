package duke.command;

/**
 * Represents a command that is entered by the user which would lead to an exception.
 */
public class ExceptionCommand extends Command {
    private String exceptionMessage;


    /**
     * Constructs an {@code ExceptionCommand}.
     */
    public ExceptionCommand(String exceptionMessage) {
        super();
        this.exceptionMessage = exceptionMessage;

    }

    /**
     * Causes the bot to return the exception message.
     *
     * @return the exception message.
     */
    @Override
    public String runCommand() {
        return exceptionMessage;
    }
}
