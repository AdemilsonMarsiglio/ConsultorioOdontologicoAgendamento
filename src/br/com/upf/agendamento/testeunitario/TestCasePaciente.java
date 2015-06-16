/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upf.agendamento.testeunitario;

import br.com.upf.agendamento.control.basico.PacienteCon;
import junit.framework.TestCase;

/**
 *
 * @author Ademilsom
 */
public class TestCasePaciente {
    public static boolean gravar(String  nome){
        
        PacienteCon pacienteCon = new PacienteCon();
        pacienteCon.incluir();
        pacienteCon.getObjeto().setNmPaciente(nome);
        
        return pacienteCon.gravar();
    }
    
}
