package br.com.upf.agendamento.view.util.relatorios;


import br.com.parcerianet.generic.modelo.util.controls.Bean;
import java.awt.Image;
import java.beans.Beans;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Classe que permite que sejam formatados os dados e os parametros que dever�o
 * ser apresentados para os relat�rios, ou que dever�o realizar algum controle
 * no relat�rio
 *
 * @author Ademilson
 *
 */
public class FormatationDataToJasper {

    private JRDataSource jrDataSource = null;

    //** Mapeia os argumentos que se desejo passar por parametro
    private Map parameters = new HashMap();

    //** Arquivo que permite que sejam pegadas as properties do relat�rio
    private Properties propHeader = new Properties();

    /**
     * Construtor default da classse
     *
     * @param dados
     * @param strComplemento
     * @param atributos
     * @param nmReportFile
     */
    public FormatationDataToJasper(List dados, String[] atributos, String nmReportFile, ResourceBundle resourceBundle
            , String nmEmpresa, String nmFilial, String usuario, String aplicacao, String cidade, Image logo) {
        //**Pega os dados do vo
        readData(dados, atributos, nmReportFile, resourceBundle, nmEmpresa, nmFilial, usuario, aplicacao, cidade, logo);
    }

    /**
     ** Constrói MAP de par�metros
     */
    private void buildParametros(ResourceBundle resource, String nmReportFile, String nmEmpresa, String nmFilial,
            String usuario, String aplicacao, String cidade, Image logo) {
//        try { //** Carrega arquivo de properties
//            propHeader.load(getClass().getResource("/configuracoes.properties").openStream());
//        } catch (IOException ioEx) {
//            Logger.getLogger(FormatationDataToJasper.class.getName()).log(Level.SEVERE, "Erro ao carregar arquivo configuracoes.properties", ioEx);
//        }

        parameters.put("Data", "Data: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        parameters.put("Hora", "Hora: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        
      //** Se usuário não estiver logado, as três linhas abaixo irão gerar uma
        //** Exception que é tratada na classe que chama o relatório.
        //parameters.put("DsReport"              , JPFramePrincipal.getResourceBundle().getResString("nm.rel."+nmReportFile));
        parameters.put("Empresa", nmEmpresa);
        parameters.put("Filial", nmFilial);
        parameters.put("TituloRel", nmReportFile); // Ex: Relatório de Perfil
        //parameters.get("DsReport").toString()); 
        parameters.put("Usuario", "Usuário: " + usuario);
        parameters.put("Cidade", cidade);
        parameters.put("Aplicacao", "Programa: " + aplicacao);
        parameters.put("StrPagina", "Página");
        //parameters.put("StrTotal"              , JPFramePrincipal.getResourceBundle().getResString("str.total"));
        if (logo != null) {
            parameters.put("Logo", logo);//new ImageIcon(Imagens.class.getResource(JPFramePrincipal.getSessionLogin().getEmpresa().getLogo())).getImage());
        }
        parameters.put("REPORT_RESOURCE_BUNDLE", resource);
    }

    /**
     * Permite que sejam pegos os dados do vo e jogados para um data source,
     * para que sejam passados para o relat�rio
     *
     * @param dados
     * @param complemento
     * @param atributos
     * @param nmReportFile
     */
    protected void readData(List dados, String[] atributos, String nmReportFile, ResourceBundle resouceBundle,
            String nmEmpresa, String nmFilial, String usuario, String aplicacao, String cidade, Image logo) {
        //** Constrói vetor de parametros
        buildParametros(resouceBundle,nmReportFile, nmEmpresa, nmFilial, usuario, aplicacao, cidade, logo);

        //** BEGIN: Variáveis e funções referente aos dados do Relatório
        if (dados.isEmpty()) {
            jrDataSource = null;
        } else if (dados.get(0) instanceof Beans) {

            if (atributos == null) {

                createDataSource(dados);
            } else {

                int lin = dados.size();
                int col = atributos.length;
                String[][] novosDados = new String[lin][col];
                int cont = 0;
                ListIterator iter = dados.listIterator();

                while (iter.hasNext()) {
                    Object linha = iter.next();
                    for (int j = 0; j < atributos.length; j++) {
                        Object attr = ((Bean) linha).getAttribute(atributos[j]);
                        novosDados[cont][j] = (attr != null) ? attr.toString() : "";
                    }
                    cont++;
                }
                createDataSource(novosDados);
            }
        } else {

            int lin = dados.size();
            int col = ((Object[]) dados.get(0)).length;
            String[][] novosDados = new String[lin][col];
            int cont = 0;
            ListIterator iter = dados.listIterator();

            while (iter.hasNext()) {
                Object[] linha = (Object[]) iter.next();
                for (int j = 0; j < linha.length; j++) {
                    Object attr = linha[j];
                    novosDados[cont][j] = (attr != null) ? attr.toString() : "";
                }
                cont++;
            }
            createDataSource(novosDados);
        }
        //** END: Dados Relatório
    }

    /**
     ** Armazena dados de um ViewObject em um ArrayList
   *
     */
    protected void createDataSource(List dados) {
        jrDataSource = new JRBeanCollectionDataSource(dados);
    }

    /**
     ** Armazena dados de um ViewObject em um ArrayList
   *
     */
    protected void createDataSource(Object[][] dados) {
        jrDataSource = new JDataSource(dados);
    }

    /**
     * Permite que seja recuperado o data source, onde est�o contidos os dados
     * do ViewObject
     *
     * @return
     */
    public JRDataSource getDataSource() {
        return jrDataSource;
    }

    /**
     * Permite que seja setado o data source, onde est�o contidos os dados do
     * ViewObject
     *
     * @param jrDataSource
     */
    public void setDataSource(JRDataSource jrDataSource) {
        this.jrDataSource = jrDataSource;
    }

    /**
     * Permite que sejam recuperados os parametros para o relat�rio
     *
     * @return
     */
    public Map getParametros() {
        return parameters;
    }

    /**
     * Permite que sejam setados os parametros para o relat�rio
     *
     * @param parameters
     */
    public void setParametros(Map parameters) {
        this.parameters = parameters;
    }
}
