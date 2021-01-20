package com.pulse.spe.domain.service;


import com.pulse.spe.domain.model.Usuario;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	public String gerarToken(Authentication authenticate){


		Usuario logado = (Usuario) authenticate.getPrincipal();
		Date hoje =  new Date();
		Date dataExpiracao = new Date(hoje.getTime() + 3600000 * 12);
		
		return Jwts.builder()
				.setIssuer("portalApi")
				.setSubject(logado.getId().toString())
				.claim("usuario",logado )
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, "p0rt4l@xbc").compact();
	}

	public boolean isValido(String token) {
		try {
			@SuppressWarnings("unused")
			Jws<Claims> claims = Jwts.parser().setSigningKey("p0rt4l@xbc").parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey("p0rt4l@xbc").parseClaimsJws(token).getBody();
	    return Long.parseLong(claims.getSubject());
	}


}
