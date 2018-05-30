package com.vidyamay.utils;

import java.text.Normalizer;

//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

/**
 * The Class PropertyHolderUtil.
 */
@Configuration
@DependsOn("environment")
@Order(Ordered.LOWEST_PRECEDENCE)
//@RefreshScope
public class PropertyHolderUtil {

    /**
     * Instantiates a new property holder util.
     */
    public PropertyHolderUtil() {
        super();
    }

    /**
     * Get the Property Value as Integer.
     *
     * @param name
     *            the name
     * @return the integer property
     */
    public static Integer getIntegerProperty(String name) {
        return getPropertyValue(name, Integer.class) != null
                ? getPropertyValue(name, Integer.class) : null;
    }

    /**
     * Get the property value as Boolean.
     *
     * @param name
     *            the name
     * @return the boolean property
     */
    public static Boolean getBooleanProperty(String name) {
        return getPropertyValue(name, Boolean.class) != null
                ? getPropertyValue(name, Boolean.class) : null;
    }

    /**
     * Get the property value as String.
     *
     * @param name
     *            the name
     * @return the string property
     */
    public static String getStringProperty(String name) {
        if (ObjectUtils.isEmpty(getPropertyValue(name, String.class)))
            return "";
        return Normalizer.normalize(getPropertyValue(name, String.class),
                Normalizer.Form.NFKC);
    }

    /**
     * Gets the string.
     *
     * @param <T>
     *            the generic type
     * @param name
     *            the name
     * @param clazz
     *            the clazz
     * @return the string
     */
    private static <T> T getPropertyValue(String name, Class<T> clazz) {
        return BeanUtil.getBean(Environment.class).getProperty(name, clazz);
    }
}
