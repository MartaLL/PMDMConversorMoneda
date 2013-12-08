package com.example.pmdmconversormoneda;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.method.DialerKeyListener;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView monedas1,monedas2,factor;
	int REQUEST_TEXT;
	double conversion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText moneda1=(EditText) findViewById(R.id.editText);
		final EditText moneda2=(EditText) findViewById(R.id.editText2);
		monedas1=(TextView) findViewById(R.id.textView1);
		monedas2=(TextView) findViewById(R.id.textView2);
		factor=(TextView) findViewById(R.id.textView4);
		factor.setText("166.386");
		moneda1.requestFocus();
		moneda1.setOnKeyListener(new OnKeyListener() { 
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				try{
					conversion=Float.parseFloat(factor.getText().toString());
					Float m1=Float.parseFloat(moneda1.getText().toString());
					final Float operacion1=(float) (m1/conversion);
					moneda2.setText(operacion1.toString()); 
					return true;
				}catch(NumberFormatException nfe){
					Toast miToast=Toast.makeText(getApplicationContext(),"Introduzca un número",Toast.LENGTH_LONG);
					miToast.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
					miToast.show();
				}
				return false;
			} 
		});
	}
	//addtextchangelistener para evitar problemas de memoria se debe cambiar
	//el evento de escritura con el evento onkeylistener cuando yo escribo que haga
	//algo, cuando yo escribo se modifique el texto tiene varios métodos uno de 
	//ellos es onkey que es de pulsacion de tecla el componente sobre el que se ha
	//pulsado, el codigo de la tecla que se ha pulsado y el identificador del evento
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId()==R.id.SubmnuCambM){
			REQUEST_TEXT=0;
			Intent intent=new Intent(MainActivity.this,CambiarMoneda.class);
			startActivityForResult(intent,REQUEST_TEXT);
			return true;
		}
		else if(item.getItemId()==R.id.SubmnuCambF){
			REQUEST_TEXT=1;
			Intent intent=new Intent(MainActivity.this,CambiarConversion.class);
			startActivityForResult(intent,REQUEST_TEXT);
			return true;
			//Un cuadro en el cual se establece el nombre y el tipo y un boton con ok y
			//llevar el resultado a la activity principal con setResult para que la 
			//actividad principal pueda acceder a esos datos. Hay dialogos predefinidos
			//en los cuales existe el return.
		}
		else if(item.getItemId()==R.id.SubmnuSalir){
			this.finish();
			return true;
		}
		else
		{
			return super.onOptionsItemSelected(item);
		}	
	}
	protected void onActivityResult(int requestCode,int resultCode, Intent data){
		if(requestCode==0){
			if(resultCode==Activity.RESULT_OK){
				monedas1.setText(data.getExtras().get("Moneda1").toString());
				monedas2.setText(data.getExtras().get("Moneda2").toString());
			}
		}
		if(requestCode==1){
			if(resultCode==Activity.RESULT_OK){
				monedas1.setText(data.getExtras().get("Moneda3").toString());
				monedas2.setText(data.getExtras().get("Moneda4").toString());
				factor.setText(data.getExtras().get("Factor").toString());
			}
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			// Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
			return true;
		}
		//para las demas cosas, se reenvia el evento al listener habitual
		return super.onKeyDown(keyCode, event);
	} 
}
