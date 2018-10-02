package ar.edu.utn.frsf.isi.dam.laboratiorio01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.SeekBar;
import android.widget.ProgressBar;

import ar.edu.utn.frsf.dam.laboratorio01.modelo.Cliente;
import ar.edu.utn.frsf.dam.laboratorio01.modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {

    private PlazoFijo pf;
    private Cliente cliente;
    private Double resultadoPF;

    // widgets de la vista
    private EditText edEmail;
    private EditText edtCUIL;
    private RadioGroup rGroup;
    private EditText montoInversion;
    private SeekBar seekBarDias;
    private TextView intereses;
    private ToggleButton btnSeleccion;
    private CheckBox terminos;
    private Button btnCalcularPlazoFijo;
    private TextView resultado;
    private TextView dias;
    private RadioButton dolares;
    private RadioButton pesos;

    private Button btnHacerPlazoFijo;
    private EditText edtMonto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pf = new PlazoFijo(getResources().getStringArray(R.array.tasas));
        cliente = new Cliente();


        edEmail = (EditText) findViewById(R.id.edtEmail);
        edtCUIL = (EditText) findViewById(R.id.CUIL);
        rGroup = (RadioGroup) findViewById(R.id.grupoRadioButton);
        edtMonto = (EditText) findViewById(R.id.montoAInvertir);
        final RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(rGroup.getCheckedRadioButtonId());
        montoInversion = (EditText) findViewById(R.id.montoAInvertir);
        intereses = (TextView) findViewById(R.id.interesesGanados);
        btnSeleccion = (ToggleButton) findViewById(R.id.botonAccion);
        terminos = (CheckBox) findViewById(R.id.checkBoxtermino);
        btnHacerPlazoFijo = (Button) findViewById(R.id.botonPlazoFijo);
        resultado = (TextView) findViewById(R.id.mensajeResultado);
        btnHacerPlazoFijo.setEnabled(false);
        seekBarDias = (SeekBar) findViewById(R.id.diasDeInversion);
        dias = (TextView) findViewById(R.id.diasSeleccionados);
        dolares = (RadioButton) findViewById(R.id.dolares);
        pesos = (RadioButton) findViewById(R.id.pesos);
        //dias.setText("Cantidad de dias: " + seekBarDias.getProgress());


        seekBarDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (montoInversion.getText().equals(""))
                    return;

                dias.setText("Dias de plazo: " + seekBarDias.getProgress());
                intereses.setText("$ " + pf.intereses(progress, Integer.parseInt(montoInversion.getText().toString())));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean chekeado = terminos.isChecked();
                if (chekeado) {
                    btnHacerPlazoFijo.setEnabled(true);
                } else {
                    CharSequence text = "Es obligatorio aceptar las condiciones";
                    int duration = Toast.LENGTH_SHORT;
                    btnHacerPlazoFijo.setEnabled(false);
                    Toast toast = Toast.makeText(MainActivity.this, text, duration);
                    toast.show();

                }
            }
        });


        btnHacerPlazoFijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuesta;
                if (edEmail.getText().equals(""))
                    return;

                else if (montoInversion.getText().equals(""))
                    return;

                if (dolares.isChecked()) {

                    respuesta = "dolares";
                } else {

                    respuesta = "pesos";
                }

                if (edEmail != null && Integer.parseInt(montoInversion.getText().toString()) > 0) {

                    resultado.setText("El plazo fijo se realizo exitosamente." + "Plazo Fijo:"
                            + seekBarDias.getProgress()
                            + "Monto:" + edtMonto.getText().toString()
                            + " Avisar Vencimiento = " + btnSeleccion.isChecked()
                            + "Renovar Automaticamente = " + terminos.isChecked()
                            + "Moneda: " + respuesta);
                }
            }
        });
    }
}