package com.sds.weatherstory.jwt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public JwtUtil() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
	}
	
	public PublicKey getPublicKeyFromString(String base64Key) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] keybytes = Base64.getDecoder().decode(base64Key);
		
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keybytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		return keyFactory.generatePublic(keySpec);
	}
	
	public String generateToken(String id, String role, Long expireTime) {
		log.debug("privateKey : " + privateKey);
		return Jwts.builder()
//				.setSubject(username)	//사용자 정보(username)
					.setSubject(id) // 헤더 역할
//				.claim("role", role)
					.claim("role", role) // claim() 메서드를 이용하여 넣고 싶은 데이터 넣을 수 있음.(몸체)
					.setIssuedAt(new Date()) // 발행 시간
					.setExpiration(new Date(System.currentTimeMillis() + expireTime)) // 만료시간 milisecond 단위
					.signWith(SignatureAlgorithm.RS256, privateKey).compact(); // String화 시킴. 생성된 jwt 토큰 생성 밑 직렬화(string으로
	}
	
	public String getEncodedPublicKey() {
		byte[] publicKeyBytes = publicKey.getEncoded();
		
		String encodedPublicKey = Base64.getEncoder().encodeToString(publicKeyBytes);
		return encodedPublicKey;
	}
}
