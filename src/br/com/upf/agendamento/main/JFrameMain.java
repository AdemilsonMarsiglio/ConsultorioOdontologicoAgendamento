package br.com.upf.agendamento.main;

import br.com.parcerianet.utilcomp.Util;
import br.com.parcerianet.view.padroes.JPFramePrincipal;
import br.com.upf.agendamento.view.AgendamentoMain;
import br.com.upf.agendamento.view.PacienteMain;
import br.com.upf.agendamento.view.SobreForm;
import br.com.upf.agendamento.view.imagens.Imagens;
import br.com.upf.agendamento.view.util.StatusBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Ademilsom
 */
public class JFrameMain extends JFrame {

    private ImageIcon imgMenuBlank = new ImageIcon(JPFramePrincipal.class.getResource("/br/com/parcerianet/view/imagens/blank.gif"));

    /**
     * Creates new form JFrameMain
     */
    public JFrameMain() {
        initComponents();

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setTitle("Agendamento de Pacietes");
        this.setIconImage(Imagens.IMG_DIARY.getImage());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                acaoSair();
            }
        });

        btnAgenda.setBorderPainted(false);
        btnPaciente.setBorderPainted(false);
        toolBar.addSeparator();

        //** 1 -> Layout todo do sistema.
        //** 2 -> Funcionalidades Visiveis.
        //** 3 -> Funcionalidades/Correções.
        this.add(BorderLayout.SOUTH, new StatusBar("  SISTEMA DE AGENDAMENTO DE PACIENTES", "VERSÃO 1.3.4  "));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        btnPaciente = new javax.swing.JButton();
        btnAgenda = new javax.swing.JButton();
        desktopPane = new JDesktopPane(){
            @Override
            public void paintComponent(Graphics g){
                g.drawImage(imgMenuBlank.getImage(),30,0,Color.WHITE,this);
            }
        };
        jMenuBar1 = new javax.swing.JMenuBar();
        menuItemSistema = new javax.swing.JMenu();
        menuItemSair = new javax.swing.JMenuItem();
        menuItemPrograma = new javax.swing.JMenu();
        itemMenuPaciente = new javax.swing.JMenuItem();
        itemMenuAgenda = new javax.swing.JMenuItem();
        menuItemRelatorios = new javax.swing.JMenu();
        menuAjud = new javax.swing.JMenu();
        menuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        btnPaciente.setIcon(Imagens.IMG_PEOPLE);
        btnPaciente.setToolTipText("Paciente");
        btnPaciente.setFocusable(false);
        btnPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacienteActionPerformed(evt);
            }
        });
        toolBar.add(btnPaciente);

        btnAgenda.setIcon(Imagens.IMG_NOTEBOOK);
        btnAgenda.setToolTipText("Agenda");
        btnAgenda.setFocusable(false);
        btnAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaActionPerformed(evt);
            }
        });
        toolBar.add(btnAgenda);

        getContentPane().add(toolBar, java.awt.BorderLayout.NORTH);

        desktopPane.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        getContentPane().add(desktopPane, java.awt.BorderLayout.CENTER);

        menuItemSistema.setText("Sistema");

        menuItemSair.setText("Sair");
        menuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSairActionPerformed(evt);
            }
        });
        menuItemSistema.add(menuItemSair);

        jMenuBar1.add(menuItemSistema);

        menuItemPrograma.setText("Programas");

        itemMenuPaciente.setText("Paciente");
        itemMenuPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuPacienteActionPerformed(evt);
            }
        });
        menuItemPrograma.add(itemMenuPaciente);

        itemMenuAgenda.setText("Agenda");
        itemMenuAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuAgendaActionPerformed(evt);
            }
        });
        menuItemPrograma.add(itemMenuAgenda);

        jMenuBar1.add(menuItemPrograma);

        menuItemRelatorios.setText("Relatórios");
        jMenuBar1.add(menuItemRelatorios);

        menuAjud.setText("Ajuda");

        menuItemSobre.setText("Sobre");
        menuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSobreActionPerformed(evt);
            }
        });
        menuAjud.add(menuItemSobre);

        jMenuBar1.add(menuAjud);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemMenuPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuPacienteActionPerformed
        criaInternalFrame("Paciente", new PacienteMain(), new Dimension(800, 600));
    }//GEN-LAST:event_itemMenuPacienteActionPerformed

    private void btnPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacienteActionPerformed
        criaInternalFrame("Paciente", new PacienteMain(), new Dimension(800, 600));
    }//GEN-LAST:event_btnPacienteActionPerformed

    private void menuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSairActionPerformed
        acaoSair();
    }//GEN-LAST:event_menuItemSairActionPerformed

    private void itemMenuAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuAgendaActionPerformed
        criaInternalFrame("Agendamento", new AgendamentoMain(), new Dimension(800, 600));
    }//GEN-LAST:event_itemMenuAgendaActionPerformed

    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaActionPerformed
        criaInternalFrame("Agendamento", new AgendamentoMain(), new Dimension(800, 600));
    }//GEN-LAST:event_btnAgendaActionPerformed

    private void menuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSobreActionPerformed
        JDialog dlgSobre = new JDialog(getFramePrincipal(), "Sobre");
        dlgSobre.add(new SobreForm());
        dlgSobre.setSize(800, 600);
        dlgSobre.setModal(true);
        dlgSobre.setResizable(false);
        dlgSobre.setLocationRelativeTo(null);
        dlgSobre.setVisible(true);
    }//GEN-LAST:event_menuItemSobreActionPerformed

//    public static void criaDialogRelatorio(String title, Component component) {
//
//
//        JInternalFrame[] allFrames = desktopPane.getAllFrames();
//        //** Testa existe algum JInternalFrame Selecionado..
//        int frameY = 0, frameX = 0;
//        Dimension tamanho = new Dimension(790, 470);
//        if (allFrames.length > 0 && allFrames[0].isSelected()) {
//            frameY = allFrames[0].getY() + 100;
//            frameX = allFrames[0].getX() + 100;
//            if (frameX + tamanho.width > desktopPane.getWidth()) {
//                frameX = 0;
//            }
//            if (frameY + tamanho.height > desktopPane.getHeight()) {
//                frameY = 0;
//            }
//        }
//
//        JInternalFrame jifRel = new JInternalFrame(title, true, true, true, true);
//        jifRel.setName("R:" + title);
//        jifRel.setLayout(new BorderLayout());
//        jifRel.add(component, BorderLayout.CENTER);
//        jifRel.reshape(frameX, frameY, tamanho.width, tamanho.height);
//        desktopPane.add(jifRel);
//
//        //**Adiciona Listener para o internalFrame
//        jifRel.addInternalFrameListener(new InternalFrameListener() {
//            public void internalFrameOpened(InternalFrameEvent evt) {
//            }
//
//            public void internalFrameIconified(InternalFrameEvent evt) {
//            }
//
//            public void internalFrameDeiconified(InternalFrameEvent evt) {
//            }
//
//            public void internalFrameDeactivated(InternalFrameEvent evt) {
//            }
//
//            public void internalFrameClosing(InternalFrameEvent evt) {
//            }
//
//            //** Chamado depois que o internalFrame é fechado
//
//            public void internalFrameClosed(InternalFrameEvent evt) {
//                for (int i = 0; i < mniWinRel.getItemCount(); i++) {
//                    String nameInternalFrame = ((JInternalFrame) evt.getSource()).getName();
//                    if (mniWinRel.getItem(i).getName().equals(nameInternalFrame)) {
//                        mniWinRel.remove(mniWinRel.getItem(i));
//                        break;
//                    }
//                }
//            }
//
//            //** Chamado quando a janela se torna ativa
//
//            public void internalFrameActivated(InternalFrameEvent evt) {
//            }
//        });
//
//        
//        jifRel.setVisible(true);
//
//    }

    //** Localização do próximo internalFrame
    private static int nextFrameX = 0;
    private static int nextFrameY = 0;
    private static int frameDistance = 0;

    public static void criaInternalFrame(String title, Component component, Dimension size) {

        JInternalFrame[] allFrames = desktopPane.getAllFrames();

        if (buscaInternalFrame(title)) {
            return;
        }

        //** Testa existe algum JInternalFrame Selecionado..
        if (allFrames.length > 0 && allFrames[0].isSelected()) {
            nextFrameY = allFrames[0].getY() + frameDistance;
            nextFrameX = allFrames[0].getX() + frameDistance;
            if (nextFrameX + size.width > desktopPane.getWidth()) {
                nextFrameX = 0;
            }
            if (nextFrameY + size.height > desktopPane.getHeight()) {
                nextFrameY = 0;
            }
        } else {
            nextFrameY = 0;
            nextFrameX = 0;
        }

        JInternalFrame internalFrame = new JInternalFrame(title, true, true, true, true) {

            @Override
            public boolean isSelected() {
                boolean selected = super.isSelected();

                if (selected) {
                    renameButton(this, "<html><body><b>" + this.getTitle() + "</b></body></html>");
                } else {
                    renameButton(this, "<html><body>" + this.getTitle() + "</body></html>");
                }

                return selected;

            }
        };
        internalFrame.add(component);

        desktopPane.add(internalFrame);

        internalFrame.setSize(size);
        internalFrame.reshape(nextFrameX, nextFrameY, size.width, size.height);
        internalFrame.setMinimumSize(size);

        Container contnerNewFrame = internalFrame.getContentPane();

        if (frameDistance == 0) {
            frameDistance = internalFrame.getHeight() - contnerNewFrame.getHeight();
        }
        nextFrameX += frameDistance;
        nextFrameY += frameDistance;

        internalFrame.setOpaque(true);
        internalFrame.setVisible(true);

        internalFrame.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                super.internalFrameClosed(e);

                for (Component component : toolBar.getComponents()) {
                    if (component instanceof JButton) {
                        JButton button = ((JButton) component);
                        if (button.getName() != null) {
                            if (button.getName().equals(((JInternalFrame) e.getSource()).getTitle())) {
                                toolBar.remove(button);
                                toolBar.repaint();
                            }
                        }
                    }
                }

            }
        });

        criaBotaoLink(title);
    }

    private static void renameButton(JInternalFrame internalFrame, String name) {

        for (Component component : toolBar.getComponents()) {
            if (component instanceof JButton) {
                JButton button = ((JButton) component);
                if (button.getName() != null) {
                    if (button.getName().equals(internalFrame.getTitle())) {
                        button.setText(name);
                        toolBar.repaint();
                    }
                }
            }
        }

    }

    private static boolean buscaInternalFrame(String title) {

        JInternalFrame[] allFrames = desktopPane.getAllFrames();

        for (JInternalFrame internalFrame : allFrames) {
            if (internalFrame.getTitle().equals(title)) {
                try {
                    internalFrame.setSelected(true);
                    return true;
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(JFrameMain.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }

        }

        return false;
    }

    private static void criaBotaoLink(final String title) {
        //** Cria um novo botão que será adicionado ao toolBar
        JButton btnToolBar = new JButton();
        //** Seta propriedades do Botão
        btnToolBar.setText("<html><body>" + title + "</body></html>");
        btnToolBar.setFont(new Font(Util.NM_DEFAULT_FONT, 0, 10));
        btnToolBar.setForeground(Color.BLACK);
        btnToolBar.setName(title);
        btnToolBar.setIcon(Imagens.IMG_WINDOW);
        btnToolBar.setBorderPainted(false);
        btnToolBar.setFocusPainted(false);
        btnToolBar.setContentAreaFilled(false);
        btnToolBar.setMargin(new Insets(2, 2, 2, 2));
        btnToolBar.setHorizontalAlignment(JLabel.LEFT);
        btnToolBar.setLayout(new BorderLayout());
        btnToolBar.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent evt) {
                JButton btnSource = ((JButton) evt.getSource());
                btnSource.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseClicked(MouseEvent ev) {
                buscaInternalFrame(title);
            }
        });
        //** Pega as dimensões de btnToolBar
        Dimension dimensaoBotao = btnToolBar.getPreferredSize();
        //** Adiciona btnClose ao btnToolBar
//        btnToolBar.add(internalFrame.getButtonClose(), BorderLayout.EAST);
        //** Seta as novas dimensões para btnToolBar, após adição de btnclose
        dimensaoBotao.setSize(dimensaoBotao.getWidth() + 24, dimensaoBotao.getHeight());
        btnToolBar.setPreferredSize(dimensaoBotao);

        //** Adiciona o Botão ao ToolBar
        toolBar.add(btnToolBar, toolBar.getComponents().length);
        toolBar.revalidate();

        //** Atualiza o valor total = a soma das dimensões dos botões adicionados no toolBar
        tamanhoButtons = tamanhoButtons + (int) btnToolBar.getPreferredSize().getWidth();
    }

    private static int tamanhoButtons = 0;

    public static JFrame getFramePrincipal() {
        return (JFrame) getFrames()[0];
    }

    private void acaoSair() {

        if (desktopPane.getAllFrames().length != 0) {
            int resp = JOptionPane.showConfirmDialog(this, "Deseja encerrar a aplicação?", "Sair", JOptionPane.YES_NO_OPTION);

            if (resp == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgenda;
    private javax.swing.JButton btnPaciente;
    private static javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemMenuAgenda;
    private javax.swing.JMenuItem itemMenuPaciente;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuAjud;
    private javax.swing.JMenu menuItemPrograma;
    private javax.swing.JMenu menuItemRelatorios;
    private javax.swing.JMenuItem menuItemSair;
    private javax.swing.JMenu menuItemSistema;
    private javax.swing.JMenuItem menuItemSobre;
    private static javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new JFrameMain().setVisible(true);
    }
}
