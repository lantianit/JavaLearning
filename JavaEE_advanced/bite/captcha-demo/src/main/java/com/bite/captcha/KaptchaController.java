package com.bite.captcha;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RequestMapping("/admin")
@RestController
public class KaptchaController {
    private static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";
    private static final String KAPTCHA_SESSION_DATE = "KAPTCHA_SESSION_DATE";
    private static final long TIME_OUT = 60*1000;//一分钟, 毫秒数
    /**
     * 校验验证码是否正确
     * @param inputCaptcha  用户输入的验证码
     * @return
     */
    @RequestMapping("/check")
    public boolean check(String inputCaptcha, HttpSession session){
        //1. 判断输入的验证码是否为空
        //2. 获取生成的验证码
        //3. 比对 生成的验证码和输入的验证码是否一致
        //4. 确认验证码是否过期
        if (!StringUtils.hasLength(inputCaptcha)){
            return false;
        }
        //生成的验证码(正确的验证码)
        String saveCaptcha = (String)session.getAttribute(KAPTCHA_SESSION_KEY);
        Date savaCaptchaDate = (Date)session.getAttribute(KAPTCHA_SESSION_DATE);

        if (inputCaptcha.equalsIgnoreCase(saveCaptcha)){//不区分大小写
            if (savaCaptchaDate!=null || System.currentTimeMillis()-savaCaptchaDate.getTime()<TIME_OUT){
                return true;
            }
        }
        return false;
    }
}
