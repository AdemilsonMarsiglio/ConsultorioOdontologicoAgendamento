package br.com.upf.agendamento.view.util.relatorios;

import br.com.parcerianet.util.ParseDate;
import br.com.upf.agendamento.main.JFrameMain;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * Responsável pela geração de Relatórios. Faz mapeamento dos dados, exibe barra
 * de progressão e gera tela para visualização do relatório.
 *
 * @author Ademilson
 */
public class VisualizarRelJasper {

    //** ProgressBar que indica o progresso na geração do relatorio
    private ProgressMonitor progressMonitor = null;
    //** Value em porcento que o progress já realizou
    private int progressValue = 0;
    //** Thread para gerar relatorio
    private Thread geraRelatorio = null;

    //**Indica se o relatório deve ser modal ou não
    private boolean modal = false;
    private List lista;
    private Class classeOrigem;
    private ResourceBundle resourceBundle;
    private String[] atributosRel;
    private String nomeRel;
    private Map parametros;

    private String nmEmpresa;
    private String nmFilial;
    private String usuario;
    private String aplicacao;
    private String cidade;
    private String TITULORELATORIO;
    private Image logo;

    private int nrLinhasPreview = 10;
    private URL urlFile;
    private JDialog jDialog = new JDialog();
    private JasperPrint jrPrintAdendo = null;
    //** Logging
    private Logger logger = Logger.getLogger("br.com.parcerianet.view.util.relatorios");

    /**
     * Possibilita informar o nome do relatório que não necessitaria ter o mesmo
     * nome do ViewObject, que é o padrão.
     *
     * @param list Lista de Objetos que serão tratados e visualizados no
     * relatório.
     * @param classeOrigem Classe que originou o relatório. espaço específico.
     * @param atributosRel Atributos que serão listados no relatório.
     * @param nomeRel Nome do arquivo do relatório a ser gerado.
     * @param modal Se <code>true</code> a janela de visualização do relatório
     * @param resourceBundle
     * @param nmEmpresa
     * @param nmFilial
     * @param usuario
     * @param aplicacao
     * @param cidade
     * @param logo ficará modal para a aplicação. O normal é não ser modal.
     */
    public VisualizarRelJasper(List list, Class classeOrigem, String[] atributosRel, String nomeRel, boolean modal,
            ResourceBundle resourceBundle, String nmEmpresa, String nmFilial, String usuario, String aplicacao, String cidade, Image logo, String NOME_RELATORIO) {
        this.lista = list;
        this.classeOrigem = classeOrigem;
        this.atributosRel = atributosRel;
        this.resourceBundle = resourceBundle;
        this.modal = modal;
        this.nmEmpresa = nmEmpresa;
        this.nmFilial = nmFilial;
        this.usuario = usuario;
        this.aplicacao = aplicacao;
        this.cidade = cidade;
        this.logo = logo;
        this.TITULORELATORIO = NOME_RELATORIO;

        setReportName((nomeRel == null) ? classeOrigem.getSimpleName() : nomeRel);
    }

    /**
     * Gera e apresenta o relatório para visualização.
     */
    public void action() {
        //**Thread que fica rodando para ver se o usuário clicou no botão cancelar
        Thread isCanceled = new Thread(new MonitoraGeracao());
        isCanceled.start();

        //** Thread para atualizar o valor do ProgressMonitoe
        Thread atualizaProgress = new Thread(new AtualizaProgress());
        //** Thead responsavel por gerar o relatorio.
        geraRelatorio = new Thread(new GerarRelatorio());
        //** Instancia um novo ProgressMonitor e seta suas propriedades
        progressMonitor = new ProgressMonitor(jDialog, "",
                "Aguarde! Gerando Relatório", 0, 100);

        //** Indica quanto tempo vai demorar para decidir se vai mostrar o popup
        progressMonitor.setMillisToDecideToPopup(-1);
        //** Indica quanto tempo vai demorar para aparecer o popup
        progressMonitor.setMillisToPopup(-1);
        //** Estarta as threads
        atualizaProgress.start();
        geraRelatorio.start();
    }

    /**
     * Permite que seja recuperado um preview do relatório
     *
     * @param nrPaginas número de páginas que devem ser apresentadas no previm
     * @param zoomRatio tamanho da área de visualização(default 0.5f)
     * @return
     */
    /**
     * Permite que seja recuperado um preview do relatório
     *
     * @param nrPaginas número de páginas que devem ser apresentadas no previm
     * @param zoomRatio tamanho da área de visualização(default 0.5f)
     * @return
     */
    public JPanel getPreviewReport() {

        try {
            FormatationDataToJasper dataFormat = null;
            List listaParcial = lista;
            ResourceBundle resourceBundleAux = resourceBundle;

            if (listaParcial.isEmpty()) {
                return null;
            }
            dataFormat = new FormatationDataToJasper(listaParcial.subList(0, (listaParcial.size() < nrLinhasPreview) ? listaParcial.size() : nrLinhasPreview), atributosRel, nomeRel, resourceBundleAux,
                    this.nmEmpresa, this.nmFilial, this.usuario, this.aplicacao, this.cidade, this.logo);

            //**Pegando os parametros
            Map parametrosRel = dataFormat.getParametros();
            parametrosRel.put("Modelo", true);
            if (getParametros() != null) {
                parametrosRel.putAll(getParametros());
            }

            //** Seta os dados que irá conter o meu relatorio
            JRDataSource jrDataSource = dataFormat.getDataSource();
            //** Cria um JasperPrint a partir dos dados do View e de um Objeto JasperReport
            JasperPrint jrPrint = JasperFillManager.fillReport(urlFile.openStream(), parametrosRel, jrDataSource);
            JPViewer view = new JPViewer(jrPrint);
            view.setAjusteLargura(true);

            return view;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Não foi possível gerar o preview", ex);
            return new JPanel();
        }
    }

    public JasperPrint getJasperPrint() {

        try {

            FormatationDataToJasper dataFormat = getFormatationData();

            //**Pegando os parametros
            Map parametrosRel = dataFormat.getParametros();
            if (getParametros() != null) {
                parametrosRel.putAll(getParametros());
            }

            JRDataSource jrDataSource = dataFormat.getDataSource();
            return JasperFillManager.fillReport(urlFile.openStream(), parametrosRel, jrDataSource);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Não foi possível recuperar JasperPrint", ex);
            return null;
        }
    }

    public void addPages(JasperPrint jrPrintAdendo) {
        this.jrPrintAdendo = jrPrintAdendo;
    }

    public void setParametros(Map param) {
        this.parametros = param;
    }

    public Map getParametros() {
        return parametros;
    }

    public String[] getAtributosRel() {
        return atributosRel;
    }

    public void setAtributosRel(String[] atributosRel) {
        this.atributosRel = atributosRel;
    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    public String getReportName() {
        return nomeRel;
    }

    public void setReportName(String nomeRel) {
        this.setReportName(nomeRel, classeOrigem.getResource("relatorios/" + nomeRel + "RelJasper.jasper"));
    }

    public void setReportName(String nomeRel, URL urlFile) {

        this.nomeRel = nomeRel;
        this.urlFile = urlFile;
    }

    public int getNrLinhasPreview() {
        return nrLinhasPreview;
    }

    public void setNrLinhasPreview(int nrLinhasPreview) {
        this.nrLinhasPreview = nrLinhasPreview;
    }

    private FormatationDataToJasper getFormatationData() {
        return new FormatationDataToJasper(lista, atributosRel, nomeRel, resourceBundle, nmEmpresa, nmFilial, usuario, aplicacao, cidade, logo);
    }

    /**
     * Classe de <code>Thread</code> responsável pela atualização da barra de
     * progresso.
     */
    private class AtualizaProgress implements Runnable {

        public void run() {
            //** Verifica se o Progress já chegou ao final ou foi clicado no botão cancelar
            while (progressValue < 100 && !progressMonitor.isCanceled()) {
                //** Redesenha a tela para mostrar o Progresso do ProgressMonitor.
                //** Quando ficava sem isso ficava mostrando a caixa de dialogo em cinza
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //** Seta o valor de Progresso do ProgressMonitor
                        progressMonitor.setProgress(progressValue);
                    }
                });

                try {
                    Thread.sleep(10); //** Adormece thread
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, "InterruptedException", e);
                }
            }
            progressMonitor.close();
        }
    }

    /**
     * Classe de Thread responsável pela geração do relatório
     */
    private class GerarRelatorio implements Runnable {

        public void run() {
            try {
                //** Seta o valor de progresso do ProgressMonitor
                progressValue = 5;
                //** Adormece a trhead por 1 milisegundo
                Thread.sleep(1);

                FormatationDataToJasper dataFormat = getFormatationData();

                progressValue = 25;
                //** Seta os dados que irá conter o meu relatorio
                JRDataSource jrDataSource = dataFormat.getDataSource();
                //** Seta o valor de progresso do ProgressMonitor
                progressValue = 40;
                //** Adormece a trhead por 1 milisegundo
                Thread.sleep(1);
                //**Pegando os parametros
                Map parametros = dataFormat.getParametros();
                if (getParametros() != null) {
                    parametros.putAll(getParametros());
                }

                //** Cria um JasperPrint a partir dos dados do View e de um Objeto JasperReport
                JasperPrint jrPrint = JasperFillManager.fillReport(urlFile.openStream(), parametros, jrDataSource);
                //** Seta o valor de progresso do ProgressMonitor
                progressValue = 85;
                //** Adormece a trhe   ad por 1 milisegundo
                Thread.sleep(1);

                //** Verifica se tem adendos ao relatório
                if (jrPrintAdendo != null) {
                    List<JRPrintPage> paginasAdendo = jrPrintAdendo.getPages();
                    for (JRPrintPage page : paginasAdendo) {
                        jrPrint.addPage(page);
                    }
                }

                //** Seta o valor de progresso do ProgressMonitor
                progressValue = 95;
                //** Adormece a trhe   ad por 1 milisegundo
                Thread.sleep(1);

                //** Cria visualização do relatório
                JPViewer view = new JPViewer(jrPrint);
                //** Ajusta a largura para o tamanho da tela
                view.setAjusteLargura(true);
                //** Abre a tela para visualização: testa antes se tem parametros

                JFrameMain.criaInternalFrame(TITULORELATORIO, view, new Dimension(900, 500));

                //** Muda a mensagem apresentada no ProgressMonitor
                progressMonitor.setNote("Geração Concluída!");
                //** Seta o valor de progresso do ProgressMonitor
                progressValue = 100;
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Interrupted Exception", e);
            } catch (Exception ex) {
                progressMonitor.setNote("<html><body><b>"
                        + "ERRO AO GERAR RELATÓRIO"
                        + "</b></body></html>");
                logger.log(Level.SEVERE, "Erro ao formatar dados para relatório:", ex);
            }
        }
    }

    /**
     * Monitora a geração do relatório verificando um possível cancelamento da
     * tarefa.
     */
    private class MonitoraGeracao implements Runnable {

        public void run() {
            //** Verifica se o usuário clicou no botão cancelar do Progress ou o relatório já foi gerado
            while ((progressMonitor == null) || !progressMonitor.isCanceled() && progressValue < 100) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, "Interrupted Exception", e);
                }
            }
            //** Interrompe a geração do relatório
            geraRelatorio.interrupt();
        }
    }

    /**
     * Substituição da classe do Jasper Reports para que a visualização do
     * relatório seja apresentada com "Ajuste de Largura".
     *
     * @author jean
     *
     */
    class JPViewer extends JRViewer {

        public JPViewer(JasperPrint jprint) {
            super(jprint);

        }

        public void setAjusteLargura(boolean status) {
            this.btnFitWidth.setSelected(status);
        }
    }
}
