/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upf.agendamento.view.util.relatorios;

import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Ademilson
 */
public class JDataSource implements JRDataSource {

    private Object[][] data = null;
    private List lstData = null;
    private int nrAtributos = 0;
    private int dataLength = 0;
    private int index = -1;

    /**
     *
     */
    public JDataSource(Object[][] data) {
        this.data = data;
        this.nrAtributos = data[0].length;
        this.dataLength = data.length;
    }
    
    
     public JDataSource(List data) {
        this.lstData = data;
        this.nrAtributos = (data.isEmpty()) ? 0 : ((Object[])data.get(0)).length;
        this.dataLength = data.size();
    }

    /**
     *
     */
    public boolean next() throws JRException {
        index++;

        return (index < dataLength);
    }

    /**
     *
     */
    public Object getFieldValue(JRField field) throws JRException {
        String fieldName = field.getName();
        for (int i = 1; i <= nrAtributos; i++) {
            if (("Dado" + i).equals(fieldName)) {
                if (lstData != null) {
                     return "" + ((Object[])lstData.get(index))[i - 1]; 
                }
                return "" + data[index][i - 1];
            }
        }
        return "";
    }
}
