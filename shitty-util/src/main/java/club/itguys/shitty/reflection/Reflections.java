package club.itguys.shitty.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author sgyh
 */
public class Reflections {

    /**
     * 不考虑方法重载，若方法重载，返回第一个同名方法
     * @param className
     * @param methodName
     * @param isDeclaredMethod
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    public static Method getMethod(String className, String methodName, boolean isDeclaredMethod) throws ClassNotFoundException, NoSuchMethodException {
        Class clazz = Class.forName(className);
        Method[] methods = isDeclaredMethod ? clazz.getDeclaredMethods() : clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        throw new NoSuchMethodException("No method name: " + methodName);
    }

    public static Field getField(String className, String fieldName, boolean isDeclaredField) throws ClassNotFoundException, NoSuchFieldException {
        Class clazz = Class.forName(className);
        Field field = isDeclaredField ? clazz.getDeclaredField(fieldName) : clazz.getField(fieldName);
        return field;
    }

    public static Class[] getAnnotationClass(String annotationName) {
        return null;
    }

}
