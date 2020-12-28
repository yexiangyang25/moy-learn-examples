package org.moy.spring.validator.i18n.validator;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.groups.Default;

public class GroupBean {
    @Max(value = 2, groups = Default.class)
    @Max(value = 3, groups = AddGroup.class)
    @Max(value = 5, groups = UpdateGroup.class)
    private Integer length;


    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    static interface AddGroup {
    }

    static interface UpdateGroup {
    }
}
