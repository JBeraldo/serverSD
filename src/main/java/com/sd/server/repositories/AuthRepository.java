package com.sd.server.repositories;

import com.sd.server.DAO.JWTSessionDAO;
import com.sd.server.DAO.UserDAO;
import com.sd.server.Exceptions.NoSessionException;
import com.sd.server.Exceptions.UnauthorizedUserException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthRepository {

    private static final String SECRET_KEY = "AoT3QFTTEkj16rCby/TPVBWvfSQHL3GeEz3zVwEd6LDrQDT97sgDY8HJyxgnH79jupBWFOQ1+7fRPBLZfpuA2lwwHqTgk+NJcWQnDpHn31CVm63Or5c5gb4H7/eSIdd+7hf3v+0a5qVsnyxkHbcxXquqk9ezxrUe93cFppxH4/kF/kGBBamm3kuUVbdBUY39c4U3NRkzSO+XdGs69ssK5SPzshn01axCJoNXqqj+ytebuMwF8oI9+ZDqj/XsQ1CLnChbsL+HCl68ioTeoYU9PLrO4on+rNHGPI0Cx6HrVse7M3WQBPGzOd1TvRh9eWJrvQrP/hm6kOR7KrWKuyJzrQh7OoDxrweXFH8toXeQRD8=";

    static UserDAO userDAO = new UserDAO();

    static JWTSessionDAO jwtSessionDAO = new JWTSessionDAO();

    public static String createJwt(String subject,boolean isAdm) {
        return Jwts.builder()
                .claim("user_id", subject)
                .claim("admin", isAdm)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public static Long getUserId(String token) {
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
        return Long.parseLong(jws.getBody().getSubject());
    }

    public static boolean isAdmin(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        return (boolean) claims.get("admin");
    }

    public static void validateAdminUser(long user_id) throws UnauthorizedUserException, NoSessionException {
        if(!userDAO.isUserAdmin(user_id)){
            throw new UnauthorizedUserException();
        }
        if(!jwtSessionDAO.hasUserActiveSession(user_id)){
            throw new NoSessionException();
        }
    }

    public static void validateCommonUser(Long user_id) throws UnauthorizedUserException, NoSessionException {
        if(!jwtSessionDAO.hasUserActiveSession(user_id)){
            throw new NoSessionException();
        }
    }
}
