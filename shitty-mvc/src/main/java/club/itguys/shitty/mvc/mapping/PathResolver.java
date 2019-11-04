package club.itguys.shitty.mvc.mapping;

import club.itguys.shitty.mvc.core.HttpMethod;
import club.itguys.shitty.mvc.core.Parameter;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author sgyh
 */
public interface PathResolver {

    String getPath();

    Method getResolver();

    HttpMethod getHttpMethod();

    List<Parameter> getParameters();

    boolean isJsonSerialize();

    String resolve(Object... objects);

}
