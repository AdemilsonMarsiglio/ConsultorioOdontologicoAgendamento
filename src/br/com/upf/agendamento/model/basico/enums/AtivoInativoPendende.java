package br.com.upf.agendamento.model.basico.enums;

/**
 *
 * @author Ademilsom
 */
public enum AtivoInativoPendende {
    A("ativo"), I("inativo"), P("pendente");
    
    private String enumeration;
    
    private AtivoInativoPendende(String enumeration){
        this.enumeration = enumeration;
    }

    @Override
    public String toString() {
        return enumeration.toUpperCase();
    }
}
