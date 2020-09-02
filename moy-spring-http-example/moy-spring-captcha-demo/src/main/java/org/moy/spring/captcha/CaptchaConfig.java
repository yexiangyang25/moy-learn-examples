package org.moy.spring.captcha;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class CaptchaConfig {


    @Bean
    public Producer defaultKaptcha() {
        Properties properties = new Properties();
        properties.put("kaptcha.session.key", "kaptcha.code");
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        // 渲染效果：水纹：WaterRipple；鱼眼：FishEyeGimpy；阴影：ShadowGimpy
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        properties.put("kaptcha.image.width", "90");
        properties.put("kaptcha.image.height", "33");
        properties.put("kaptcha.textproducer.font.size", "25");
        properties.put("kaptcha.textproducer.char.length", "4");
        properties.put("kaptcha.textproducer.char.space", "5");
        // 和登录框背景颜色一致
        properties.put("kaptcha.background.clear.from", "247,247,247");
        properties.put("kaptcha.background.clear.to", "247,247,247");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
