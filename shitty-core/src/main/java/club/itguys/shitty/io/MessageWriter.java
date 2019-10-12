package club.itguys.shitty.io;

/**
 * @author sgyh
 */
public interface MessageWriter extends Runnable {

    void write(Object object) throws MessageException;

}
