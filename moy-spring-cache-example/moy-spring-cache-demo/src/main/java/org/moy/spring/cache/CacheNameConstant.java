package org.moy.spring.cache;

import java.lang.reflect.Field;

public interface CacheNameConstant {

    String TEST = "test";

    String USER = "user";


    public static void main(String[] args) throws IllegalAccessException {
        Class<CacheNameConstant> cacheNameConstantClass = CacheNameConstant.class;
        Field[] fields = cacheNameConstantClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(null);
            System.out.println(String.format("%s = %s", name, value));
        }
    }
}
