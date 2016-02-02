package br.com.colmeia.model.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.colmeia.model.persistence.entity.Perfil;

public final class Security {

	private static Perfil getPerfil(String perfil) {
		if(perfil!=null)
			perfil = perfil.toUpperCase();
		switch (perfil) {
		case "ADMINISTRADOR":
			return Perfil.ADMINISTRADOR;
		case "COORDENADOR":
			return Perfil.COORDENADOR;
		case "ORGANIZADOR":
			return Perfil.ORGANIZADOR;
		case "USUARIO":
			return Perfil.USUARIO;
		}
		return null;
	}
	
	public static boolean getAcesso(Perfil currentUser,String permissao){
		Perfil perfil = getPerfil(permissao);
		if(perfil == null)return false;
		if(currentUser == null)return false;
		return currentUser.getPerfil() <= perfil.getPerfil();
	}
	
	public static String criptografarMD5(String password) {
		if (password != null) {
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				md5.update(password.getBytes());
				BigInteger hash = new BigInteger(1, md5.digest());
				password = hash.toString(16);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			while (password.length() < 32) {
				password = "0" + password;
			}
		}
		return password;
	}
}
