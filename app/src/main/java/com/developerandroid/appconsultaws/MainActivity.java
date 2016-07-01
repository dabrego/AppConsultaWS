package com.developerandroid.appconsultaws;

import android.os.StrictMode;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(android.os.Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy);
        }

    } // Ends onCreate

    public void consultaTemperaturaGradosF(){
        String NAMESPACE = "http://www.w3schools.com/xml/";
        String URL="http://www.w3schools.com/xml/tempconvert.asmx";
        String METHOD_NAME = "CelsiusToFahrenheit";
        String SOAP_ACTION = "http://www.w3schools.com/xml/CelsiusToFahrenheit";
        try {
            // Modelo el request
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Celsius", "37"); // Paso parametros al WS
            // Modelo el Sobre
            SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11); sobre.dotNet = true;
            sobre.setOutputSoapObject(request);
            // Modelo el transporte
            HttpTransportSE transporte = new HttpTransportSE(URL);
            // Llamada
            transporte.call(SOAP_ACTION, sobre);
            // Resultado
            SoapPrimitive resultado = (SoapPrimitive) sobre.getResponse();
            Toast.makeText(getApplicationContext(), "Resultado: " + resultado.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error: " + e.getMessage() + " ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
