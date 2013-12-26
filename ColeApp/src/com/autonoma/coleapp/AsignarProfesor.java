package com.autonoma.coleapp;

import org.json.JSONObject;

import da.factory.LocalDaoFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class AsignarProfesor extends Activity {

	public final static String EXTRA_MESSAGE = "com.autonoma.coleapp.MESSAGE";
	String[] datos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asignar_profesor);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent intent = getIntent();
		datos = intent.getStringArrayExtra(VerGrados.EXTRA_MESSAGE);
		TextView msn = (TextView) findViewById(R.id.userProfesor);
		msn.append(" : " +datos[0]);
		
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
		getMenuInflater().inflate(R.menu.asignar_profesor, menu);
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
	
	public void volver(View view){
		Intent intent = new Intent(getApplicationContext(), VerGrados.class);
		intent.putExtra(EXTRA_MESSAGE, datos[0]);
		startActivity(intent);
		finish();
	}
	
	public void asignarProfesor(View view){
		
		EditText nombre= (EditText) findViewById(R.id.nomProfesor);
		EditText apellido= (EditText) findViewById(R.id.apeProfesor);
		EditText sueldo= (EditText) findViewById(R.id.sueldoProfesor);
		new HandlerAsignarProfesor().execute(nombre.getText()+"", apellido.getText()+"", sueldo.getText()+"");
		
	}
	
	
	
	public class HandlerAsignarProfesor extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... datosProfesor){
        	
        	String url="GradoDao.php?key=2&&nom="+datosProfesor[0]
        			+"&&ape="+datosProfesor[1]
        			+"&&sueldo="+datosProfesor[2]
        			+"&&idNivel="+datos[1]+"&&idGrado="+datos[2];
        	
        	try{
                LocalDaoFactory local= new LocalDaoFactory();
                JSONObject jObj = local.crearConexionLocal(url);
            	return jObj.getString("estado");

            }catch(Exception e){
                return "No hay conexion con server";
            }

        }

        protected void onPostExecute(String result) {
        		if(result.equals("Profesor Asignado")){
        			Intent intent = new Intent(getApplicationContext(), VerGrados.class);
        			intent.putExtra(EXTRA_MESSAGE, datos[0]);
        			startActivity(intent);
        			finish();
        		}
        		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }


    }
	
	

}
