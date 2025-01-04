package org.example.bank.systems;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;


public class GenerationJWT {

    public static  String generateToken(int userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret_key");
                 String token = JWT.create()
                    .withIssuer("MaslyakBank")
                    .withClaim("userId", userId)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // Токен на 1 час
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при создании токена");
        }
    }

}
