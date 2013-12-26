package com.autonoma.coleapp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.ls.LSInput;

import com.autonoma.coleapp.VerAlumnos.HandlerCargarAlumnos;

import bean.ListaGradosBean;
import da.factory.LocalDaoFactory;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class VerGrados extends Activity {

	public final static String EXTRA_MESSAGE = "com.autonoma.coleapp.MESSAGE";
	String mensaje;
	ListaGradosBean listaGrados[];
	int idlist;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_grados);
		// Show the Up button in the action bar.
		setupActionBar();
				
		Intent intent = getIntent();
		
		if(intent.getStringExtra(Opciones.EXTRA_MESSAGE)==null){
			mensaje = intent.getStringExtra(AsignarProfesor.EXTRA_MESSAGE);
		}else{
			mensaje = intent.getStringExtra(Opciones.EXTRA_MESSAGE);
		}
		
		TextView msn = (TextView) findViewById(R.id.user);
		msn.append(" : "+mensaje);
		mostrarPopUp();
		//Carga lista de cursos de la DB
		new HandlerCargarGrados().execute(); 
		
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
		getMenuInflater().inflate(R.menu.ver_grados, menu);
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
		finish();
	}
	
	//Muestra el menu de opciones al dar click a un grado
	private void mostrarPopUp(){
		ListView lista = (ListView) findViewById(R.id.ListaGradosVie);
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
	    @Override
		public void onItemClick(AdapterView<?>paret, View viewClicked, int position,long id) {
			// TODO Auto-generated method stub
	    	
	    	idlist=position;
	    	CharSequence opciones[] = new CharSequence[] {"Asignar Profesor", "Liberar Grado"};

	    	AlertDialog.Builder builder = new AlertDialog.Builder(VerGrados.this);
	    	builder.setTitle("¿Que desea hacer?");
	    	builder.setItems(opciones, new DialogInterface.OnClickListener() {
	    	    @Override
	    	    public void onClick(DialogInterface dialog, int which) {
	    	       
	    	    	switch (which) {
					case 0:	// Me envia a la Actividad de Asignar Profesor
						if(listaGrados[idlist].getIdProfesor()==0){
								Intent intent = new Intent(getApplicationContext(), AsignarProfesor.class);
								String[] datos={mensaje,listaGrados[idlist].getIdNivel()+"",listaGrados[idlist].getIdGrado()+""};
								intent.putExtra(EXTRA_MESSAGE, datos);
								startActivity(intent);
								finish();
							}else{
								Toast.makeText(getApplicationContext(), "Ya tiene Profesor Asignado \n Liberar Grado", Toast.LENGTH_LONG).show();	
							}
						break;
					case 1: if(listaGrados[idlist].getIdProfesor()!=0){
								new HandlerLiberarGrado().execute(listaGrados[idlist].getIdProfesor(),
								listaGrados[idlist].getIdNivel(),listaGrados[idlist].getIdGrado());
							}else{
								Toast.makeText(getApplicationContext(), "El Grado ya esta libre", Toast.LENGTH_LONG).show();
							}
						break;	

					default:
						break;
					}    	
	    	    }
	    	});
	    	builder.show();
	    	
	    	
		}
		});
		
	}
	
	
	
	
	public class HandlerCargarGrados extends AsyncTask<Void, Void, Integer> {

        protected Integer doInBackground(Void... Voids){
        	
        	JSONObject obj;
        	String url="GradoDao.php?key=1";
        	
        	try{
                LocalDaoFactory local= new LocalDaoFactory();
                obj = local.crearConexionLocal(url);
                JSONArray array = obj.getJSONArray("GRADOS");
                listaGrados= new ListaGradosBean[array.length()];
                   
                for(int i=0; i<array.length(); i++){
                JSONObject jObj = array.getJSONObject(i);
                ListaGradosBean grado = new ListaGradosBean();
                grado.setIdNivel(Integer.parseInt(jObj.getString("idNivel")));
                grado.setNivel(jObj.getString("nivel"));
                grado.setIdGrado(Integer.parseInt(jObj.getString("idGrado")));
                grado.setGrado(jObj.getString("grado"));
                grado.setIdProfesor(Integer.parseInt(jObj.getString("idProfesor")));
                grado.setNombre(jObj.getString("nombre"));
                grado.setSueldo(Float.parseFloat(jObj.getString("sueldo")));
                listaGrados[i]=grado;
                }
            }catch(Exception e){
            	Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();		
            }

        	return 0;
        	
        }

        protected void onPostExecute(Integer non) {
        	//Create list of items
        	String datosGrados[]= new String[listaGrados.length];
        	for(int i=0; i< datosGrados.length; i++){
        		datosGrados[i]="Nivel: "+listaGrados[i].getNivel()
        				+ "\tGrado: "+listaGrados[i].getGrado()+"\n"
        				+ "Profesor: "+listaGrados[i].getNombre()
        				+ "\tSueldo: "+listaGrados[i].getSueldo()+"\n";
        	}
        	
        	//Build Adapter
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.grados, datosGrados);
        	//Configure the list view
        	
        	ListView lista = (ListView) findViewById(R.id.ListaGradosVie);
    		lista.setAdapter(adapter);
    		
        }

    }
	
	
	
public class HandlerLiberarGrado extends AsyncTask<Integer, Void, String>{
		//Elimina al Profesor que fue asigando a ese grado
		protected String doInBackground(Integer... ids){
			
			String url="GradoDao.php?key=3&&idProfesor="+ids[0]+"&&idNivel="+ids[1]+"&&idGrado="+ids[2];
			try {
				
				LocalDaoFactory local = new LocalDaoFactory();
				JSONObject jobj= local.crearConexionLocal(url);
				return jobj.getString("estado");
				
			} catch (Exception e) {
				return "Error de conexion";
			}
			
		}
		
		protected void onPostExecute(String result){
			
			if(result.equals("Grado Liberado")){
				new HandlerCargarGrados().execute(); 
    		}
    		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
