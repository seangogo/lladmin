package com.custom.sean.common.utils.basics;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author seangogo
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;


    /**
     * 从redis中获取登陆账户的相关信息
     * @param key token 对象 key
     * @param clz token 对象 value 类型
     */
    private <T> T getObjectFromToken(String key, Class<T> clz){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("opt".getBytes("UTF-8"))
                    .parseClaimsJws(token).getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return claims.get(key,clz);
    }

    public String getUserName() {
        return getObjectFromToken("user_name",String.class);
    }

    public String getOrgCode() {
        return getObjectFromToken("orgCode",String.class);
    }

    public Set<String> getRoleCodes() {
        List<String> list=getObjectFromToken("authorities",List.class);
        List<String> roles=list.stream().filter(s -> s.startsWith("ROLE_")).collect(Collectors.toList());
        HashSet set =new HashSet();
        roles.forEach(s -> set.add(s.substring(5)));
        return set;
    }

    public Set<String> getResourceCodes(HttpServletRequest request) {
        List<String> list=getObjectFromToken("authorities",List.class);
        return new HashSet(list.stream().filter(s -> !s.startsWith("ROLE_")).collect(Collectors.toList()));
    }

}