package com.posidex.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60l;
	private static final String SECRET_KEY = "p9/L3J7GaSo/PcXFGJniH0ab7BiV5ow35D10ytqhF57qOoF/HIfpfURKlUKxpwNS0ApxQdQgMosRypIX4QxIGAhMCQD+5j3oqO9Zex0utQQb0ODBedgD8A9OUphXVvejow4u+EiqY0hSBXGOHDRn1kYkgPccC5Ly7AapaGofwLrtO4vDDpO/ZT+WZVPE9ZAfJSAeZqblAEmz1Mcftmyz3d96UcP8ecmRwOxcIcFYwS4bkrbSrb5s9K8EavGOJgYiTSCLuQf2NiEYvLfKXc5J7PR7RnXDpcl7b4ztFsVHMeIltDxaNHssnUe6YxczsFKSX1l+c6sXmrND0lDunbErgTT/fepVxDkbO0zloftcYp8=";

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String userName) {
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(getSiginKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSiginKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public Date getGenerationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}
}