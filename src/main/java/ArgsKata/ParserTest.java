package ArgsKata;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setup(){
        ArrayList<Pair<String, String>> schema = new ArrayList<Pair<String, String>>();
        Pair<String, String> argPair;
        argPair = new Pair<String,String>("-a" , "int");
        schema.add(argPair);
        argPair = new Pair<String,String>("-b" , "bool");
        schema.add(argPair);
        argPair = new Pair<String,String>("-c" , "String");
        schema.add(argPair);
        argPair = new Pair<String,String>("-d" , "int");
        schema.add(argPair);
        argPair = new Pair<String,String>("-e" , "bool");
        schema.add(argPair);

        parser = new Parser(schema);
    }

    @Test
    public void shouldReturnListOfPairsAsKeyValue(){
        // when
        ArrayList<Pair<String, String>> elemList = parser.getElements( "-b 123 -a 32");

        // then
        assertThat(elemList.get(0).getKey()).isEqualTo("-b");
        assertThat(elemList.get(0).getValue()).isEqualTo("123");
        assertThat(elemList.get(1).getKey()).isEqualTo("-a");
        assertThat(elemList.get(1).getValue()).isEqualTo("32");
    }

    @Test
    public void shouldReturnListOfPairsWhenFlagHasNoValue(){
        // when
        ArrayList<Pair<String, String>> elemList = parser.getElements( "-b 32 -a");

        // then
        assertThat(elemList.get(0).getKey()).isEqualTo("-b");
        assertThat(elemList.get(0).getValue()).isEqualTo("32");
        assertThat(elemList.get(1).getKey()).isEqualTo("-a");
        assertThat(elemList.get(1).getValue()).isEqualTo("");
    }

    @Test
    public void shouldReturnValueFromElementListByKey() throws ParserException {
        // when
        parser.setElements( "-b 32 -a");

        assertThat(parser.getValueByKey("-b")).isEqualTo("32");
        assertThat(parser.getValueByKey("-a")).isEqualTo("");
    }

    @Test
    public void shouldParseInputWithSchemaINT(){

        //when
        ArrayList<Pair<String, String>> programInput = parser.parseInput("-a 3");

        assertThat(programInput.get(0).getValue()).isEqualTo("3");
        assertThat(programInput.get(1).getValue()).isEqualTo("0");
        assertThat(programInput.get(2).getValue()).isEqualTo("");
        assertThat(programInput.get(3).getValue()).isEqualTo("0");
        assertThat(programInput.get(4).getValue()).isEqualTo("0");
    }
}
