package club.itguys.shitty.mvc.mapping;

import club.itguys.shitty.mvc.core.HttpMethod;

import java.lang.reflect.Method;

/**
 * @author sgyh
 */
public interface PathResolver {

    String getPath();

    Method getResolver();

    HttpMethod getHttpMethod();

    Object resolve(Object... objects);

}
