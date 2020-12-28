package org.moy.spring.validator.i18n.i18n.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>Description: [国际化支持]</p>
 * Created on 2018/12/18
 *
 * @author <a href="mailto: moy25@foxmail.com">叶向阳</a>
 * @version 1.0
 * Copyright (c) 2018 墨阳
 */
public class I18nUtil {

    private static final Logger LOG = LoggerFactory.getLogger(I18nUtil.class);
    private static final String CLASS_PATH_BASE_NAME = "message/ValidationMessages";
    private static final Locale DEFAULT_LOCALE = Locale.CHINA;

    public static String i18nMessage(String messageKey) {
        return i18nMessage(CLASS_PATH_BASE_NAME, DEFAULT_LOCALE, messageKey);
    }

    public static String i18nMessage(Locale locale, String messageKey) {
        return i18nMessage(CLASS_PATH_BASE_NAME, locale, messageKey);
    }

    public static String i18nMessage(String baseName, Locale locale, String messageKey) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
        if (resourceBundle.containsKey(messageKey)) {
            return resourceBundle.getString(messageKey);
        } else {
            LOG.warn("ResourceBundle: {} can not find messageKey: {} , Locale: {}", baseName, messageKey, locale);
        }
        return messageKey;
    }
}
