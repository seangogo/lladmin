package com.seangogo.modules.security.rest;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.seangogo.modules.security.utils.ImgResult;
import com.seangogo.modules.security.utils.SecurityUtils;
import com.seangogo.modules.security.utils.VerifyCodeUtils;
import com.seangogo.modules.system.service.UserService;
import com.seangogo.tools.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 部门数据库操作类
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping(value = "/info")
    public ResponseEntity getUserInfo() {
        String username = SecurityUtils.getUsername();
        return ResponseEntity.ok(userService.findUserInfo(username));
    }

    /**
     * 获取验证码
     */
    @GetMapping(value = "vCode")
    public ImgResult getCode() throws IOException {
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = IdUtil.simpleUUID();
        redisService.saveCode(uuid, verifyCode);
        // 生成图片
        int w = 120, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            return new ImgResult(Base64.encode(stream.toByteArray()), uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            stream.close();
        }
    }
}
