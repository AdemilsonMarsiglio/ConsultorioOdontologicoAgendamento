package br.com.upf.agendamento.view.util;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Ademilsom
 */
public class StatusBar extends JPanel{

    public StatusBar(int linhaCorrente, int totalLinhas) {
        super(new BorderLayout());
        
        add(BorderLayout.WEST, new JLabel("Linha selecionada: " + linhaCorrente));
        add(BorderLayout.CENTER, new JLabel("Total de Linhha: " + totalLinhas, JLabel.RIGHT));
        
        setBorder(new EtchedBorder(2));
    }
    
    public StatusBar(String mensagem, String versao){
        super(new BorderLayout());
        
        JLabel lblMensagem = new JLabel(mensagem);
        
        add(BorderLayout.WEST, lblMensagem);
        add(BorderLayout.CENTER, new JLabel(versao, JLabel.RIGHT));
        
        setBorder(new EtchedBorder(2));
        
    }
}
