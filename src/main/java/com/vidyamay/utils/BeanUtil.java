package com.vidyamay.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * The Class BeanUtil.
 */
@Component
public class BeanUtil implements ApplicationContextAware {

    /** The application context. */
    private static ApplicationContext applicationContext;

    /**
     * Instantiates a new bean util.
     */
    public BeanUtil() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.context.ApplicationContextAware#setApplicationContext
     * (org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        BeanUtil.applicationContext = applicationContext;
    }

    /**
     * Gets the bean.
     *
     * @param <T>
     *            the generic type
     * @param clzz
     *            the clzz
     * @return the bean
     */
    public static <T> T getBean(Class<T> clzz) {
        return BeanUtil.applicationContext.getBean(clzz);
    }

    /**
     * Gets the bean.
     *
     * @param <T>
     *            the generic type
     * @param beanName
     *            the bean name
     * @param clzz
     *            the clzz
     * @return the bean
     */
    public static <T> T getBean(String beanName, Class<T> clzz) {
        return BeanUtil.applicationContext.getBean(beanName, clzz);
    }
}
