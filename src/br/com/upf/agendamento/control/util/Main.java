package br.com.upf.agendamento.control.util;

import br.com.upf.agendamento.model.util.HibernateUtil;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Ademilsom
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        HibernateUtil.getSession();
        JFrameMain frameMain = new JFrameMain();
        frameMain.setVisible(true);
    }

}
