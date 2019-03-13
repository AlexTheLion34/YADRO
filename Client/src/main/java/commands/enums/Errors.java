package commands.enums;

public enum Errors {

    SERVER_ERROR("ERROR:\n\t Server error"),
    NAME_ERROR("ERROR:\n\t  Provided interface was not found"),
    NO_ARGUMENT_ERROR("ERROR:\n\t  No commands or options were provided"),
    INVALID_ARGUMENT_ERROR("ERROR:\n\t  Invalid commands or arguments provided\n\t " +
                                                " Use \"cli_net help\" to see the valid commands");

    private final String message;

    Errors(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
