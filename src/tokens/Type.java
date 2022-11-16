package tokens;

public class Type {

    private String type;


    public Type(String type) {
        boolean validType = false;

        if(!validType) {
            throw new IllegalArgumentException("type " + type + " is not a valid type");
        }

        this.type = type;
    }
}
