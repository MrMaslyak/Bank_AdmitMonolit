package org.example.bank.systems;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.bank.database.DatabaseGetter;
import org.example.bank.until.ErrorDialog;

import java.util.Date;


public class JWToken {

    public static String generateToken(int userId) {
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


    public static boolean isExpiresToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expirationDate = jwt.getExpiresAt();
            System.out.println(expirationDate);
            if (expirationDate != null && expirationDate.before(new Date())) {

                return true;
            } else {
                System.out.println("Token is alive");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;

        }
    }

    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret_key");
            JWT.require(algorithm)
                    .withIssuer("MaslyakBank")
                    .build()
                    .verify(token);
            System.out.println("Token is valid");
            return true;
        } catch (Exception e) {
            ErrorDialog.showErrorDialog("Ошибка при проверке токена");
            return false;
        }
    }





}
