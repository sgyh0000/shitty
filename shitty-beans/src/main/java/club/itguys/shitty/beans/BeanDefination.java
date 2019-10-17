package club.itguys.shitty.beans;

/**
 * @author sgyh
 */
public interface BeanDefination<T> {

    void init();

    Class<T> getBeanClass();

    String getBeanName();

    T getBean();

    void destroy();

}
