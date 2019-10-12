package club.itguys.shitty.io;

/**
 * @author sgyh
 */
public interface MessageHandleChain {

    MessageHandleChain append(MessageHandler messageHandler);

    void addExceptionHandler(ExceptionHandler exceptionHandler);

}
