package club.itguys.shitty.io;

import java.io.ObjectInputStream;

/**
 * @author sgyh
 */
public class DefaultMessageReader implements MessageReader {

    private ObjectInputStream objectInputStream;

    public DefaultMessageReader(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    @Override
    public Object read() throws MessageException {
        try {
            return objectInputStream.readObject();
        } catch (Exception e) {
            throw new MessageException(e.getMessage());
        }
    }

    @Override
    public void run() {

    }
}
