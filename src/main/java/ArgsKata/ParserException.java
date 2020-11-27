package ArgsKata;

public class ParserException extends  Throwable{
    private String message;

    ParserException( String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}