import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.example.myblog.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

@RestController
class CaptchaController {
    @Value("${imagepath}")
    private String imagepath;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/getcaptcha")
    public AjaxResult getCaptcha() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(128,80);
        String uuid = UUID.randomUUID().toString().replace("-","");
        lineCaptcha.write(imagepath+uuid+".png");
        String url = "/image/" + uuid + ".png";
        redisTemplate.opsForValue().set(uuid,lineCaptcha.getCode());
        HashMap<String,String> result = new HashMap<>();
        result.put("codeurl",url);
        result.put("codekey",uuid);
        return AjaxResult.succ(result);
    }
}