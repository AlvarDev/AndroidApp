package com.autonoma.coleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Opciones extends Activity {

	public final static String EXTRA_MESSAGE = "com.autonoma.coleapp.MESSAGE";
	String mensaje;
	
	public Opciones() {
		mensaje="";
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opciones);
		
		
		Intent intent = getIntent();
		mensaje = intent.getStringExtra(Login.EXTRA_MESSAGE);
		
		TextView msn = (TextView) findViewById(R.id.nom);
		msn.append(" : "+mensaje);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opciones, menu);
		return true;
	}
	
	
	public void irMatricula(View view){
		Intent intent = new Intent(getApplicationContext(), Matricula.class);
		intent.putExtra(EXTRA_MESSAGE, mensaje);
		startActivity(intent);
		
	}
	
	public void irVer(View view){
		Intent intent = new Intent(getApplicationContext(), VerAlumnos.class);
		intent.putExtra(EXTRA_MESSAGE, mensaje);
		startActivity(intent);
		
	}

	public void irVerGrados(View view){
		Intent intent = new Intent(getApplicationContext(), VerGrados.class);
		intent.putExtra(EXTRA_MESSAGE, mensaje);
		startActivity(intent);
		
	}
	
	public void salir(View view){
		finish();
	}

}
