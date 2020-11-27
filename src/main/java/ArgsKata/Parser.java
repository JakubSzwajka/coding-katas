package ArgsKata;

import javafx.util.Pair;

import java.util.ArrayList;

public class Parser {

    private ArrayList<Pair<String, String>> schema;
    private ArrayList<Pair<String, String>> elements;

    public Parser(ArrayList<Pair<String, String>> schema) {
        this.schema = schema;
    }


    public ArrayList<Pair<String, String>> parse(String[] args) {
        ArrayList<Pair<String, String>> parsedArgs = new ArrayList<Pair<String, String>>();
        return parsedArgs;
    }

    public ArrayList<Pair<String, String>> getElements(String toParse) {
        ArrayList<Pair<String, String>> argArray = new ArrayList<Pair<String, String>>();
        Pair<String, String> keyValuePair;

        String key = "";
        String value = "";

        String[] elements = toParse.split(" ");

        for (int i = 0; i < elements.length; i++) {
            if (this.isFlag(elements[i])) {
                key = elements[i];

                try {
                    if (!this.isFlag(elements[i + 1])) {
                        value = elements[i + 1];
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    value = "";
                }

                keyValuePair = new Pair<String, String>(key, value);
                argArray.add(keyValuePair);
            }
        }
        return argArray;
    }

    private boolean isFlag(String element) {
        return element.charAt(0) == '-';
    }

    public String getValueByKey(String key) throws  ParserException{
        for (Pair<String, String> pair : this.elements ) {
            String key2 = pair.getKey();
            if ( key2.equals(key) ){
                return pair.getValue();
            }
        }
        throw new ParserException("No key found");
    }

    public void setElements(String s) {
        this.elements = this.getElements(s); 
    }

    public ArrayList<Pair<String, String>> parseInput(String arguments) {
//        default values
//        for int: 0
//        for String ""
//        for bool 0 - false
        int i = 0;
        this.setElements(arguments);
        ArrayList<Pair<String, String>> tempSchema = (ArrayList<Pair<String, String>>) this.schema.clone();

        for (Pair<String, String> pair: tempSchema) {

            String value = null;
            try {
                value = this.getValueByKey(pair.getKey());
            } catch (ParserException e) {
//                default values
//                switch (pair.getValue())

            }

            pair = new Pair<>(pair.getKey(), value);
            tempSchema.set(i , pair);
            i++;
            System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
        }

        return tempSchema;
    }
}
