package br.com.colmeia.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.colmeia.model.persistence.dao.implementacao.IngressoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Ingresso;

@FacesConverter(value = "IngressoConverter", forClass = Ingresso.class)
public class IngressoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.equals("Selecione")) {
			if (!value.trim().isEmpty()) {
				try {
					return new IngressoHibernateDAO().findById(Long.valueOf(value));
				} catch (Exception e) {
					return e.getMessage();
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Ingresso && ((Ingresso) obj).getId() != null) {
			return ((Ingresso) obj).getId().toString();
		}
		return null;
	}

}
