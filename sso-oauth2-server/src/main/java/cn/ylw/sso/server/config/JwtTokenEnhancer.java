package cn.ylw.sso.server.config;

import cn.ylw.sso.server.entity.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * token增强
 *
 * @author yanluwei
 * @date 2021/4/20
 */
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        User principal = (User) oAuth2Authentication.getPrincipal();
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
        // 这里注意，一定要创建map，直接使用会报错，因为底层用的emptyMap
        Map<String, Object> map = new HashMap<>(1);
        map.put("user_id", principal.getId());
        token.setAdditionalInformation(map);
        return token;
    }
}
