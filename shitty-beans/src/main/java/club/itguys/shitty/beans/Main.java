package club.itguys.shitty.beans;


/**
 * @author sgyh
 */
public class Main {

    public static void main(String[] args) {
        try {
            Initializer initializer = new DefaultInitializer();
            initializer.init("club.itguys.shitty");
            Test test = ((Test) BeanDefinationMap.getBean(Test.class));
            test.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
