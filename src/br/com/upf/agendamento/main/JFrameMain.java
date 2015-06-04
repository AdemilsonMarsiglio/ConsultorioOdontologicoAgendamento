package br.com.upf.agendamento.main;

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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

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
        
        this.add(BorderLayout.SOUTH,new StatusBar("  SISTEMA DE AGENDAMENTO DE PACIENTES", "VERSÃO 1.0  "));
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

        menuItemPrograma.setText("Programa");

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

    //** Localização do próximo internalFrame
    private  int nextFrameX=0;
    private  int nextFrameY=0;
    private  int frameDistance=0;
    
    
    private void criaInternalFrame(String title, Component component, Dimension size) {
        
        JInternalFrame[] allFrames = desktopPane.getAllFrames();
        
        for (JInternalFrame internalFrame : allFrames) {
            if(internalFrame.getTitle().equals(title)){
                try {
                    internalFrame.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(JFrameMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
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

        
        JInternalFrame internalFrame = new JInternalFrame(title, true, true, true, true);
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
    }
    
    public static JFrame getFramePrincipal(){
        return (JFrame) getFrames()[0];
    }
    
    private void acaoSair(){
        
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
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new JFrameMain().setVisible(true);
    }
}
