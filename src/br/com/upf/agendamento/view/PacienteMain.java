package br.com.upf.agendamento.view;

import br.com.parcerianet.generic.modelo.util.controls.OrderBy;
import br.com.upf.agendamento.view.util.NavigatorBar;
import br.com.parcerianet.utilcomp.containers.JPScrollPane;
import br.com.upf.agendamento.control.basico.AgendamentoCon;
import br.com.upf.agendamento.control.basico.PacienteCon;
import br.com.upf.agendamento.model.basico.Paciente;
import br.com.upf.agendamento.model.basico.enums.AtivoInativoPendende;
import br.com.upf.agendamento.view.imagens.Imagens;
import br.com.upf.agendamento.view.util.relatorios.VisualizarRelJasper;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
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
import org.hibernate.criterion.Order;
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

    private PacienteCon pacienteCon = new PacienteCon();

    private PacienteForm pacienteForm = new PacienteForm();

    private NavigatorBar navigatorBar;
    JPanel pnlCentralizador = new JPanel(new CardLayout());

    public PacienteMain() {

        this.setLayout(new BorderLayout());

        criaCardPane();
        criaNavigatorBar();

        setVisiblePanel(CONSULTA);
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
                    if (alterar()) {
                        navigatorBar.setEnableBtnNavigation(false);
                    }
                }
            }
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.setAutoCreateRowSorter(true);

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
        Object[][] dados = new Object[listPaciente.size()][5];
        
        for (Paciente paciente : listPaciente) {
            dados[i][0] = paciente.getIdPaciente();
            dados[i][1] = paciente.getNmPaciente();
            dados[i][2] = paciente.getFone();
            dados[i][3] = paciente.getCidade();
            dados[i++][4] = paciente.getStCliente();
        }
        
        table.setModel(new DefaultTableModel(dados, new Object[]{"Código", "Nome", "Fone", "Cidade", "Situação"}));

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
                new Object[]{"this", Restrictions.ilike("nmPaciente", txfCampoBusca.getText(), MatchMode.ANYWHERE)}
            });
        } else if (cbxTipoBusca.getSelectedItem().equals("Cidade")) {
            pacienteCon.setCriterions(new Object[]{
                new Object[]{"this", Restrictions.ilike("cidade", txfCampoBusca.getText(), MatchMode.ANYWHERE)}
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

    private void criaNavigatorBar() {
        navigatorBar = new NavigatorBar() {

            @Override
            public boolean incluir_() {
                return incluir();
            }

            @Override
            public boolean alterar_() {
                return alterar();
            }

            @Override
            public boolean salvar_() {
                return salvar();
            }

            @Override
            public void deletar_() {
                deletar();
            }

            @Override
            public void cancelar_() {
                cancelar();
            }

            @Override
            public void imprimir_() {
                imprimir();
            }
        };

        this.add(BorderLayout.SOUTH, navigatorBar);
    }

    private void refreshRendererToCells() {

        Dimension sizeArea = new Dimension(800, 600);
        //** Alinhamento de coluna a direita
        DefaultTableCellRenderer alignDireita = new DefaultTableCellRenderer();
        alignDireita.setHorizontalAlignment(JLabel.RIGHT);

        DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
        alignCenter.setHorizontalAlignment(JLabel.CENTER);
        
        //** Alinhamento colorido
        TableCellRendererColor color = new TableCellRendererColor(JLabel.CENTER);

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setPreferredSize(new Dimension(100, 25));

        int column = 0;
        TableColumn colUm = table.getColumnModel().getColumn(column++);
        colUm.setPreferredWidth((10 * sizeArea.width) / 100);
        colUm.setCellRenderer(alignDireita);

        TableColumn colDois = table.getColumnModel().getColumn(column++);
        colDois.setPreferredWidth((40 * sizeArea.width) / 100);

        TableColumn colTres = table.getColumnModel().getColumn(column++);
        colTres.setPreferredWidth((15 * sizeArea.width) / 100);
        colTres.setCellRenderer(alignCenter);

        TableColumn colQuatro = table.getColumnModel().getColumn(column++);
        colQuatro.setPreferredWidth((25 * sizeArea.width) / 100);
        
        TableColumn colCinco = table.getColumnModel().getColumn(column++);
        colCinco.setPreferredWidth((10 * sizeArea.width) / 100);
        colCinco.setCellRenderer(color);
    }
    
    private boolean incluir() {
        acao = 'I';

        setVisiblePanel(FORMULARIO);
        populaDadosFormulario("", null, AtivoInativoPendende.A, "", "", "", "", "", "", "");

        return true;
    }

    private boolean alterar() {
        acao = 'A';

        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum item Selecionado");
            return false;
        }

        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "É necessário ter itens na consulta.");
            return false;
        }

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

        return true;
    }

    private boolean salvar() {

        if (!incluirAlterar()) {
            return false;
        }

        setVisiblePanel(CONSULTA);
        atualizaTabela();

        return true;
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
        setVisiblePanel(CONSULTA);
    }
    
    private void imprimir(){
        pacienteCon.setOrderByClause(new OrderBy[]{
            new OrderBy(Order.asc("cidade")),
            new OrderBy(Order.asc("nmPaciente"))
        });
        
        new VisualizarRelJasper(pacienteCon.getLista(), getClass(), null, "PacienteMain", false, pacienteCon.getResourceBundle(), "Consultorio Odontológico", "Controle de Agendamento", "Desenvolvedor", "Sistema de Agendamento de Pacientes", "Passo Fundo", Imagens.IMG_DIARY.getImage(), "Relatório de Pacientes").action();
    }

    private void setVisiblePanel(String panel) {
        CardLayout card = (CardLayout) pnlCentralizador.getLayout();
        card.show(pnlCentralizador, panel);
    }

    private void populaDadosFormulario(String nome, Date dtDtNacimento, AtivoInativoPendende situacao, String fone, String CPF,
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
        pacienteCon.getObjeto().setStCliente((AtivoInativoPendende) pacienteForm.getCbxSituacao().getSelectedItem());
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

    class TableCellRendererColor extends DefaultTableCellRenderer {

        public TableCellRendererColor(int alinhamento) {
            setHorizontalAlignment(alinhamento);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            //**Pega o componente do super
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Object stPendente = table.getValueAt(row, 4);
            
            if (stPendente != null) {
                if (stPendente.equals(AtivoInativoPendende.A)) {
                    setIcon(Imagens.IMG_ATIVO);
                    setText("");
                    setToolTipText(AtivoInativoPendende.A.toString());
                } else if (stPendente.equals(AtivoInativoPendende.I)) {
                    setIcon(Imagens.IMG_INATIVO);
                    setText("");
                    setToolTipText(AtivoInativoPendende.I.toString());
                } else if (stPendente.equals(AtivoInativoPendende.P)) {
                    setIcon(Imagens.IMG_PENDENTE);
                    setText("");
                    setToolTipText(AtivoInativoPendende.P.toString());
                }
            }
            return c;
        }
    }
}
