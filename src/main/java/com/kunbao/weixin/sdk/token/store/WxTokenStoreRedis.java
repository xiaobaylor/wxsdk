package com.kunbao.weixin.sdk.token.store;

import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.token.WXTokenController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by xueyu on 16/10/26.
 */
@Slf4j
public class WxTokenStoreRedis {

    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    @Cacheable
    public void init() {
        log.info("WXTokenController init TokenProtector redis");
        WXTokenController.init(new WXTokenController.TokenProtector() {
            //TODO 有并发问题
            public synchronized String refreshToken() throws WXException {
                log.debug("refreshToken");
                String token = WXTokenController.LonglyTokenProtector.getTokenLongly();
                stringRedisTemplate.opsForValue().set("accesstokenkey", token);
                stringRedisTemplate.expire("accesstokenkey", 7030, TimeUnit.SECONDS);
                return token;
            }

            public String getToken() throws WXException {
                String token = stringRedisTemplate.opsForValue().get("accesstokenkey");
                if (token == null) {
                    token = refreshToken();
                }
                return token;
            }
        });
    }
}
