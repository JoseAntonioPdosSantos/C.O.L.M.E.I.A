package br.com.colmeia.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.colmeia.model.persistence.dao.implementacao.PalestranteHibernateDAO;
import br.com.colmeia.model.persistence.entity.Palestrante;

@FacesConverter(value = "PalestranteConverter",forClass = Palestrante.class)
public class PalestranteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.equals("Selecione")) {
			if (!value.trim().isEmpty()) {
				try {
					return new PalestranteHibernateDAO().findById(Long.valueOf(value));
				} catch (Exception e) {
					return e.getMessage();
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if(obj instanceof Palestrante && ((Palestrante)obj).getId() != null){
			return ((Palestrante)obj).getId().toString();
		}
		return null;
	}

}
