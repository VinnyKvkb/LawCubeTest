package com.lawcube.helper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	//@Value("${secret}")
	private String secret="lawcubetest123$";

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		// return getClaimFromToken(token, Claims::getSubject);
		return getAllClaimsFromToken(token);
	}

	// for retrieveing any information from token we will need the secret key
	private String getAllClaimsFromToken(String token) {
		// parse the token.
		Algorithm algorithm = Algorithm.HMAC256(secret); // use more secure key
		JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build(); // Reusable verifier instance
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("email").asString();
	}

//	//check if the token has expired
//	private Boolean isTokenExpired(String token) {
//		final Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}

	// generate token for user
	public String generateToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, email);
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		String token = JWT.create().withIssuer("auth0").withClaim("email", subject).sign(algorithm);

		return token;

	}

	public String validateToken(String token) {
		final String username = getUsernameFromToken(token);
		System.out.println("username--->" + username);
		return username;
	}

}
