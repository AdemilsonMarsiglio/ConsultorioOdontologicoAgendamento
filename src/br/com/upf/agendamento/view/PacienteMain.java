package br.com.upf.agendamento.view;

import br.com.parcerianet.utilcomp.containers.JPScrollPane;
import br.com.upf.agendamento.control.basico.AgendamentoCon;
import br.com.upf.agendamento.control.basico.PacienteCon;
import br.com.upf.agendamento.model.basico.Paciente;
import br.com.upf.agendamento.model.basico.enums.AtivoInativo;
import br.com.upf.agendamento.view.imagens.Imagens;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ademilsom
 */
public class PacienteMain extends JPanel {

    private char acao;
    private Integer idPaciente;

    private JTable table;

    private final String CONSULTA = "1";
    private final String FORMULARIO = "2";

    PacienteCon pacienteCon = new PacienteCon();

    PacienteForm pacienteForm = new PacienteForm();

    JPanel pnlCentralizador = new JPanel(new CardLayout());

    public PacienteMain() {

        this.setLayout(new BorderLayout());

        criaCardPane();
        criaNavigatorBar();

        setVisiblePanel(CONSULTA);
        setEnableBtnNavigation(true);
    }

    private void criaCardPane() {
        JPanel pnlConsulta = new JPanel(new BorderLayout());

        pnlConsulta.add(BorderLayout.CENTER, criaTabela());
        pnlConsulta.add(BorderLayout.NORTH, criaSearchBar());

        pnlCentralizador.add(pnlConsulta, CONSULTA);
        pnlCentralizador.add(pacienteForm, FORMULARIO);

        this.add(BorderLayout.CENTER, pnlCentralizador);
    }

    private JPScrollPane criaTabela() {

        table = new JTable() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);

                if (me.getClickCount() == 2) {
                    alterar();
                }
            }
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        table.setRowHeight(26);
        atualizaTabela();
        return new JPScrollPane(table);
    }

    private void atualizaTabela() {
        if (table.getModel() != null) {
            table.setModel(new DefaultTableModel());
        }

        List<Paciente> listPaciente = pacienteCon.getLista();
        int i = 0;
        Object[][] dados = new Object[listPaciente.size()][4];
        for (Paciente paciente : listPaciente) {
            dados[i][0] = paciente.getIdPaciente();
            dados[i][1] = paciente.getNmPaciente();
            dados[i][2] = paciente.getFone();
            dados[i++][3] = paciente.getCidade();
        }

        table.setModel(new DefaultTableModel(dados, new Object[]{"Código", "Nome", "Fone", "Cidade"}));

        refreshRendererToCells();
    }

    private void acaoFiltro() {
        if (txfCampoBusca.getText().isEmpty()) {
            pacienteCon.setCriterions(null);
            atualizaTabela();
            return;
        }

        if (cbxTipoBusca.getSelectedItem().equals("Código")) {
            pacienteCon.setCriterions(new Object[]{
                new Object[]{"this", Restrictions.eq("idPaciente", Integer.parseInt(txfCampoBusca.getText()))}
            });
        } else if (cbxTipoBusca.getSelectedItem().equals("Nome")) {
            pacienteCon.setCriterions(new Object[]{
                new Object[]{"this", Restrictions.ilike("nmPaciente", txfCampoBusca.getText(), MatchMode.START)}
            });
        } else if (cbxTipoBusca.getSelectedItem().equals("Cidade")) {
            pacienteCon.setCriterions(new Object[]{
                new Object[]{"this", Restrictions.ilike("cidade", txfCampoBusca.getText(), MatchMode.START)}
            });
        }

        atualizaTabela();
    }

    private JComboBox cbxTipoBusca;
    private JTextField txfCampoBusca;

    private JPanel criaSearchBar() {
        JPanel pnlSearch = new JPanel(new GridBagLayout());
        pnlSearch.setPreferredSize(new Dimension(100, 70));

        cbxTipoBusca = new JComboBox(new DefaultComboBoxModel(new Object[]{"Código", "Nome", "Cidade"}));
        cbxTipoBusca.setSelectedIndex(1);
        cbxTipoBusca.setPreferredSize(new Dimension(90, 30));

        txfCampoBusca = new JTextField(45);
        txfCampoBusca.setPreferredSize(new Dimension(70, 30));
        txfCampoBusca.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    acaoFiltro();
                }
            }

        });

        JButton btnPesquisar = new JButton("Pesquisar", Imagens.IMG_FILTROMENOR);
        btnPesquisar.setPreferredSize(new Dimension(110, 30));
        btnPesquisar.setFont(new Font("Arial", Font.BOLD, 12));
        btnPesquisar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                acaoFiltro();
            }
        });
        GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlSearch.add(cbxTipoBusca, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlSearch.add(txfCampoBusca, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlSearch.add(btnPesquisar, gridBagConstraints);

        return pnlSearch;
    }

    private final JButton btnIncluir = new JButton(Imagens.IMG_ADD);
    private final JButton btnAlterar = new JButton(Imagens.IMG_EDIT);
    private final JButton btnSalvar = new JButton(Imagens.IMG_SAVE);
    private final JButton btnCancelar = new JButton(Imagens.IMG_CANCEL);
    private final JButton btnDeletar = new JButton(Imagens.IMG_DELETE);

    private void criaNavigatorBar() {
        JPanel pnlNavigation = new JPanel(new GridBagLayout());

        btnIncluir.setToolTipText("Incluir");
        btnAlterar.setToolTipText("Alterar");
        btnSalvar.setToolTipText("Salvar");
        btnCancelar.setToolTipText("Cancelar");
        btnDeletar.setToolTipText("Excluir");

        btnIncluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                incluir();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                cancelar();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                alterar();
            }
        });

        btnSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                salvar();
            }
        });
        btnDeletar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                deletar();
            }
        });

        GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
//        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlNavigation.add(btnIncluir, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
//        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        pnlNavigation.add(btnAlterar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
//        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        pnlNavigation.add(btnDeletar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
//        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        pnlNavigation.add(btnSalvar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        pnlNavigation.add(btnCancelar, gridBagConstraints);

        this.add(BorderLayout.SOUTH, pnlNavigation);
    }

    private void refreshRendererToCells() {

        Dimension sizeArea = new Dimension(800, 600);
        //** Alinhamento de coluna a direita
        DefaultTableCellRenderer alignDireita = new DefaultTableCellRenderer();
        alignDireita.setHorizontalAlignment(JLabel.RIGHT);

        DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
        alignCenter.setHorizontalAlignment(JLabel.CENTER);

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setPreferredSize(new Dimension(100, 25));

        int column = 0;
        TableColumn colUm = table.getColumnModel().getColumn(column++);
        colUm.setPreferredWidth((15 * sizeArea.width) / 100);
        colUm.setCellRenderer(alignDireita);

        TableColumn colDois = table.getColumnModel().getColumn(column++);
        colDois.setPreferredWidth((40 * sizeArea.width) / 100);

        TableColumn colTres = table.getColumnModel().getColumn(column++);
        colTres.setPreferredWidth((20 * sizeArea.width) / 100);
        colTres.setCellRenderer(alignCenter);

        TableColumn colQuatro = table.getColumnModel().getColumn(column++);
        colQuatro.setPreferredWidth((25 * sizeArea.width) / 100);
    }

    private void incluir() {
        acao = 'I';

        setEnableBtnNavigation(false);

        setVisiblePanel(FORMULARIO);
        populaDadosFormulario("", null, AtivoInativo.A, "", "", "", "", "", "", "");
    }

    private void alterar() {
        acao = 'A';

        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum item Selecionado");
            return;
        }

        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "É necessário ter itens na consulta.");
            return;
        }

        setEnableBtnNavigation(false);

        idPaciente = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

        PacienteCon pacienteConAlterar = new PacienteCon();
        pacienteConAlterar.setCriterions(new Object[]{
            new Object[]{"this", Restrictions.eq("idPaciente", idPaciente)}
        });

        Paciente pacienteAlterar = pacienteConAlterar.getLista().get(0);

        populaDadosFormulario(pacienteAlterar.getNmPaciente(), pacienteAlterar.getDtNascimento(), pacienteAlterar.getStCliente(), pacienteAlterar.getFone(),
                pacienteAlterar.getCpf(), pacienteAlterar.getDsLogradouro(), pacienteAlterar.getNrEndereco(), pacienteAlterar.getDsBairro(),
                pacienteAlterar.getCep(), pacienteAlterar.getCidade());

        setVisiblePanel(FORMULARIO);

    }

    private void salvar() {

        if (!incluirAlterar()) {
            return;
        }

        setEnableBtnNavigation(true);
        setVisiblePanel(CONSULTA);
        atualizaTabela();

    }

    private void deletar() {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum item Selecionado");
            return;
        }

        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "É necessário ter itens na consulta.");
            return;
        }

        int resp = JOptionPane.showConfirmDialog(this, "Confirma Exclusão do paciente?", "Excluir?", JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            idPaciente = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

            AgendamentoCon agendamentoCon = new AgendamentoCon();
            agendamentoCon.setCriterions(new Object[]{
                new Object[]{"paciente", Restrictions.eq("idPaciente", idPaciente)}
            });

            if (!agendamentoCon.getLista().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não é possivel excluir, pois o mesmo esta sendo referenciado na tabela de agendamento.", "Exclusão cancelada", 2);
                return;
            }

            pacienteCon.localizar(idPaciente);
            pacienteCon.excluir();

            atualizaTabela();
        }
    }

    private void cancelar() {
        setEnableBtnNavigation(true);
        setVisiblePanel(CONSULTA);
    }

    private void setVisiblePanel(String panel) {
        CardLayout card = (CardLayout) pnlCentralizador.getLayout();
        card.show(pnlCentralizador, panel);

    }

    private void populaDadosFormulario(String nome, Date dtDtNacimento, AtivoInativo situacao, String fone, String CPF,
            String logradouro, String numero, String bairro, String CEP, String cidade) {

        pacienteForm.getTxfNome().setText(nome);
        pacienteForm.getJdcNascimento().setDate(dtDtNacimento);
        pacienteForm.getCbxSituacao().setSelectedItem(situacao);
        pacienteForm.getTxfFone().setText(fone);
        pacienteForm.getTxfCPF().setText(CPF);

        pacienteForm.getTxfLogradouro().setText(logradouro);
        pacienteForm.getTxfNumero().setText(numero);
        pacienteForm.getTxfBairro().setText(bairro);
        pacienteForm.getTxfCEP().setText(CEP);
        pacienteForm.getTxfCidade().setText(cidade);
    }

    private boolean incluirAlterar() {
        if (pacienteForm.getTxfNome().getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o nome do paciente.");
            return false;
        }

        if (acao == 'A') {

            pacienteCon.localizar(idPaciente);

            pacienteCon.alterar();

        } else if (acao == 'I') {

            PacienteCon verificaNmPacienteCon = new PacienteCon();
            verificaNmPacienteCon.setCriterions(new Object[]{
                new Object[]{"this", Restrictions.eq("nmPaciente", pacienteForm.getTxfNome().getText())}
            });

            if (!verificaNmPacienteCon.getLista().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Já existe um paciente com este nome.");
                return false;
            }

            pacienteCon.incluir();

        }

        pacienteCon.getObjeto().setNmPaciente(pacienteForm.getTxfNome().getText());
        pacienteCon.getObjeto().setDtNascimento(pacienteForm.getJdcNascimento().getDate());
        pacienteCon.getObjeto().setStCliente((AtivoInativo) pacienteForm.getCbxSituacao().getSelectedItem());
        pacienteCon.getObjeto().setFone(pacienteForm.getTxfFone().getText());
        pacienteCon.getObjeto().setCpf(pacienteForm.getTxfCPF().getText());

        pacienteCon.getObjeto().setDsLogradouro(pacienteForm.getTxfLogradouro().getText());
        pacienteCon.getObjeto().setNrEndereco(pacienteForm.getTxfNumero().getText());
        pacienteCon.getObjeto().setDsBairro(pacienteForm.getTxfBairro().getText());
        pacienteCon.getObjeto().setCep(pacienteForm.getTxfCEP().getText());
        pacienteCon.getObjeto().setCidade(pacienteForm.getTxfCidade().getText());

        pacienteCon.gravar();

        return true;
    }

    private void setEnableBtnNavigation(boolean enable) {
        btnIncluir.setEnabled(enable);
        btnAlterar.setEnabled(enable);
        btnDeletar.setEnabled(enable);
        btnCancelar.setEnabled(!enable);
        btnSalvar.setEnabled(!enable);
    }

}
