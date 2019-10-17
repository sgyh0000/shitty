package club.itguys.shitty.beans;

/**
 * @author sgyh
 */
public class DefaultBeanDefination<T> implements BeanDefination<T> {

    private String beanName;
    private T bean;
    private Class<T> beanClass;

    public DefaultBeanDefination(String beanName, T bean, Class<T> beanClass) {
        this.bean = bean;
        if (beanName == null) {
            beanName = beanClass.getSimpleName().replaceFirst(beanClass.getSimpleName().substring(0, 1), beanClass.getSimpleName().substring(0, 1).toLowerCase());
        }
        this.beanName = beanName;
        this.beanClass = beanClass;
    }

    @Override
    public void init() {

    }

    @Override
    public Class<T> getBeanClass() {
        return beanClass;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public T getBean() {
        return bean;
    }

    @Override
    public void destroy() {

    }
}
