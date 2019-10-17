package club.itguys.shitty.beans;

import club.itguys.shitty.beans.anno.Component;
import club.itguys.shitty.beans.anno.Injection;

/**
 * @author sgyh
 */
@Component(name = "test2")
public class Test {

    @Injection(name = "test")
    private TestBean testBean;

    public void test() {
        testBean.test();
    }

}
