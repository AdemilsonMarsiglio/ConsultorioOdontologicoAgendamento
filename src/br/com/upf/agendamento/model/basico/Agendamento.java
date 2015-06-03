package br.com.upf.agendamento.model.basico;

import br.com.parcerianet.util.ParseDate;
import br.com.upf.agendamento.model.basico.enums.StatusAgenda;
import java.beans.Beans;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ademilsom
 */
@Entity
@Table(name = "bas_agendamento")
@SequenceGenerator(name = "SEQ_BASAGENDAMENTO", sequenceName = "SEQ_BASAGENDAMENTO")
public class Agendamento extends Beans implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_BASAGENDAMENTO")
    @Column(length = 8, updatable = false)
    private Integer idAgendamento;
    
    @Column(length = 5, nullable = false)
    private String hrInicio;
    
    @Column(length = 5)
    private String hrFim;
    
    private StatusAgenda stAgendamento = StatusAgenda.N;
    
    @Temporal(TemporalType.DATE)
    private Date dtAgendamento;
    
    @Column(length = 200)
    private String obsAgendamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaciente", nullable = false)
    private Paciente paciente;

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public StatusAgenda getStAgendamento() {
        return stAgendamento;
    }

    public void setStAgendamento(StatusAgenda stAgendamento) {
        this.stAgendamento = stAgendamento;
    }

    public Date getDtAgendamento() {
        return dtAgendamento;
    }

    public void setDtAgendamento(Date dtAgendamento) {
        this.dtAgendamento = dtAgendamento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getHrInicio() {
        return hrInicio;
    }

    public void setHrInicio(String hrInicio) {
        this.hrInicio = hrInicio;
    }

    public String getHrFim() {
        return hrFim;
    }

    public void setHrFim(String hrFim) {
        this.hrFim = hrFim;
    }

    public String getObsAgendamento() {
        return obsAgendamento;
    }

    public void setObsAgendamento(String obsAgendamento) {
        this.obsAgendamento = obsAgendamento;
    }

    public String getDtAgendamentoTable() {
        return new ParseDate().formatData(getDtAgendamento(), "dd/MM/yyyy");
    }
    
    
}
