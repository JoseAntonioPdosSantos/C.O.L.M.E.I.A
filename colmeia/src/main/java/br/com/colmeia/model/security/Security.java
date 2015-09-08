package br.com.colmeia.model.security;

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
}
