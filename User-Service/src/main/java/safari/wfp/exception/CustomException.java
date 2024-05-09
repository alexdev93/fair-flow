package safari.wfp.exception;

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(String.format(message));
    }
}
