package br.com.upf.agendamento.model.basico.enums;

/**
 *
 * @author Ademilsom
 */
public enum AtivoInativo {
    A("ativo"), I("inativo");
    
    private String enumeration;
    
    private AtivoInativo(String enumeration){
        this.enumeration = enumeration;
    }

    @Override
    public String toString() {
        return enumeration.toUpperCase();
    }
}
