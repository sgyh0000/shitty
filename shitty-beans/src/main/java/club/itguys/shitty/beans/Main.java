package club.itguys.shitty.beans;


/**
 * @author sgyh
 */
public class Main {

    public static void main(String[] args) {
        try {
            Initializer initializer = new DefaultInitializer();
            initializer.init("club.itguys");
            Test test = ((Test) BeanDefinationMap.getBean("test2"));
            test.test();
        } catch (Exception e) {

        }
    }

}
