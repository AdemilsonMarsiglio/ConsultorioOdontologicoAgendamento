package br.com.upf.agendamento.view;

import br.com.upf.agendamento.control.basico.AgendamentoCon;
import br.com.upf.agendamento.main.JFrameMain;
import br.com.upf.agendamento.model.basico.Agendamento;
import br.com.upf.agendamento.view.imagens.Imagens;
import br.com.upf.agendamento.view.util.relatorios.JViwer;
import br.com.upf.agendamento.view.util.relatorios.VisualizarRelJasper;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.jdesktop.swingx.JXCollapsiblePane;

/**
 *
 * @author Ademilson
 */
public class ConsultaMain extends javax.swing.JPanel {
    
    private JXCollapsiblePane collapsiblePane;
    private VisualizarRelJasper visualizarRelJasper;
    
    private AgendamentoCon agendamentoCon = new AgendamentoCon();
    
    public ConsultaMain() {
        initComponents();
        
        agendamentoCon.setJoin(new Object[]{
            new Object[]{"paciente", JoinType.LEFT_OUTER_JOIN}
        });
        
        collapsiblePane = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);
        collapsiblePane.setLayout(new BorderLayout());
        collapsiblePane.add(pnlFiltro);
        
        btnFiltrar.setIcon(Imagens.IMG_FILTROMENOR);
        btnLimpar.setIcon(Imagens.IMG_LIMPARMENOR);
        
        pnlColapse.add(BorderLayout.CENTER, collapsiblePane);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlFiltro = new javax.swing.JPanel();
        pnlBotoes = new javax.swing.JPanel();
        btnFiltrar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        pnlCamposFiltro = new javax.swing.JPanel();
        lblData = new javax.swing.JLabel();
        lblPaciente = new javax.swing.JLabel();
        jdcData1 = new org.jdesktop.swingx.JXDatePicker();
        jdcData2 = new org.jdesktop.swingx.JXDatePicker();
        txfPAciente = new javax.swing.JTextField();
        lblEntre = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        pnlColapse = new javax.swing.JPanel();
        btnCollapse = new javax.swing.JButton();
        pnlRelatorioa = new javax.swing.JPanel();

        pnlFiltro.setLayout(new java.awt.BorderLayout());

        pnlBotoes.setLayout(new java.awt.GridBagLayout());

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        pnlBotoes.add(btnFiltrar, new java.awt.GridBagConstraints());

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        pnlBotoes.add(btnLimpar, new java.awt.GridBagConstraints());

        pnlFiltro.add(pnlBotoes, java.awt.BorderLayout.SOUTH);

        pnlCamposFiltro.setLayout(new java.awt.GridBagLayout());

        lblData.setText("Data");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 1, 0);
        pnlCamposFiltro.add(lblData, gridBagConstraints);

        lblPaciente.setText("Paciente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 1, 0);
        pnlCamposFiltro.add(lblPaciente, gridBagConstraints);

        jdcData1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jdcData1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcData1PropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 1, 0);
        pnlCamposFiltro.add(jdcData1, gridBagConstraints);

        jdcData2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 1, 15);
        pnlCamposFiltro.add(jdcData2, gridBagConstraints);

        txfPAciente.setColumns(30);
        txfPAciente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 1, 15);
        pnlCamposFiltro.add(txfPAciente, gridBagConstraints);

        lblEntre.setText("à");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        pnlCamposFiltro.add(lblEntre, gridBagConstraints);

        pnlFiltro.add(pnlCamposFiltro, java.awt.BorderLayout.CENTER);

        setLayout(new java.awt.BorderLayout());

        pnlCenter.setLayout(new java.awt.BorderLayout());

        pnlColapse.setLayout(new java.awt.BorderLayout());

        btnCollapse.setIcon(Imagens.IMG_SETA_ESQUERDA);
        btnCollapse.setFocusable(false);
        btnCollapse.setPreferredSize(new java.awt.Dimension(18, 23));
        btnCollapse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCollapseActionPerformed(evt);
            }
        });
        pnlColapse.add(btnCollapse, java.awt.BorderLayout.EAST);

        pnlCenter.add(pnlColapse, java.awt.BorderLayout.WEST);

        pnlRelatorioa.setLayout(new java.awt.BorderLayout());
        pnlCenter.add(pnlRelatorioa, java.awt.BorderLayout.CENTER);

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        montaCriterios();
        
        List<Agendamento> listAgendamento = agendamentoCon.getLista();
        if(!listAgendamento.isEmpty()){
            visualizarRelJasper = new VisualizarRelJasper(listAgendamento, getClass(), null, "AgendamentoMain", false, agendamentoCon.getResourceBundle(), "Consultorio Odontológico", "Controle de Agendamento", "Desenvolvedor", "Sistema de Agendamento de Pacientes", "Passo Fundo", Imagens.IMG_DIARY.getImage(), "Relatório de Agendamentos");
            JViwer reportViewer = new JViwer(visualizarRelJasper.getJasperPrint());
            reportViewer.setAjusteLargura(true);

            if(pnlRelatorioa.getComponents() != null){
                pnlRelatorioa.removeAll();
            }

            pnlRelatorioa.add(reportViewer);
            collapsiblePane.setCollapsed(true);
        } else {
            JOptionPane.showInternalMessageDialog(JFrameMain.getSelectedWindow(), "Nenhum agendamento encontrado nesta data.", "Aviso", 2);
        }
        
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnCollapseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCollapseActionPerformed
        if(collapsiblePane.isCollapsed()){
            collapsiblePane.setCollapsed(false);
            btnCollapse.setIcon(Imagens.IMG_SETA_ESQUERDA);
        } else {
            collapsiblePane.setCollapsed(true);
            btnCollapse.setIcon(Imagens.IMG_SETA_DIREITA);
        }
    }//GEN-LAST:event_btnCollapseActionPerformed

    private void jdcData1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcData1PropertyChange
        jdcData2.setDate(jdcData1.getDate());
    }//GEN-LAST:event_jdcData1PropertyChange

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        jdcData2.setDate(null);
        jdcData1.setDate(null);
        txfPAciente.setText(null);
    }//GEN-LAST:event_btnLimparActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCollapse;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpar;
    private org.jdesktop.swingx.JXDatePicker jdcData1;
    private org.jdesktop.swingx.JXDatePicker jdcData2;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblEntre;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlCamposFiltro;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlColapse;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlRelatorioa;
    private javax.swing.JTextField txfPAciente;
    // End of variables declaration//GEN-END:variables

    private void montaCriterios(){
        
        ArrayList criterios = new ArrayList();
        
        if(jdcData1.getDate() != null){
            
            if(jdcData2 == null){
                jdcData2.setDate(jdcData1.getDate());
            }
            
            criterios.add(new Object[]{"this", Restrictions.between("dtAgendamento", jdcData1.getDate(), jdcData2.getDate())});
        }
        
        if(!txfPAciente.getText().isEmpty()){
            criterios.add(new Object[]{"paciente", Restrictions.ilike("nmPaciente", txfPAciente.getText(), MatchMode.ANYWHERE)});
        }
        
        agendamentoCon.setCriterions(criterios.toArray());
    }
}
