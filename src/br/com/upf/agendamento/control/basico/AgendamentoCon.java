package br.com.upf.agendamento.control.basico;

import br.com.upf.agendamento.control.util.GenericController;
import br.com.upf.agendamento.model.basico.Agendamento;

/**
 *
 * @author Ademilsom
 */
public class AgendamentoCon extends GenericController<Agendamento>{

    public AgendamentoCon() {
        super(Agendamento.class, true);
    }
}
