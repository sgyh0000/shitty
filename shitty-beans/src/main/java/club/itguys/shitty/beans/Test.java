package club.itguys.shitty.beans;

import club.itguys.shitty.beans.anno.Component;
import club.itguys.shitty.beans.anno.Injection;

/**
 * @author sgyh
 */
@Component
public class Test {

    @Injection
    private TestBean testBean;

    public void test() {
        testBean.test();
    }

}
