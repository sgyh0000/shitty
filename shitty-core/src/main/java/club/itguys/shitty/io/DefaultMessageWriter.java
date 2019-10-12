package club.itguys.shitty.io;

import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author sgyh
 */
public class DefaultMessageWriter implements MessageWriter {

    private ObjectOutputStream objectOutputStream;

    public DefaultMessageWriter(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public void write(Object object) throws MessageException {
        try {
            if (object instanceof Serializable) {
                objectOutputStream.writeObject(object);
            } else {
                throw new MessageException("Not serialization exception");
            }
        } catch (Exception e) {
            throw new MessageException(e.getMessage());
        }
    }

    @Override
    public void run() {

    }
}
