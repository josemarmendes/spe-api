package com.pulse.spe.domain.dto;


import lombok.Getter;

public class TokenDto {
	@Getter
	private String token;
	@Getter
	private String string;

	public TokenDto(String token, String string) {
		this.token = token;
		this.string = string;
	}
}