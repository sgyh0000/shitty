package club.itguys.shitty.mvc.mapping;

import club.itguys.shitty.beans.BeanDefination;
import club.itguys.shitty.mvc.anno.Param;
import club.itguys.shitty.mvc.anno.Path;
import club.itguys.shitty.mvc.anno.ResponseBody;
import club.itguys.shitty.mvc.core.HttpMethod;
import club.itguys.shitty.mvc.core.Parameter;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sgyh
 */
public class PathResolverImpl implements PathResolver {

    private Method method;
    private BeanDefination beanDefination;
    private String path;
    private HttpMethod httpMethod;
    private List<Parameter> parameters;
    private boolean isJsonSerialize;

    public PathResolverImpl(Method method, BeanDefination beanDefination) {
        this.method = method;
        this.beanDefination = beanDefination;

        Path path = method.getAnnotation(Path.class);
        this.path = path.path();
        this.httpMethod = path.method();

        this.isJsonSerialize = method.getAnnotation(ResponseBody.class) != null;

        this.parameters = new ArrayList<>();
        java.lang.reflect.Parameter[] parameters = method.getParameters();
        for (java.lang.reflect.Parameter parameter : parameters) {
            Param param = parameter.getAnnotation(Param.class);
            if (param == null) {
                if (parameter.getType().getName().equals(HttpServletRequest.class.getName())) {
                    this.parameters.add(new Parameter(method, "req", true, null, HttpServletRequest.class));
                    continue;
                }
                if (parameter.getType().getName().equals(HttpSession.class.getName())) {
                    this.parameters.add(new Parameter(method, "session", true, null, HttpSession.class));
                    continue;
                }
                // todo 其它情况应该抛异常
            }
            this.parameters.add(new Parameter(method, param.name(), param.isRequired(), param.defaultValue(), parameter.getType()));
        }
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Method getResolver() {
        return method;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public boolean isJsonSerialize() {
        return isJsonSerialize;
    }

    @Override
    public String resolve(Object... objects) {
        if (objects.length > 0) {
            for (int i = 0; i < objects.length; i++) {
                Parameter parameter = parameters.get(i);
                String clazzName = parameter.getType().getName();
                if (!parameter.isRequired() && "".equals(objects[i])) {
                    if (double.class.getName().equals(clazzName)) {
                        objects[i] = 0.0;
                    } else if (float.class.getName().equals(clazzName)) {
                        objects[i] = 0.0f;
                    } else if (long.class.getName().equals(clazzName)) {
                        objects[i] = 0L;
                    } else if (int.class.getName().equals(clazzName)) {
                        objects[i] = 0;
                    } else {
                        objects[i] = null;
                    }
                    continue;
                }
                if (Double.class.getName().equals(clazzName) || double.class.getName().equals(clazzName)) {
                    objects[i] = Double.valueOf(objects[i].toString());
                } else if (Float.class.getName().equals(clazzName) || float.class.getName().equals(clazzName)) {
                    objects[i] = Float.valueOf(objects[i].toString());
                } else if (Long.class.getName().equals(clazzName) || long.class.getName().equals(clazzName)) {
                    objects[i] = Long.valueOf(objects[i].toString());
                } else if (Integer.class.getName().equals(clazzName) || int.class.getName().equals(clazzName)) {
                    objects[i] = Integer.valueOf(objects[i].toString());
                }
            }
        }

        Object result = null;
        try {
            result = method.invoke(beanDefination.getBean(), objects);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (isJsonSerialize) {
            result = JSON.toJSONString(result);
        }
        return result == null ? "null" : result.toString();
    }
}
