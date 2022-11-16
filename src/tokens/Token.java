package tokens;

public class Token {
    private String tokenType;
    // the token's value as a string
    private String value;
    // the line number in the source code file where the token exists
    private int position;

    public Token(String tokenType, String value, int position) {
        this.tokenType = tokenType;
        this.value = value;
        this.position = position;
    }

    public String getTokenType() {
        return tokenType;
    }
    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        String s = "";

        s += this.value + ":";
        s += this.tokenType;

        return s;
    }
}
