package org.moy.spring.captcha;

import com.google.code.kaptcha.GimpyEngine;

import java.awt.image.BufferedImage;

public class GimpyEngineImpl implements GimpyEngine {

    @Override
    public BufferedImage getDistortedImage(BufferedImage baseImage) {
        return baseImage;
    }
}
