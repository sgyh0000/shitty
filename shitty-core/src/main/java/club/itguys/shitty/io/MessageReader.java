package club.itguys.shitty.io;

/**
 * @author sgyh
 */
public interface MessageReader extends Runnable {

    Object read() throws MessageException;

}
