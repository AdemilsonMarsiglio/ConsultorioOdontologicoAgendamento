/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upf.agendamento.control.util;

import br.com.parcerianet.modelo.util.controls.GenericModel;
import br.com.parcerianet.util.properties.UIResourceBundle;

/**
 *
 * @author Ademilsom
 */
public class GenericController<T> extends GenericCON<T> {

    public GenericController(Class classe, boolean considera) {
        super(classe, new GenericModel(), considera);
    }

    public GenericController(Class classe) {
        super(classe, new GenericModel(), false);
    }

    @Override
    public UIResourceBundle getResourceBundle() {
        return (UIResourceBundle) UIResourceBundle.getBundle(UIResourceBundleCON.class.getName());
    }

}
