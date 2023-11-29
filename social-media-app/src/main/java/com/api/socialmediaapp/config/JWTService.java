/*
 * package com.api.socialmediaapp.config;
 * 
 * import java.security.Key; import java.util.Date; import java.util.HashMap;
 * import java.util.Map;
 * 
 * import org.springframework.stereotype.Component;
 * 
 * import io.jsonwebtoken.Jwts; import io.jsonwebtoken.SignatureAlgorithm;
 * import io.jsonwebtoken.io.Decoders; import io.jsonwebtoken.security.Keys;
 * 
 * @Component public class JWTService {
 * 
 * private static final CharSequence SECRET =
 * "091de41fe2df55f1b9c99534e98cc866b95f8a21d6026c533a8e1796ed05e44d8d8f2fab4ac14c5d5c867f04da1704716641742c9d1677c69d87474c8262a970";
 * 
 * public String generateToken(String userName) { Map<String, Object> claims =
 * new HashMap<>(); return createToken(claims, userName); }
 * 
 * private String createToken(Map<String, Object> claims, String userName) {
 * return Jwts.builder().claims(claims).subject(userName).issuedAt(new
 * Date(System.currentTimeMillis())) .expiration(new
 * Date(System.currentTimeMillis() + 1000 * 60 * 30))
 * .signWith(SignatureAlgorithm.HS256, getSignKey()).compact();
 * 
 * }
 * 
 * private Key getSignKey() { byte[] keyBytes = Decoders.BASE64.decode(SECRET);
 * return Keys.hmacShaKeyFor(keyBytes); } }
 */