package commands.enums;

public enum Errors {

    SERVER_ERROR("ERROR:\n\t Server error"),
    NAME_ERROR("ERROR:\n\t  Provided interface was not found"),
    NO_ARGUMENT_ERROR("ERROR:\n\t  No commands or options were provided");

    private final String message;

    Errors(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }
}
