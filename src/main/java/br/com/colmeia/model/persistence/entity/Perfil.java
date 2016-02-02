package br.com.colmeia.model.persistence.entity;

public enum Perfil {

	ADMINISTRADOR(1), COORDENADOR(2), ORGANIZADOR(3), USUARIO(4);
	private int perfil;

	private Perfil(int perfil) {
		this.perfil = perfil;
	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

}
