package br.com.upf.agendamento.model.basico.enums;

/**
 *
 * @author Ademilsom
 */
public enum StatusAgenda {
    N("novo"), A("atendido"), C("cancelado");

    private String enumeration;

    private StatusAgenda(String enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public String toString() {
        return enumeration.toUpperCase();
    }
}
