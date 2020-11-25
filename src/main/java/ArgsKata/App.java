package ArgsKata;

import java.util.*;

public class App {

    ArrayList<ArrayList<String>> schema = new ArrayList<ArrayList<String>>();
    Parser parser = new Parser(prepareSchema());
    public static void main(String[] args) {

    }

    private ArrayList<ArrayList<String>> prepareSchema(){
        ArrayList<ArrayList<String>> schem = new ArrayList<ArrayList<String>>();

        ArrayList<String> arg = new ArrayList<String>();
        arg.add("-a");
        arg.add("1");
        arg.add("2");
        schem.add(arg);

        return schem;
    }
}
