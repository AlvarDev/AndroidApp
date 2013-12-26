package com.autonoma.coleapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import bean.AlumnoBean;
import da.factory.LocalDaoFactory;

public class Matricula extends Activity {
	
	private Spinner nivel, grado;
	String mensaje;
	
	public Matricula() {
		mensaje="";
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_matricula);
		// Show the Up button in the action bar.
		setupActionBar();
		
		addItemsOnSpinnerNivel();
		addItemsOnSpinnerGrado();
		
		Intent intent = getIntent();
		mensaje = intent.getStringExtra(Login.EXTRA_MESSAGE);
		
		TextView msn = (TextView) findViewById(R.id.nomUser);
		msn.append(" : "+mensaje);
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.matricula, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void salir(View view){
		finish();
	}
	
	public void addItemsOnSpinnerNivel() {

		nivel = (Spinner) findViewById(R.id.nivel);
		List<String> list = new ArrayList<String>();
		list.add("Seleccione Nivel");
		list.add("Primaria");
		list.add("Secundaria");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		nivel.setAdapter(dataAdapter);
	}
	
	public void addItemsOnSpinnerGrado() {

		grado = (Spinner) findViewById(R.id.grado);
		List<String> list = new ArrayList<String>();
		list.add("Seleccione Grado");
		list.add("1ro");
		list.add("2ro");
		list.add("3ro");
		list.add("4ro");
		list.add("5ro");
		list.add("6ro");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		grado.setAdapter(dataAdapter);
	}
	
	
	
	public void matricular(View view){
		
		EditText nombre= (EditText) findViewById(R.id.nombre);
		EditText apellido= (EditText) findViewById(R.id.apellido);
		EditText fechaNac= (EditText) findViewById(R.id.fecha);
				 
		int itemNivel = nivel.getSelectedItemPosition();
		int itemGrado = grado.getSelectedItemPosition();
		
		if(itemNivel!=0 && itemGrado!=0){
			
			
			if((itemNivel+itemGrado)==8){
				Toast.makeText(getApplicationContext(), "no existe 6to de Secundaria", Toast.LENGTH_SHORT).show();
			}else{
				AlumnoBean alumno = new AlumnoBean();
				alumno.setNom(nombre.getText()+"");
				alumno.setApe(apellido.getText()+"");
				alumno.setFechaNac(fechaNac.getText()+"");
				alumno.setIdNivel(itemNivel);
				alumno.setIdGrado(itemGrado);
				
				new HandlerMatricula().execute(alumno);
				
			}
			
		}else{
			Toast.makeText(getApplicationContext(), "Seleccione Nivel y Grado", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public class HandlerMatricula extends AsyncTask<AlumnoBean, Void, String> {

        protected String doInBackground(AlumnoBean... alumno){
        	
        	String url="AlumnoDao.php?key=1&&"
        			+ "nom="+alumno[0].getNom()+"&&"
        			+ "ape="+alumno[0].getApe()+"&&"
        			+ "fechNac="+alumno[0].getFechaNac()+"&&"
        			+ "idNivel="+alumno[0].getIdNivel()+"&&"
        			+ "idGrado="+alumno[0].getIdGrado()+"";
        	
        	try{
                LocalDaoFactory local= new LocalDaoFactory();
                JSONObject jObj = local.crearConexionLocal(url);
            	return jObj.getString("estado");

            }catch(Exception e){
                return "No hay conexion con server";
            }

        }

        protected void onPostExecute(String result) {
        		if(result.equals("Alumno Matriculado")){
        			finish();
        		}
        		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }


    }
	
	
	
	
	
	
	
	
	
	
	

}
