package cn.ylw.sso.resources.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 重写
 *
 * @author yanluwei
 * @date 2021/4/21
 */
public class SSOAuthenticationConverter extends DefaultUserAuthenticationConverter {
    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey("user_name")) {
            Map<String, Object> user = new HashMap<>();
            user.put("user_name", map.get("user_name"));
            user.put("user_id", map.get("user_id"));
            return new UsernamePasswordAuthenticationToken(user, "N/A", Collections.emptyList());
        } else {
            return null;
        }
    }
}
