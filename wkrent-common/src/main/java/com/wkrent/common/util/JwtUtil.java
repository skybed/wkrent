package com.wkrent.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具
 * @author Administrator
 */
public class JwtUtil {

    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;

    private static final String TOKEN_SECRET = "ef4cb7b9193a4a8b88fa77a3c1567d48";

    /**
     * 生成签名 2小时后过期
     * @param userAccount 用户账号
     * @param userId 用户Id
     * @return
     */
    public static String sign(String userAccount, String userId){

        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<String, Object>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //附带userAccount ,userId信息生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName", userAccount)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * token 解码
     * @param token 秘钥
     * @return 是否正确
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            //解密
            verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
