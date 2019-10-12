package club.itguys.shitty;

/**
 * @author sgyh
 */
public interface Session {

    Object getAttribute(Object key);

    void putAttribute(Object key, Object value);

    boolean contains(Object key);

    Object remove(Object key);

    void clear();

}
