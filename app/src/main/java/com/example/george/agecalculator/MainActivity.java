package com.example.george.agecalculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Variables utilizadas
    private TextView txtSubtitulo;
    private TextView txtDiferencia;
    private TextView txtEdadEvaluada;
    private Calendar calendar;
    private DatePickerDialog datepicker;
    private int anioActual;
    private int mesActual;
    private int diaActual;
    private int edad;
    private int ultimaEdadEvaluada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Establecer el ID a cada componente
        txtSubtitulo = (TextView) findViewById(R.id.txtSubtitulo);
        txtDiferencia = (TextView) findViewById(R.id.txtDiferencia);
        txtEdadEvaluada = (TextView) findViewById(R.id.txtEdadEvaluada);
        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);

        //Asignar interfaz de escucha al boton
        btnCalcular.setOnClickListener(this);

        //Iniciar objetivo calendar y almacenar fecha actual
        calendar = Calendar.getInstance();
        anioActual = calendar.get(Calendar.YEAR);
        mesActual = calendar.get(Calendar.MONTH);
        diaActual = calendar.get(Calendar.DAY_OF_MONTH);

        //Creacion del DatePickerDialog
        datepicker = new DatePickerDialog(this, datepickerListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE));
    }



    @Override
    public void onClick(View view) {

        datepicker.show();

    }


    //Se crea la interfaz de escucha para el DatePickerDialog
    private DatePickerDialog.OnDateSetListener datepickerListener = new DatePickerDialog.OnDateSetListener(){

        //Se sobreescribe este metodo para establecer la data del DatePicker
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR,i);
            calendar.set(Calendar.MONTH,i1);
            calendar.set(Calendar.DATE,i2);

            //Se adquiere la fecha seleccionada
            int anioNacimiento = datePicker.getYear();
            int mesNacimiento = datePicker.getMonth();
            int diaNacimiento = datePicker.getDayOfMonth();

            //Se llama al metodo para calcular la Edad
            calcularEdad(anioNacimiento,mesNacimiento,diaNacimiento);

        }
    };

    private void calcularEdad (int anioNacimiento, int mesNacimiento, int diaNacimiento){

        if (anioNacimiento <= anioActual && mesNacimiento <= mesActual && diaNacimiento <= diaActual){

            edad = anioActual - anioNacimiento;

            String fecha = "Tu edad es : " + String.valueOf(edad) + " Años , " + String.valueOf(mesActual - mesNacimiento) +
                    " Meses  y " + String.valueOf(diaActual - diaNacimiento) + " Dias ";

            //Se fija la edad en pantalla
            txtSubtitulo.setText(fecha);

            //Se calcula la diferencia entres edades
            calcularDiferencia();

            ultimaEdadEvaluada = edad;


        } else {

            String mensaje = "Debe seleccionar una fecha menor a la fecha actual";

            txtSubtitulo.setText("");

            txtDiferencia.setText("");

            txtEdadEvaluada.setText("");

            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();

        }

    }

    private void calcularDiferencia (){

        if (ultimaEdadEvaluada != 0){
;
            int ultimaEdad = ultimaEdadEvaluada;

            int edadMayor = Math.max(ultimaEdad,edad);

            int edadMenor = Math.min(ultimaEdad,edad);

            int diferenciaEdad = edadMayor - edadMenor;

            String diferencia = "La diferencia entre edades es : " + diferenciaEdad + " Años ";

            String edadAnterior = "Edad Anterior : " + String.valueOf(ultimaEdadEvaluada);

            txtEdadEvaluada.setText(edadAnterior);

            txtDiferencia.setText(diferencia);

        }

    }





}
