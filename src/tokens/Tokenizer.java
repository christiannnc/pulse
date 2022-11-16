package tokens;
import java.sql.Array;
import java.util.*;

public class Tokenizer {
    private enum TokenType {
        LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
        SEMI_COLON, COLON, DOUBLE_QT, SINGLE_QT,

        MUL, DIV, ADD, SUB, POW, MOD,

        EQ,

        EQ_COMP, OR, NOT, NOT_EQ, WHILE, IF, ELSE, ELSE_IF,
        LESS, GREATER, LESS_EQ, GREATER_EQ,


        INT, DOUBLE, BOOLEAN, STRING, IDENTIFIER,
        LINE_BREAK,
        INVALID
    }

    private static Scanner file;
    private static String source = "";

    public static final String INTS = "0123456789";
    public static final String ALPHABETIC_STRING =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHANUMERIC_STRING =
            ALPHABETIC_STRING + INTS;

    private static int tokenCount = 0;
    private static int current = 0;
    private static int lineNumber = 0;


    public static ArrayList<Token> tokenize(Scanner sourceFile) {
        file = sourceFile;
        file.useDelimiter("");

        // copy the source file's contents to a string
        // this will make the file easier to tokenize
        while(file.hasNext()) {
            tokenCount++;
            String nextStr = file.next();
            if(nextStr.equals("\n")) {
                lineNumber++;
            }
            source += nextStr;
        }

        ArrayList<Token> tokens = new ArrayList<>();

        // tokenize the string until the current position is the length of the string
        while(current != tokenCount) {
            String element = source.substring(current, (current + 1));
            // ignore any whitespace
            if(element.equals(" ")) {
                current++;
                continue;
            }

            int startPosition = current;

            TokenType tokenTypeVal = getType(element);
            String tokenizedElement = source.substring(startPosition, (current + 1));

            Token token = new Token(tokenTypeVal.toString(), tokenizedElement, lineNumber);

            tokens.add(token);
            current++;
        }

        return tokens;
    }

    public static void advance(Scanner scan) {
        scan.next();
    }

    public static TokenType getType(String value) {
        if(value.equals("\n")) {
            return TokenType.LINE_BREAK;
        } else if (value.equals("*")) {
            return TokenType.MUL;
        } else if (value.equals("/")) {
            return TokenType.DIV;
        } else if (value.equals("+")) {
            return TokenType.ADD;
        } else if (value.equals("-")) {
            return TokenType.SUB;
        } else if (value.equals("^")) {
            return TokenType.POW;
        } else if (value.equals("%")) {
            return TokenType.MOD;
        } else if (value.equals("(")) {
            return TokenType.LEFT_PAREN;
        } else if (value.equals(")")) {
            return TokenType.RIGHT_PAREN;
        } else if (value.equals("{")) {
            return TokenType.LEFT_BRACE;
        } else if (value.equals("}")) {
            return TokenType.RIGHT_BRACE;
        } else if (value.equals(":")) {
            return TokenType.COLON;
        } else if (value.equals(";")) {
            return TokenType.SEMI_COLON;
        } else if (value.equals("\"")) {
            return TokenType.DOUBLE_QT;
        } else if (value.equals("'")) {
            return TokenType.SINGLE_QT;
        } else if (value.equals("!")) {
            if (match("=")) {
                return TokenType.NOT_EQ;
            } else {
                return TokenType.NOT;
            }
        } else if (value.equals("<")) {
            if (match("=")) {
                return TokenType.LESS_EQ;
            } else {
                return TokenType.LESS;
            }
        } else if (value.equals(">")) {
            if (match("=")) {
                return TokenType.GREATER_EQ;
            } else {
                return TokenType.GREATER;
            }
        } else if (value.equals("=")) {
            if (match("=")) {
                return TokenType.EQ_COMP;
            } else {
                return TokenType.EQ;
            }
        } else if (value.equals("|")) {
            if (match("|")) {
                return TokenType.OR;
            } else {
                return TokenType.INVALID;
            }
        } else if(INTS.contains(value)) {
                if(makeNumber(value).equals("")) {
                    return TokenType.INVALID;
                } else {
                    return TokenType.INT;
                }

        } else if(ALPHANUMERIC_STRING.contains(value)) {
            String concatenatedValue = concatenateString(value);
            if(concatenatedValue.equals("else")) {
                return TokenType.ELSE;
            } else if(concatenatedValue.equals("or")) {
                return TokenType.OR;
            } else if(concatenatedValue.equals("while")) {
                return TokenType.WHILE;
            } else if(concatenatedValue.equals("else if")) {
                return TokenType.ELSE_IF;
            } else {
                return TokenType.IDENTIFIER;
            }
        } else {
            // if this is entered, the token was invalid
            return TokenType.INVALID;
        }

    }

    public static boolean match(String expected) {
        if(
                source.substring((current + 1), (current + 2)).equals(expected)
        ) {
            current++;
            return true;
        } else {
            return false;
        }
    }

    public static String makeNumber(String number) {
        String num = "";
        int position = current;
        int decimalCt = 0;

        while(INTS.contains(number) || number.equals(".")) {
            // a number cannot contain more than one decimal
            if(decimalCt > 1) {
                return "";
            } else if(number.equals(".")) {
                decimalCt++;
            }

            num += number;
            position++;
            number = source.substring(position, position + 1);

            // is this necessary?
            if(!INTS.contains(number)) {
                continue;
            }

            current++;
        }

        return num;
    }

    public static String concatenateString(String str) {
        String concatenatedStr = "";
        int position = current;

        while(ALPHANUMERIC_STRING.contains(str)) {

            concatenatedStr += str;
            position++;
            str = source.substring(position, position + 1);

            // is this necessary?
            if(!ALPHANUMERIC_STRING.contains(str)) {
                continue;
            }

            current++;
        }

        return concatenatedStr;
    }
}
