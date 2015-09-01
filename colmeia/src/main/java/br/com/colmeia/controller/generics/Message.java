package br.com.colmeia.controller.generics;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public abstract class Message {

	protected Boolean success = false;
	public static final int SIZE_ZERO = 0;
	public static final int SUCCESS_UPDATE = 1;
	public static final int FAILURE_UPDATE = 2;
	public static final int SUCCESS_DELETE = 3;
	public static final int FAILURE_DELETE = 4;
	public static final int SUCCESS_RECORD = 5;
	public static final int FAILURE_RECORD = 6;
	public static final int FAILURE_FILL_DATA = 7;
	public static final int DUPLICATE_RECORD = 8;
	public static final int RECORD = 9;
	public static final int DELETE = 10;
	public static final int UPDATE = 11;
	public static final int ERROR_UNEXPECTED = 12;
	public static final int FAILURE_LOGIN = 13;
	public static final int ERROR_FIND = 14;

	protected void message(Severity severity, String title, String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severity, title, message));
	}

	protected void message(String title, String message) {
		FacesMessage faces = new FacesMessage(FacesMessage.SEVERITY_INFO,
				title, message);
		FacesContext.getCurrentInstance().addMessage(null, faces);
	}

	protected void message(final Integer condition) {
		FacesMessage faces = null;
		switch (condition) {
		case SIZE_ZERO:
			faces = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
					"Não foi lacalizado nenhum dado para esta consulta!");
			break;
		case SUCCESS_UPDATE:
			faces = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação",
					"Cadasto atualizado com sucesso!");
			break;
		case FAILURE_UPDATE:
			faces = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso",
					"Não foi possível atualizar este cadastro!");
			break;
		case SUCCESS_DELETE:
			faces = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação",
					"Cadastro excluído com sucesso!");
			break;
		case FAILURE_DELETE:
			faces = new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"Aviso",
					"Não foi possível excluir este cadastro. Verifique se este registro não está sendo usando em outro cadastro!");
			break;
		case SUCCESS_RECORD:
			faces = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação",
					"Cadastro gravado com sucesso!");
			break;
		case FAILURE_RECORD:
			faces = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso",
					"Não foi possível gravar este cadastro!");
			break;
		case FAILURE_FILL_DATA:
			faces = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso",
					"Os campos não foram preenchidos corretamente!");
			break;
		case DUPLICATE_RECORD:
			faces = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Registro Duplicado", "Este cadastro já existe!");
			break;
		case FAILURE_LOGIN:
			faces = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"INFORMAÇÃO", "Login ou senha inválido. :'(");
			break;
		case ERROR_UNEXPECTED:
			faces = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"ERROR", "Ocorreu um erro inesperado. Favor contatar o administrador do sistema. :'(");
			break;
		case ERROR_FIND:
			faces = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Aviso", "Item não localizado. :'(");
			break;
		}
		FacesContext.getCurrentInstance().addMessage(null, faces);
	}

	public boolean processMessage(Boolean arg0, int typeOfOperation) {
		int success = 1;
		int failed = 0;

		switch (typeOfOperation) {
		case RECORD:
			success = SUCCESS_RECORD;
			failed = FAILURE_RECORD;
			break;
		case UPDATE:
			success = SUCCESS_UPDATE;
			failed = FAILURE_UPDATE;
			break;
		case DELETE:
			success = SUCCESS_DELETE;
			failed = FAILURE_DELETE;
			break;
		}
		if (arg0) {
			message(success);
			return true;
		} else {
			message(failed);
			return false;
		}
	}

	public void checkList(List<?> args) {
		if (args != null)
			if (args.size() == 0)
				message(Message.SIZE_ZERO);
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean gravado) {
		this.success = gravado;
	}

}
