package club.itguys.shitty.mvc;

import club.itguys.shitty.mvc.core.Parameter;
import club.itguys.shitty.mvc.exceptions.UnsupportedMethodException;
import club.itguys.shitty.mvc.mapping.MvcContext;
import club.itguys.shitty.mvc.mapping.PathResolver;
import club.itguys.shitty.mvc.mapping.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sgyh
 */
public class RequestMappingServlet extends HttpServlet implements RequestMapping {

    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        // 浏览器端缓存是否失效逻辑,保留
        long lastModified;
        if (method.equals("GET")) {
            lastModified = super.getLastModified(req);
            if (lastModified != -1L) {
                long ifModifiedSince = req.getDateHeader("If-Modified-Since");
                if (ifModifiedSince < lastModified / 1000L * 1000L) {
                    if (!resp.containsHeader("Last-Modified")) {
                        if (lastModified >= 0L) {
                            resp.setDateHeader("Last-Modified", lastModified);
                        }

                    }
                } else {
                    resp.setStatus(304);
                    return;
                }
            }
        }
        // 所有请求统一mapping
        this.mapping(req, resp);
    }

    @Override
    public void mapping(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getServletPath();
        String method = req.getMethod();
        PathResolver pathResolver = null;
        try {
            // 可能404, 可能请求方法不对
            pathResolver = MvcContext.getResolver(path, method);
        } catch (NullPointerException e) {
            resp.sendError(404, e.getMessage());
            return;
        } catch (UnsupportedMethodException e) {
            resp.sendError(400, e.getMessage());
            return;
        }
        List<String> parameters = pathResolver.getParameters().stream().map(
                p -> getParameterValue(req, p)
        ).collect(Collectors.toList());
        try {
            String result = pathResolver.resolve(parameters.toArray());
            // 重定向
            if (result.startsWith(REDIRECT_PREFIX)) {
                resp.sendRedirect(result.replace(REDIRECT_PREFIX, ""));
                return;
            }
            // json字符串
            if (pathResolver.isJsonSerialize()) {
                resp.setContentType("application/json");
            }
            resp.getWriter().print(result);
        } catch (UnsupportedMethodException e) {
            // 有必填字段未填
            resp.sendError(400, e.getMessage());
        }
    }

    private String getParameterValue(HttpServletRequest req, Parameter parameter) {
        // todo 添加非表单参数HttpServletRequest、HttpSession等参数的注入
        String value = req.getParameter(parameter.getName());
        // 缺省值替代, 确保到PathResolver时都为非null(若仍有null, 则返回400)
        return value == null ? parameter.getDefaultValue() : value;
    }
}
