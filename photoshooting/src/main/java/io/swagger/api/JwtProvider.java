package io.swagger.api;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.fasterxml.classmate.util.ResolvedTypeCache.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

	private KeyStore keyStore;
	
	@PostConstruct
    public void init() throws java.io.IOException {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/photoshooting.jks");
            keyStore.load(resourceAsStream, "photoshooting".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringBlogException("Exception occured while loading keystore");
        }

    }
	
	public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .compact();
    }

	private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("photoshooting", "photoshooting".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringBlogException("Exception occured while retrieving public key from keystore");
        }
    }

	public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        return true;
    }

	private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate("photoshooting").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringBlogException("Exception occured while retrieving public key from keystore");
        }
    }

	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(getPublickey())
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
		
	}
}
 