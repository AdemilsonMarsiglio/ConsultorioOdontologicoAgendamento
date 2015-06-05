package br.com.upf.agendamento.view;

import br.com.parcerianet.utilcomp.components.formatacao.JFormattedCepField;
import br.com.parcerianet.utilcomp.components.formatacao.JFormattedCpfCnpjField;
import br.com.parcerianet.utilcomp.components.formatacao.LimitaTextField;
import br.com.upf.agendamento.model.basico.enums.AtivoInativoPendende;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Ademilsom
 */
public class PacienteForm extends javax.swing.JPanel {

    /**
     * Creates new form PacienteForm
     */
    public PacienteForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tabbedPane = new javax.swing.JTabbedPane();
        pnlDadosCliente = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txfNome = new javax.swing.JTextField();
        lblNascimento = new javax.swing.JLabel();
        jdcNascimento = new com.toedter.calendar.JDateChooser();
        lblCPF = new javax.swing.JLabel();
        lblFone = new javax.swing.JLabel();
        txfFone = new javax.swing.JFormattedTextField();
        lblCbxStPaciente = new javax.swing.JLabel();
        cbxSituacao = new javax.swing.JComboBox();
        txfCPF = new br.com.parcerianet.utilcomp.components.formatacao.JFormattedCpfCnpjField();
        pnlEndereco = new javax.swing.JPanel();
        lblDsLogradouro = new javax.swing.JLabel();
        txfLogradouro = new javax.swing.JTextField();
        lblnrEndereco = new javax.swing.JLabel();
        lblCEP = new javax.swing.JLabel();
        txfBairro = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        lblCidade = new javax.swing.JLabel();
        txfCidade = new javax.swing.JTextField();
        txfCEP = new br.com.parcerianet.utilcomp.components.formatacao.JFormattedCepField();
        txfNumero = new br.com.parcerianet.utilcomp.components.formatacao.JFormattedNumberField();

        setLayout(new java.awt.BorderLayout());

        pnlDadosCliente.setLayout(new java.awt.GridBagLayout());

        lblNome.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlDadosCliente.add(lblNome, gridBagConstraints);

        txfNome.setColumns(20);
        txfNome.setDocument(new LimitaTextField(30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlDadosCliente.add(txfNome, gridBagConstraints);

        lblNascimento.setText("Data Nascimento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlDadosCliente.add(lblNascimento, gridBagConstraints);

        jdcNascimento.setPreferredSize(new java.awt.Dimension(120, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlDadosCliente.add(jdcNascimento, gridBagConstraints);

        lblCPF.setText("CPF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 0);
        pnlDadosCliente.add(lblCPF, gridBagConstraints);

        lblFone.setText("Fone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlDadosCliente.add(lblFone, gridBagConstraints);

        txfFone.setColumns(8);
        txfFone.setDocument(new LimitaTextField(15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlDadosCliente.add(txfFone, gridBagConstraints);

        lblCbxStPaciente.setText("Situação");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 0);
        pnlDadosCliente.add(lblCbxStPaciente, gridBagConstraints);

        cbxSituacao.setModel(new DefaultComboBoxModel(AtivoInativoPendende.values()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlDadosCliente.add(cbxSituacao, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlDadosCliente.add(txfCPF, gridBagConstraints);

        tabbedPane.addTab("Dados Cadastrais", pnlDadosCliente);

        pnlEndereco.setLayout(new java.awt.GridBagLayout());

        lblDsLogradouro.setText("Logradouro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlEndereco.add(lblDsLogradouro, gridBagConstraints);

        txfLogradouro.setColumns(15);
        txfLogradouro.setDocument(new LimitaTextField(100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 165;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlEndereco.add(txfLogradouro, gridBagConstraints);

        lblnrEndereco.setText("Número");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 0);
        pnlEndereco.add(lblnrEndereco, gridBagConstraints);

        lblCEP.setText("CEP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 0);
        pnlEndereco.add(lblCEP, gridBagConstraints);

        txfBairro.setColumns(15);
        txfBairro.setDocument(new LimitaTextField(50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 165;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlEndereco.add(txfBairro, gridBagConstraints);

        lblBairro.setText("Bairro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlEndereco.add(lblBairro, gridBagConstraints);

        lblCidade.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlEndereco.add(lblCidade, gridBagConstraints);

        txfCidade.setColumns(15);
        txfCidade.setDocument(new LimitaTextField(30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 165;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlEndereco.add(txfCidade, gridBagConstraints);

        txfCEP.setColumns(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlEndereco.add(txfCEP, gridBagConstraints);

        txfNumero.setColumns(8);
        txfNumero.setDocument(new LimitaTextField(10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlEndereco.add(txfNumero, gridBagConstraints);

        tabbedPane.addTab("Endereço", pnlEndereco);

        add(tabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxSituacao;
    private com.toedter.calendar.JDateChooser jdcNascimento;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCEP;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCbxStPaciente;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblDsLogradouro;
    private javax.swing.JLabel lblFone;
    private javax.swing.JLabel lblNascimento;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblnrEndereco;
    private javax.swing.JPanel pnlDadosCliente;
    private javax.swing.JPanel pnlEndereco;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField txfBairro;
    private br.com.parcerianet.utilcomp.components.formatacao.JFormattedCepField txfCEP;
    private br.com.parcerianet.utilcomp.components.formatacao.JFormattedCpfCnpjField txfCPF;
    private javax.swing.JTextField txfCidade;
    private javax.swing.JFormattedTextField txfFone;
    private javax.swing.JTextField txfLogradouro;
    private javax.swing.JTextField txfNome;
    private br.com.parcerianet.utilcomp.components.formatacao.JFormattedNumberField txfNumero;
    // End of variables declaration//GEN-END:variables

    public JComboBox getCbxSituacao() {
        return cbxSituacao;
    }

    public JDateChooser getJdcNascimento() {
        return jdcNascimento;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JTextField getTxfBairro() {
        return txfBairro;
    }

    public JFormattedCepField getTxfCEP() {
        return txfCEP;
    }

    public JFormattedCpfCnpjField getTxfCPF() {
        return txfCPF;
    }

    public JTextField getTxfCidade() {
        return txfCidade;
    }

    public JFormattedTextField getTxfFone() {
        return txfFone;
    }

    public JTextField getTxfNome() {
        return txfNome;
    }

    public JTextField getTxfNumero() {
        return txfNumero;
    }

    public JTextField getTxfLogradouro() {
        return txfLogradouro;
    }
}
