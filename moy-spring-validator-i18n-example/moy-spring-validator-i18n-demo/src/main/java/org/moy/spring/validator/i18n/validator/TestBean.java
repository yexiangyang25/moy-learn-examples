package org.moy.spring.validator.i18n.validator;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class TestBean {
    private Long id;
    @NotNull
    @Length(max = 20)
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Z][a-z][0-9]")
    private String passWord;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date createTime;
    @Max(value = 10,message = "")
    @Min(1)
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
