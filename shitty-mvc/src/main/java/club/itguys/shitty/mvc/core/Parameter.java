package club.itguys.shitty.mvc.core;

import java.lang.reflect.Method;

/**
 * @author sgyh
 */
public class Parameter {

    private Method baseMethod;

    private String name;
    private boolean isRequired;
    private String defaultValue;

    public Parameter(Method baseMethod, String name, boolean isRequired, String defaultValue) {
        this.baseMethod = baseMethod;
        this.name = name;
        this.isRequired = isRequired;
        this.defaultValue = defaultValue;
    }

    public Method getBaseMethod() {
        return baseMethod;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
