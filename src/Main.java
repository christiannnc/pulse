import tokens.*;
import parser.*;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java pulse <source-code-file>");
            System.exit(-1);
        }

        Scanner source = null;
        boolean hasError = false;

        try {
            source = new Scanner(new FileInputStream(args[0]));
        } catch(FileNotFoundException fnfErr) {
            System.out.println("Source code not found");
            System.exit(-1);
        }

        ArrayList<Token> tokens = Tokenizer.tokenize(source);

        Parser parser = new Parser(tokens);
        AbstractSyntaxTree ast = parser.parse();

        for(Token tkn : tokens) {
            System.out.println(tkn);
        }

        //String parsedTokens = Parser.parse(tokens);
    }
}
