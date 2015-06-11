/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upf.agendamento.view.util;

import br.com.upf.agendamento.view.imagens.Imagens;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Ademilsom
 */
public abstract class NavigatorBar extends JPanel {

    private final JButton btnIncluir = new JButton(Imagens.IMG_ADD);
    private final JButton btnAlterar = new JButton(Imagens.IMG_EDIT);
    private final JButton btnSalvar = new JButton(Imagens.IMG_SAVE);
    private final JButton btnCancelar = new JButton(Imagens.IMG_CANCEL);
    private final JButton btnDeletar = new JButton(Imagens.IMG_DELETE);
    private final JButton btnImprimir = new JButton(Imagens.IMG_PRINT);

    public NavigatorBar() {
        setLayout(new BorderLayout());
        add(criaNavigatorBar());
        
        setEnableBtnNavigation(true);
    }

    private JToolBar criaNavigatorBar() {

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        btnIncluir.setToolTipText("Incluir");
        btnAlterar.setToolTipText("Alterar");
        btnSalvar.setToolTipText("Salvar");
        btnCancelar.setToolTipText("Cancelar");
        btnDeletar.setToolTipText("Excluir");
        btnImprimir.setToolTipText("Imprimir");

        btnIncluir.setBorderPainted(false);
        btnAlterar.setBorderPainted(false);
        btnSalvar.setBorderPainted(false);
        btnCancelar.setBorderPainted(false);
        btnDeletar.setBorderPainted(false);
        btnImprimir.setBorderPainted(false);

        btnIncluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setEnableBtnNavigation(false);
                incluir_();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                cancelar_();
                setEnableBtnNavigation(true);
            }
        });

        btnAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(alterar_()){
                    setEnableBtnNavigation(false);
                }
            }
        });

        btnSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(salvar_()){
                    setEnableBtnNavigation(true);
                }
            }
        });
        btnDeletar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                deletar_();
            }
        });
        
        btnImprimir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                imprimir_();
            }
        });

        toolBar.add(btnIncluir);
        toolBar.add(btnAlterar);
        toolBar.add(btnDeletar);
        toolBar.add(btnSalvar);
        toolBar.add(btnCancelar);
        toolBar.add(btnImprimir);

        return toolBar;
    }

    public void setEnableBtnNavigation(boolean enable) {
        btnIncluir.setEnabled(enable);
        btnAlterar.setEnabled(enable);
        btnDeletar.setEnabled(enable);
        btnImprimir.setEnabled(enable);
        btnCancelar.setEnabled(!enable);
        btnSalvar.setEnabled(!enable);
    }

    public abstract boolean incluir_();

    public abstract boolean alterar_();

    public abstract boolean salvar_();

    public abstract void deletar_();

    public abstract void cancelar_();
    
    public abstract void imprimir_();

}
