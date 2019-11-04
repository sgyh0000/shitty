package club.itguys.shitty.mvc.mapping;

import club.itguys.shitty.mvc.core.HttpMethod;
import club.itguys.shitty.mvc.exceptions.UnsupportedMethodException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sgyh
 */
public class MvcContext {

    private static final Map<String, PathResolver> PATH_MAPPING = new ConcurrentHashMap<>();

    /**
     * 启动完成后request从这里拿到对应的mapping handler
     * @param path
     * @return
     */
    public static PathResolver getResolver(String path, String method) {
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        PathResolver pathResolver = PATH_MAPPING.get(path);
        if (pathResolver == null) {
            // FIXME 自定义异常(最终handle为返回404状态码)
            throw new NullPointerException("No path defined");
        }
        if (!pathResolver.getHttpMethod().equals(HttpMethod.ALL) && !httpMethod.equals(pathResolver.getHttpMethod())) {
            throw new UnsupportedMethodException("Method not allow");
        }
        return pathResolver;
    }

    /**
     * 发生在tomcat启动过程中
     * @param path
     * @param pathResolver
     */
    public static synchronized void addResolver(String path, PathResolver pathResolver) {
        if (PATH_MAPPING.get(path) != null) {
            // FIXME 自定义异常(最终handle为tomcat启动失败)
            throw new UnsupportedOperationException("Path had defined");
        }
        PATH_MAPPING.put(path, pathResolver);
    }

}
