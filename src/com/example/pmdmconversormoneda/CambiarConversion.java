package com.example.pmdmconversormoneda;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CambiarConversion extends Activity {
	String datos[]={"Dólares","Euros","Libras","Pesetas"};
	String dato[]={"Euros"};
	String monedas3,monedas4;
	Editable factorTexto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cambiar_conversion);
		ArrayAdapter<String> miAdaptador1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
		final Spinner cmbOpciones1=(Spinner) findViewById(R.id.spinner3);
		cmbOpciones1.setAdapter(miAdaptador1);
		cmbOpciones1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {          
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				monedas3 = parent.getItemAtPosition(position).toString()+": ";
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub 
			}
		});
		
		ArrayAdapter<String> miAdaptador2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dato);
		final Spinner cmbOpciones2=(Spinner) findViewById(R.id.spinner4);
		cmbOpciones2.setAdapter(miAdaptador2);
		cmbOpciones2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {          
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				monedas4 = parent.getItemAtPosition(position).toString()+": ";
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub 
			}
		});
		cmbOpciones2.setEnabled(false);
		
		final EditText factor=(EditText) findViewById(R.id.editText1);
		factorTexto=factor.getText();
		
		final Button btnAceptar=(Button) findViewById(R.id.button3);
		btnAceptar.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent=new Intent(CambiarConversion.this,CambiarConversion.class);
				intent.putExtra("Moneda3",monedas3);
				intent.putExtra("Moneda4", monedas4);
				intent.putExtra("Factor",factorTexto);
				setResult(Activity.RESULT_OK,intent);
				CambiarConversion.this.finish();
			}
		});
		final Button btnCancelar=(Button) findViewById(R.id.button4);
		btnCancelar.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				CambiarConversion.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cambiar_conversion, menu);
		return false;
	}

}
