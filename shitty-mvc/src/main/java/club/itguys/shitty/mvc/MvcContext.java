package club.itguys.shitty.mvc;

import club.itguys.shitty.mvc.mapping.PathResolver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sgyh
 */
public class MvcContext {

    private static final Map<String, PathResolver> PATH_RESOLVER_MAP = new ConcurrentHashMap<>();

}
