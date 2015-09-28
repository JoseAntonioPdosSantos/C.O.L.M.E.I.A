package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Ingresso;
import br.com.colmeia.model.persistence.service.implementacao.IngressoService;

@ManagedBean
@ViewScoped
public class IngressoController extends Controller<Ingresso, IngressoService> {

	private static final long serialVersionUID = 8490761805658830369L;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}

	@Override
	public void limpar() {
		entidade = new Ingresso();
		setEditando_registro(false);
	}

	@Override
	public IngressoService getService() {
		return new IngressoService();
	}

	public void setService(IngressoService service) {
		this.service = service;
	}

	public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
             
            cell.setCellStyle(cellStyle);
        }
    }
}
