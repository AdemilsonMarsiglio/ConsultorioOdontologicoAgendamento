package br.com.upf.agendamento.view.util.relatorios;

import javax.swing.JButton;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

/**
 * 
 * @author Ademilson
 */
public class JViwer extends JRViewer {

    public JViwer(JasperPrint jprint) {
        super(jprint);
    }

    public void setAjusteLargura(boolean status) {
        this.btnFitWidth.setSelected(status);
    }
    
    public void addButtonToolBar(JButton btn, int index) {
        
        if (index != -1) {
            tlbToolBar.add(btn, index);
        } else {
            tlbToolBar.add(btn);
        }
    }
}