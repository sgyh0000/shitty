package club.itguys.shitty.reflection;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

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

    public static List<Class<?>> getAnnotationClassUnderPackage(String basePackage, String annotationName) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = getAllClassesUnderPackage(basePackage);
        return getAnnotationClass(annotationName, classes);
    }

    public static List<Class<?>> getAnnotationClassUnderPackage(String basePackage, Class<? extends Annotation> annotationClass) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = getAllClassesUnderPackage(basePackage);
        return getAnnotationClass(annotationClass, classes);
    }

    public static List<Class<?>> getAnnotationClass(String annotationName, List<Class<?>> classes) throws ClassNotFoundException {
        if (CollectionUtils.isEmpty(classes)) {
            return CollectionUtils.emptyList();
        }
        Class<Annotation> annoClass = (Class<Annotation>) Class.forName(annotationName);
        return classes.stream().filter(clazz -> {
            return clazz.getDeclaredAnnotation(annoClass) != null;
        }).collect(Collectors.toList());
    }

    public static <A extends Annotation> Map<Field, A> getAnnotatedFields(String basePackage, Class<A> annotationClass) throws IOException {
        List<Class<?>> classes = getAllClassesUnderPackage(basePackage);
        return getAnnotatedFields(classes, annotationClass);
    }

    public static <A extends Annotation> Map<Field, A> getAnnotatedFields(List<Class<?>> classes, Class<A> annotationClass) {
        Map<Field, A> fieldMap = new HashMap<>(128);
        for (Class clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                A a = field.getAnnotation(annotationClass);
                if (a != null) {
                    fieldMap.put(field, a);
                }
            }
        }
        return fieldMap;
    }

    public static <A extends Annotation> List<A> getAnnotations(Class<A> annotationClass, String basePackage) throws IOException {
        List<Class<?>> classes = getAllClassesUnderPackage(basePackage);
        return getAnnotations(annotationClass, classes);
    }

    public static <A extends Annotation> List<A> getAnnotations(Class<A> annotationClass, List<Class<?>> classes) {
        return classes.stream().filter(clazz -> {
            return clazz.getDeclaredAnnotation(annotationClass) != null;
        }).map(clazz -> {
            return clazz.getDeclaredAnnotation(annotationClass);
        }).collect(Collectors.toList());
    }

    public static List<Class<?>> getAnnotationClass(Class<? extends Annotation> annotationClass, List<Class<?>> classes) throws ClassNotFoundException {
        return getAnnotationClass(annotationClass.getName(), classes);
    }

    public static List<Class<?>> getAllClassesUnderPackage(String basePackage) throws IOException {
        List<Class<?>> classes = new ArrayList<>();
        getAllClassesUnderPackage(basePackage, classes);
        return classes;
    }

    private static void getAllClassesUnderPackage(String basePackage, List<Class<?>> classes) throws IOException {
        String path = basePackage.replaceAll("[.]", "/");
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(path);

        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                File dir = new File(URLDecoder.decode(url.getFile(), "UTF-8"));
                if (!dir.exists()) {
                    System.err.println(path + " not exists");
                }
                File[] files = dir.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.isDirectory() || pathname.getName().endsWith(".class");
                    }
                });
                for (File file : files) {
                    if (file.isDirectory()) {
                        getAllClassesUnderPackage(basePackage + "." + file.getName(), classes);
                    } else {
                        String className = file.getName().substring(0, file.getName().length() - 6);
                        try {
                            classes.add(Class.forName(basePackage + "." + className));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            // 不可能ClassNotFound
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
