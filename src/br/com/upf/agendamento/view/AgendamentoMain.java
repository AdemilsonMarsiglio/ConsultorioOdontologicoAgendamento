package br.com.upf.agendamento.view;

import br.com.parcerianet.util.ParseDate;
import br.com.parcerianet.utilcomp.containers.JPScrollPane;
import br.com.upf.agendamento.control.basico.AgendamentoCon;
import br.com.upf.agendamento.model.basico.Agendamento;
import br.com.upf.agendamento.model.basico.Paciente;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Ademilsom
 */
public class AgendamentoMain extends JPanel {

    private char acao;
    private Integer idAgedamento;

    private JTable table;

    private final String CONSULTA = "1";
    private final String FORMULARIO = "2";

    private AgendamentoCon agendamentoCon = new AgendamentoCon();
    private AgendamentoForm agendamentoForm = new AgendamentoForm();

    JPanel pnlCentralizador = new JPanel(new CardLayout());

    public AgendamentoMain() {

        this.setLayout(new BorderLayout());
        
        agendamentoCon.setJoin(new Object[]{
            new Object[]{"paciente", JoinType.LEFT_OUTER_JOIN}
        });

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
        pnlCentralizador.add(agendamentoForm, FORMULARIO);

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
                
                if(me.getClickCount() == 2){
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

        List<Agendamento> listAgendamento = agendamentoCon.getLista();
        int i = 0;
        Object[][] dados = new Object[listAgendamento.size()][5];
        for (Agendamento agendamento : listAgendamento) {
            dados[i][0] = agendamento.getIdAgendamento();
            dados[i][1] = agendamento.getPaciente().getNmPaciente();
            dados[i][2] = agendamento.getDtAgendamentoTable();
            dados[i][3] = agendamento.getHrInicio();
            dados[i++][4] = agendamento.getHrFim();
        }

        table.setModel(new DefaultTableModel(dados, new Object[]{"Código", "Nome", "Data Agendamento", "Início", "Fim"}));
        
        refreshRendererToCells();
    }

    private void acaoFiltro() {
        if (txfCampoBusca.getText().isEmpty()) {
            agendamentoCon.setCriterions(null);
            atualizaTabela();
            return;
        }

        if (cbxTipoBusca.getSelectedItem().equals("Código")) {
            agendamentoCon.setCriterions(new Object[]{
                new Object[]{"this", Restrictions.eq("idAgendamento", Integer.parseInt(txfCampoBusca.getText()))}
            });
        } else if (cbxTipoBusca.getSelectedItem().equals("Nome")) {
            agendamentoCon.setCriterions(new Object[]{
                new Object[]{"paciente", Restrictions.ilike("nmPaciente", txfCampoBusca.getText(), MatchMode.ANYWHERE)}
            });
        }

        atualizaTabela();
    }
    
    private JComboBox cbxTipoBusca;
    private JTextField txfCampoBusca;
    
    private JPanel criaSearchBar() {
        JPanel pnlSearch = new JPanel(new GridBagLayout());
        pnlSearch.setPreferredSize(new Dimension(100, 70));
        
        cbxTipoBusca = new JComboBox(new DefaultComboBoxModel(new Object[]{"Código","Nome"}));
        cbxTipoBusca.setSelectedIndex(1);
        cbxTipoBusca.setPreferredSize(new Dimension(90, 30));
        
        txfCampoBusca = new JTextField(45);
        txfCampoBusca.setPreferredSize(new Dimension(70, 30));
        txfCampoBusca.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
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
        
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        btnIncluir.setToolTipText("Incluir");
        btnAlterar.setToolTipText("Alterar");
        btnSalvar.setToolTipText("Salvar");
        btnCancelar.setToolTipText("Cancelar");
        btnDeletar.setToolTipText("Excluir");

        btnIncluir.setBorderPainted(false);
        btnAlterar.setBorderPainted(false);
        btnSalvar.setBorderPainted(false);
        btnCancelar.setBorderPainted(false);
        btnDeletar.setBorderPainted(false);
        
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

        toolBar.add(btnIncluir);
        toolBar.add(btnAlterar);
        toolBar.add(btnDeletar);
        toolBar.add(btnSalvar);
        toolBar.add(btnCancelar);

        this.add(BorderLayout.SOUTH, toolBar);
    }
    
    private void refreshRendererToCells() {
        
        Dimension sizeArea = new Dimension(800, 600);
        //** Alinhamento de coluna a direita
        DefaultTableCellRenderer alignDireita = new DefaultTableCellRenderer();
        alignDireita.setHorizontalAlignment(JLabel.RIGHT);

        DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
        alignCenter.setHorizontalAlignment(JLabel.CENTER);
        
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setPreferredSize(new Dimension(100, 25));
        
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
        colQuatro.setCellRenderer(alignCenter);
        
        TableColumn colCinco = table.getColumnModel().getColumn(column++);
        colCinco.setPreferredWidth((25 * sizeArea.width) / 100);
        colCinco.setCellRenderer(alignCenter);
    }

    private void incluir() {
        acao = 'I';

        setEnableBtnNavigation(false);

        setVisiblePanel(FORMULARIO);
        
        Date dtAtual = new Date();
        
        String HrAtual = new ParseDate().formatData(dtAtual, "HH:mm");
        Integer hora = Integer.parseInt(new ParseDate().formatData(dtAtual, "HH")) + 1;
        String horaFormatada = hora.toString();
        String minuto = new ParseDate().formatData(dtAtual, "mm");
        if(hora.toString().length() < 2){
            horaFormatada = "0" + hora;
        }
        if(minuto.length() < 2){
            minuto = "0" + minuto;
        }
        
        String hrAproximada = horaFormatada + ":" + minuto;
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dtAtual);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        dtAtual = calendar.getTime();
        
        populaDadosFormulario(null, dtAtual, HrAtual, hrAproximada, "");
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

        idAgedamento = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

        AgendamentoCon agendamentoConAlterar = new AgendamentoCon();
        agendamentoConAlterar.setJoin(agendamentoCon.getJoin());
        agendamentoConAlterar.setCriterions(new Object[]{
            new Object[]{"this", Restrictions.eq("idAgendamento", idAgedamento)}
        });

        Agendamento agendamento = agendamentoConAlterar.getLista().get(0);

        populaDadosFormulario(agendamento.getPaciente(), agendamento.getDtAgendamento(), agendamento.getHrInicio(), agendamento.getHrFim(), agendamento.getObsAgendamento());

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
    
    private void deletar(){
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum item Selecionado");
            return;
        }

        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "É necessário ter itens na consulta.");
            return;
        }

        int resp = JOptionPane.showConfirmDialog(this, "Confirma Exclusão do paciente?", "Excluir?", JOptionPane.YES_NO_OPTION);
        
        if(resp == JOptionPane.YES_OPTION){
            idAgedamento = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

            agendamentoCon.localizar(idAgedamento);
            agendamentoCon.excluir();

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

    private void populaDadosFormulario(Paciente paciente, Date dtInicio, String hrInicio, String hrFim, String obs) {
        agendamentoForm.getCbxPaciente().setSelectedItem(paciente);
        agendamentoForm.getJdcDtInicio().setDate(dtInicio);
        agendamentoForm.setHrInicio(hrInicio);
        agendamentoForm.setHrFim(hrFim);
        agendamentoForm.getTxaObservacao().setText(obs);
    }

    private boolean incluirAlterar() {
        
        if(agendamentoForm.getCbxPaciente().getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "O paciente é obrigatório.", "Informe os dados Obrigatórios", 2);
            
            return false;
        }
        
        if(agendamentoForm.getJdcDtInicio().getDate() == null){
            JOptionPane.showMessageDialog(this, "A data de atendimento é obrigatório.", "Informe os dados Obrigatórios", 2);
            
            return false;
        }
        
        String hrInicio = agendamentoForm.getHrInicio();
        String hrFim = agendamentoForm.getHrFim();
        Date dtAgendamento = agendamentoForm.getJdcDtInicio().getDate();
        
        // verifica se a hora está certa
        if(hrInicio.isEmpty()){
            JOptionPane.showMessageDialog(this, "Hora de Inicio Inválida.", "Erro", 2);
            return false;
        }
        
        if(hrFim.isEmpty()){
            JOptionPane.showMessageDialog(this, "Hora de Fim Inválida.", "Erro", 2);
            return false;
        }
        
        if(!verificaDataAnterior(dtAgendamento, hrInicio)){
            return false;
        }
        
        if(!verificaHorarioAgendamento(dtAgendamento, hrInicio.substring(0, 2))){
            
            int resp = JOptionPane.showConfirmDialog(this, "Já existe pacientes agendado nesta hora.\nDeseja agendar mesmo assim?", "Confirma Agendamento", JOptionPane.YES_NO_OPTION);
            
            if(resp == JOptionPane.NO_OPTION){
                return false;
            }
        }
        
        if (acao == 'A') {
            agendamentoCon.localizar(idAgedamento);

            agendamentoCon.alterar();
        } else if(acao == 'I') {
            agendamentoCon.incluir();
        }
        
        
        agendamentoCon.getObjeto().setPaciente((Paciente) agendamentoForm.getCbxPaciente().getSelectedItem());
        agendamentoCon.getObjeto().setDtAgendamento(dtAgendamento);
        agendamentoCon.getObjeto().setHrInicio(hrInicio);
        agendamentoCon.getObjeto().setHrFim(hrFim);
        agendamentoCon.getObjeto().setObsAgendamento(agendamentoForm.getTxaObservacao().getText());
        
        agendamentoCon.gravar();

        return true;
    }
    
    /**
     * 
     * @param dtAgendamento
     * @param hrInicio
     * @return false se já existe agendamento para este horário.
     */
    private boolean verificaHorarioAgendamento(Date dtAgendamento, String hrInicio){
       AgendamentoCon controlerVerificaAgendamento = new AgendamentoCon();
       controlerVerificaAgendamento.setJoin(agendamentoCon.getJoin());
       
       controlerVerificaAgendamento.setCriterions(new Object[]{
           new Object[]{"this", Restrictions.eq("dtAgendamento", dtAgendamento)},
           new Object[]{"this", Restrictions.ilike("hrInicio", hrInicio, MatchMode.START)},
       });
       
       if(controlerVerificaAgendamento.getLista().isEmpty()){
           return true;
       }
       
       return false;
    }
    
    private boolean verificaDataAnterior(Date dtAgendamento, String hrInicio){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dtAgendamento);
        calendar.set(Calendar.HOUR, Integer.parseInt(hrInicio.substring(0, 2)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(hrInicio.substring(3, 5)));
        
        Date dtAgendamento2 = calendar.getTime();
        
        if(dtAgendamento2.before(new Date())){
           JOptionPane.showMessageDialog(this, "A Data e Hora do agendamento tem que ser maior que a data corrente.");
           return false;
        }
        
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
