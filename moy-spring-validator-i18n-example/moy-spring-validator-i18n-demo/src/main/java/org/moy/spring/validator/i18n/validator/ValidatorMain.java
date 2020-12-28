package org.moy.spring.validator.i18n.validator;

import org.moy.spring.validator.i18n.validator.uitl.ValidationUtil;

public class ValidatorMain {

    public static void main(String[] args) {
//        useBasic();

//        useCustom();

        useGroup();
    }

    private static void useGroup() {
        GroupBean groupBean = new GroupBean();
        groupBean.setLength(3);
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(groupBean);
        printResult(validResult);

        groupBean.setLength(3);
        ValidationUtil.ValidResult validResultAddGroup = ValidationUtil.validateBean(groupBean, GroupBean.AddGroup.class);
        printResult(validResultAddGroup);

        groupBean.setLength(4);
        ValidationUtil.ValidResult validResultUpdateGroup = ValidationUtil.validateBean(groupBean, GroupBean.UpdateGroup.class);
        printResult(validResultUpdateGroup);
    }

    private static void useCustom() {
        CustomBean customBean = new CustomBean();
        customBean.setBirthday("233");
//        customBean.setBirthday("2020-09-03");
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(customBean);
        printResult(validResult);
    }

    private static void printResult(ValidationUtil.ValidResult validResult) {
        if (validResult.hasErrors()) {
            System.err.println(validResult.getErrors());
        } else {
            System.out.println("ok");
        }
    }

    private static void useBasic() {
        TestBean bean = new TestBean();
        bean.setId(10L);
        bean.setName("wokalakala");
        bean.setPassWord("密码");
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(bean);
        printResult(validResult);
    }
}
