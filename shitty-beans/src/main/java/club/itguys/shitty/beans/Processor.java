package club.itguys.shitty.beans;

/**
 *
 * bean中的注解处理器
 * 对自定义注解若有相关逻辑，需重写process
 *
 * @author sgyh
 */
public interface Processor {

    /**
     * process
     * @throws Exception
     */
    void process() throws Exception;

}
