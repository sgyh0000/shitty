package club.itguys.shitty.mvc.mapping;

import club.itguys.shitty.mvc.core.HttpMethod;

import java.lang.reflect.Method;

/**
 * @author sgyh
 */
public interface PathResolver {

    void setPath(String path);

    void setResolover(Method method);

    void setHttpMethod(HttpMethod httpMethod);

}
