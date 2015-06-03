package br.com.upf.agendamento.model.basico;

import br.com.upf.agendamento.model.basico.enums.AtivoInativo;
import java.beans.Beans;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ademilsom
 */
@Entity
@Table(name = "bas_paciente")
@SequenceGenerator(name = "SEQ_BASPACIENTE", sequenceName = "SEQ_BASPACIENTE")
public class Paciente extends Beans implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_BASPACIENTE")
    @Column(length = 8, updatable = false)
    private Integer idPaciente;
    
    @Column(length = 30, nullable= false)
    private String nmPaciente;
    
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;
    
    @Column(length = 15)
    private String cpf;
    
    //Endereço :
    
    @Column(length = 9)
    private String cep;

    @Column(length =100)
    private String dsLogradouro;

    @Column(length =10)
    private String nrEndereco;

    @Column(length =50)
    private String dsBairro;

    @Column(length =15)
    private String fone;
    
    @Column(length =30)
    private String cidade;
    
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "char")
    private AtivoInativo stCliente = AtivoInativo.A;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadastro;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNmPaciente() {
        return nmPaciente;
    }

    public void setNmPaciente(String nmPaciente) {
        this.nmPaciente = nmPaciente;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getCpf() {
        return cpf == null ? "" : cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep == null ? "" : cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDsLogradouro() {
        return dsLogradouro == null  ? "" : dsLogradouro;
    }

    public void setDsLogradouro(String dsLogradouro) {
        this.dsLogradouro = dsLogradouro;
    }

    public String getNrEndereco() {
        return nrEndereco == null ? "" : nrEndereco;
    }

    public void setNrEndereco(String nrEndereco) {
        this.nrEndereco = nrEndereco;
    }

    public String getDsBairro() {
        return dsBairro == null ? "" : dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public String getCidade() {
        return cidade == null ? "" : cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public AtivoInativo getStCliente() {
        return stCliente;
    }

    public void setStCliente(AtivoInativo stCliente) {
        this.stCliente = stCliente;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    

    @Override
    public String toString() {
        return getNmPaciente();
    }
    
}
