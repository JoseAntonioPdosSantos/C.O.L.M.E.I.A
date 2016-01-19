package br.com.colmeia.model.persistence.dao.implementacao;

import java.sql.Timestamp;
import java.util.Date;

import br.com.colmeia.model.persistence.dao.UsuarioDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Perfil;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.security.Security;

public class UsuarioHibernateDAO extends GenericHibernateDAO<Usuario, Long>implements UsuarioDAO {

	public UsuarioHibernateDAO() {
		@SuppressWarnings("unchecked")
		long total = count("SELECT COUNT(usu) FROM Usuario usu");
		
		if (total == 0) {
			Usuario usuario = new Usuario();
			usuario.setNome("José Antonio");
			usuario.setConfirmarSenha(Security.criptografarMD5("3341jd"));
			usuario.setSenha(Security.criptografarMD5("3341jd"));
			usuario.setCpf("991.989.161-49");
			usuario.setEmail("d_jota_a@hotmail.com");
			usuario.setPerfil(Perfil.ADMINISTRADOR);
			usuario.setVersao(new Timestamp(new Date().getTime()));
			usuario.setAtivo(true);
			insert(usuario);
		}else{
			try {
				Usuario usu = findById(1L);
				if(usu.getSenha() != null){
					if(usu.getSenha().trim().isEmpty()){
						usu.setSenha(Security.criptografarMD5("3341jd"));
						update(usu);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
