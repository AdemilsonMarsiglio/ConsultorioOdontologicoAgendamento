package br.com.upf.agendamento.control.util;
import br.com.parcerianet.generic.modelo.util.controls.GenericDAO;

 public abstract class GenericCON<T> extends br.com.parcerianet.generic.controle.util.controls.GenericCON<T>{

    public GenericCON(Class classe, GenericDAO genericDao, boolean considerarAcaoAoGravar) {
        super(classe, genericDao, considerarAcaoAoGravar);
    }
}