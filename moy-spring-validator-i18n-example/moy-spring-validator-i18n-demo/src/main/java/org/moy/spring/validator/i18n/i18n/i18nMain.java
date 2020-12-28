package org.moy.spring.validator.i18n.i18n;

import org.moy.spring.validator.i18n.i18n.util.I18nUtil;

import java.util.Locale;

public class i18nMain {

    public static void main(String[] args) {
        System.out.println(I18nUtil.i18nMessage("test"));

        System.out.println(I18nUtil.i18nMessage(Locale.US, "test"));

    }
}
