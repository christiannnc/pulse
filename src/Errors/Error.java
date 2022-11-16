package Errors;

public class Error {
    // errors that could be thrown by the interpreter
    private enum ErrorTypes {
        Syntax
    }

    private final String message;
    private final ErrorTypes type;
    private final int line;

    public Error(String message, ErrorTypes type, int line) {
        this.message = message;
        this.type = type;
        this.line = line;
    }

    public String getMessage() {
        return message;
    }

    public ErrorTypes getType() {
        return type;
    }

    public void unexpectedIdentifier() {
        Error unexpectedErr = new Error(
                "Unexpected identifier", ErrorTypes.Syntax, line
        );
        System.out.println(unexpectedErr);
        System.exit(-1);
    }

    public String toString() {
        String errMessage = "";
        errMessage += "[" + type + " error]" + " " + this.message;
        errMessage += "\n\t on line " + this.line;

        return errMessage;
    }
}
