package br.com.colmeia.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.colmeia.model.persistence.dao.implementacao.InstituicaoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Instituicao;

@FacesConverter(value = "InstituicaoConverter", forClass = Instituicao.class)
public class InstituicaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.equals("Selecione")) {
			if (!value.trim().isEmpty()) {
				try {
					return new InstituicaoHibernateDAO().findById(Long.valueOf(value));
				} catch (Exception e) {
					return e.getMessage();
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Instituicao && ((Instituicao) obj).getId() != null) {
			return ((Instituicao) obj).getId().toString();
		}
		return null;
	}

}
