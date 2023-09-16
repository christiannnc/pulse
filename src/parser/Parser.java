package parser;
import tokens.*;
import java.util.ArrayList;

public class Parser {
    /** The list of tokens the parser can parse over */
    private ArrayList<Token> tokens;
    /** The current index the parser is looking at */
    private int currentTokenIndex;

    public Parser(ArrayList<Token> tks) {
        this.tokens = tks;
        this.currentTokenIndex = 0;

    }

    public static AbstractSyntaxTree parse() {

        return new AbstractSyntaxTree();
    }
}
