package club.itguys.shitty.beans;

import club.itguys.shitty.beans.anno.Bean;

/**
 * @author sgyh
 */
@Bean(name = "test")
public class TestBean {

    public void test() {
        System.out.println("111");
    }

}
