package CS102_Sec3_Asgn6_Ozdemir_Erkam.Exceptions;

public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {
        super("Queue is empty");
    }
}