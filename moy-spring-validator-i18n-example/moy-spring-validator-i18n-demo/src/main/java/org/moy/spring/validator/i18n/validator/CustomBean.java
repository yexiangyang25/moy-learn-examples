package org.moy.spring.validator.i18n.validator;

import org.moy.spring.validator.i18n.validator.uitl.DateValidator;

public class CustomBean {
    @DateValidator(dateFormat = "yyyy-MM-dd")
    private String birthday;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
