package cn.ylw.sso.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密器
 *
 * @author yanluwei
 * @date 2021/4/20
 */
@Slf4j
@Component
public class Md5PasswordEncoder implements PasswordEncoder {
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }

    @Override
    public String encode(CharSequence charSequence) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(charSequence.toString().getBytes());
            // 此处可以自定义摘要，个人理解加盐
            byte[] digest = md5.digest();
            return new BigInteger(1, digest).toString(16);
        } catch (NoSuchAlgorithmException e) {
            log.error("不支持该算法，{}", e);
            return charSequence.toString();
        }
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        // 此处很重要，不能忘了
        return s.equals(this.encode(charSequence));
    }
}
