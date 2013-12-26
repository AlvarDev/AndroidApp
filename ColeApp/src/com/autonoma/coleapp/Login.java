package com.autonoma.coleapp;



import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import bean.AccesoBean;
import da.factory.LocalDaoFactory;


public class Login extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.autonoma.coleapp.MESSAGE";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}	
	
	public void salir(View view){
		finish();
	}
	
	public void ingresar(View view){
		
		EditText idAcceso= (EditText) findViewById(R.id.idAcceso);
		EditText contra= (EditText) findViewById(R.id.contra);
		
		AccesoBean acceso = new AccesoBean();
		acceso.setIdAcceso(idAcceso.getText()+"");
		acceso.setContra(contra.getText()+"");
				
		new HandlerLogin().execute(acceso);
	}
	
	public class HandlerLogin extends AsyncTask<AccesoBean, Void, String>{
		
		protected String doInBackground(AccesoBean... acceso){
			
			String url="AccesoDao.php?key=1&&usuario="+acceso[0].getIdAcceso()+"&&clave="+acceso[0].getContra()+"";
			try {
				
				LocalDaoFactory local = new LocalDaoFactory();
				JSONObject jobj= local.crearConexionLocal(url);
				return jobj.getString("nombre");
				
			} catch (Exception e) {
				return "--";
			}
			
		}
		
		protected void onPostExecute(String result){
			
			if(!result.equals("no")){
				Intent intent = new Intent(getApplicationContext(), Opciones.class);
				intent.putExtra(EXTRA_MESSAGE, result);
				startActivity(intent);
				finish();
			}else if(result.equals("--")){
				Toast.makeText(getApplicationContext(), "No se conecto con Server", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(), "Usuario o Password incorrecto", Toast.LENGTH_SHORT).show();
				EditText idAcceso= (EditText) findViewById(R.id.idAcceso);
				EditText contra= (EditText) findViewById(R.id.contra);
				idAcceso.setText("");
				contra.setText("");
			}
		}
		
		
		
		
	}
	
	
	
	
	
	
	
}
