package br.com.upf.agendamento.view.util;

import br.com.parcerianet.util.TransformString;
import br.com.upf.agendamento.view.AgendamentoForm;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Ademilsom
 */
public class JFormattedHora extends JFormattedTextField {

    private int horaField;
    private int minutoField;

    public JFormattedHora() {
        super();

        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("##:##");
        } catch (ParseException ex) {
            Logger.getLogger(AgendamentoForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        formatter.install(this);

        setHorizontalAlignment(JLabel.CENTER);
        setColumns(6);

        formataHora();
    }

    private void formataHora() {
        addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent fe) {
                super.focusLost(fe);

                JFormattedTextField textField = ((JFormattedTextField) fe.getSource());

                Calendar c = Calendar.getInstance();

                String text = textField.getText();

                String char1 = text.substring(0, 1);
                String char2 = text.substring(1, 2);
                String char3 = text.substring(3, 4);
                String char4 = text.substring(4, 5);

                if (char1.equals(" ")) {
                    char1 = "0";
                }

                if (char2.equals(" ")) {
                    char2 = "0";
                }

                if (char3.equals(" ")) {
                    char3 = "0";
                }

                if (char4.equals(" ")) {
                    char4 = "0";
                }

                c.set(Calendar.MINUTE, Integer.parseInt(char3 + char4));
                c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(char1 + char2));

                int hora = c.get(Calendar.HOUR_OF_DAY);
                int minuto = c.get(Calendar.MINUTE);

                String horaFormatada = "";
                String minutoFormatado = "";

                if (hora < 9) {
                    horaFormatada = "0" + parseIntToString(hora);
                } else {
                    horaFormatada = parseIntToString(hora);
                }

                if (minuto < 9) {
                    minutoFormatado = "0" + parseIntToString(minuto);
                } else {
                    minutoFormatado = parseIntToString(minuto);
                }

                horaField = parseStringToInt(horaFormatada);
                minutoField = parseStringToInt(minutoFormatado);

                textField.setText(horaFormatada + minutoFormatado);
            }

            private String parseIntToString(int value) {
                return String.valueOf(value);
            }

            private int parseStringToInt(String value) {
                return Integer.parseInt(value);
            }
        });
    }

    public int getHora() {
        return horaField;
    }

    public int getMinuto() {
        return minutoField;
    }
    
    public void setHoraField(int hora, int minuto){
        setText(hora + ":" + minuto);
    }
}
