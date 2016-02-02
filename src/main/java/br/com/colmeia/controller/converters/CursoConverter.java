package br.com.colmeia.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.colmeia.model.persistence.dao.implementacao.CursoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Curso;

@FacesConverter(value = "CursoConverter",forClass = Curso.class)
public class CursoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.equals("Selecione")) {
			if (!value.trim().isEmpty()) {
				try {
					return new CursoHibernateDAO().findById(Long.valueOf(value));
				} catch (Exception e) {
					return e.getMessage();
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if(obj instanceof Curso && ((Curso)obj).getId() != null){
			return ((Curso)obj).getId().toString();
		}
		return null;
	}

}
