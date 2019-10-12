package club.itguys.shitty.io;

/**
 * @author sgyh
 */
public class MessageException extends Exception {

    private String msg;

    public MessageException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
