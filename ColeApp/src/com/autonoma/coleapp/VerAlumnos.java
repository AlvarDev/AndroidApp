package com.autonoma.coleapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import bean.AccesoBean;
import bean.AlumnoBean;
import da.factory.LocalDaoFactory;

public class VerAlumnos extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.autonoma.coleapp.MESSAGE";
		
	private Spinner nivel, grado;
	int idAlu;
	String mensaje;
	AlumnoBean alumnos[];
	
	public VerAlumnos() {
		mensaje="";
		idAlu=0;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_alumnos);
		// Show the Up button in the action bar.
		setupActionBar();
		mostrarPopUp();
		
		addItemsOnSpinnerNivel();
		addItemsOnSpinnerGrado();
		
		Intent intent = getIntent();
		mensaje = intent.getStringExtra(Login.EXTRA_MESSAGE);
		
		TextView msn = (TextView) findViewById(R.id.nomUser2);
		msn.append(" : "+mensaje);
		
		//new HandlerCargarAlumnos ().execute("22");
		
	}

	

	
	
	public void addItemsOnSpinnerNivel() {

		nivel = (Spinner) findViewById(R.id.nivelVer);
		List<String> list = new ArrayList<String>();
		list.add("Seleccione Nivel");
		list.add("Primaria");
		list.add("Secundaria");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		nivel.setAdapter(dataAdapter);
	}
	
	public void addItemsOnSpinnerGrado() {

		grado = (Spinner) findViewById(R.id.gradoVer);
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
	
	private void mostrarPopUp() {
		ListView lista = (ListView) findViewById(R.id.AlumnosListView);
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
	    @Override
		public void onItemClick(AdapterView<?>paret, View viewClicked, int position,long id) {
			// TODO Auto-generated method stub
	    	
	    	idAlu=position;
	    	CharSequence colors[] = new CharSequence[] {"Modificar Datos", "Eliminar Alumno"};

	    	AlertDialog.Builder builder = new AlertDialog.Builder(VerAlumnos.this);
	    	builder.setTitle("¿Que desea hacer?");
	    	builder.setItems(colors, new DialogInterface.OnClickListener() {
	    	    @Override
	    	    public void onClick(DialogInterface dialog, int which) {
	    	        // the user clicked on colors[which]
	    	    	switch (which) {
					case 0:
						Intent intent = new Intent(getApplicationContext(), ModificarAlumno.class);
						mensaje=alumnos[idAlu].getIdAlumno()+"";
						intent.putExtra(EXTRA_MESSAGE, mensaje);
						startActivity(intent);
						
						break;
					case 1: new HandlerElimnarAlumno().execute(alumnos[idAlu].getIdAlumno());
						
						break;	

					default:
						break;
					}
	    	    	
	    	    	
	    	    }
	    	});
	    	builder.show();
	    	
	    	
			//TextView textView = (TextView) viewClicked;
			//String msn="pos: "+position+" que es "+ textView.getText().toString();
			
			//Toast.makeText(VerAlumnos.this, msn, Toast.LENGTH_SHORT).show();
		}
		});
		
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
		getMenuInflater().inflate(R.menu.ver_alumnos, menu);
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
	
	
	
	public void mostrarLista(View view) {
		//new HandlerCargarAlumnos().execute("22");
		
		int idnivel= nivel.getSelectedItemPosition();
		int idgrado = grado.getSelectedItemPosition();
		
		
		if(idnivel!=0 && idgrado!=0){
		
			if((idnivel+idgrado)==8){
				Toast.makeText(getApplicationContext(), "No existe 6to de Secundaria " , Toast.LENGTH_SHORT).show();
			}else{
				new HandlerCargarAlumnos ().execute(idnivel,idgrado);
				
			}				
							
		}else{
			Toast.makeText(getApplicationContext(), "Seleccione Nivel y Grado", Toast.LENGTH_SHORT).show();
		}	
		
	}
	
	
	
	public class HandlerCargarAlumnos extends AsyncTask<Integer, Void, AlumnoBean> {

        protected AlumnoBean doInBackground(Integer... idAlumno){
        	
        	JSONObject obj;
        	
        	//String url="AlumnoDao.php?key=4&&idNivel=2&&idGrado=11";
        	String url="AlumnoDao.php?key=4&&idNivel="+idAlumno[0]+"&&idGrado="+idAlumno[1]+"";
        	
        	
        	
        	try{
                LocalDaoFactory local= new LocalDaoFactory();
                obj = local.crearConexionLocal(url);
                JSONArray array = obj.getJSONArray("ALUMNOS");
                alumnos= new AlumnoBean[array.length()];
                
                for(int i=0; i<array.length(); i++){
                JSONObject jObj = array.getJSONObject(i);
                AlumnoBean alumno = new AlumnoBean();
                alumno.setIdAlumno(Integer.parseInt(jObj.getString("idAlumno")));
                alumno.setNom(jObj.getString("nom"));
                alumno.setApe(jObj.getString("ape"));
                alumno.setFechaNac(jObj.getString("fechNac"));
                alumnos[i]=alumno;
                }
            }catch(Exception e){
            	Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        		
            }

        	return null;
        	
        }

        protected void onPostExecute(AlumnoBean alumno) {
        		
        	//Create list of items
        	String listaAlumnos[]= new String[alumnos.length];
        	for(int i=0; i< listaAlumnos.length; i++){
        		listaAlumnos[i]="id: "+alumnos[i].getIdAlumno()+" Nombre: "+alumnos[i].getNom()+"      Apellido: "+alumnos[i].getApe()+" \nFecha Nac.: "+alumnos[i].getFechaNac();
        	}
        	
        	//Build Adapter
        	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.alumnos, listaAlumnos);
        	
        	//Configure the list view
        	
        	ListView lista = (ListView) findViewById(R.id.AlumnosListView);
        	lista.setAdapter(adapter);
        	
        }


    }
	


	public class HandlerElimnarAlumno extends AsyncTask<Integer, Void, String>{
		
		protected String doInBackground(Integer... idAlumno){
			
			String url="AlumnoDao.php?key=3&&idAlumno="+idAlumno[0];
			try {
				
				LocalDaoFactory local = new LocalDaoFactory();
				JSONObject jobj= local.crearConexionLocal(url);
				return jobj.getString("estado");
				
			} catch (Exception e) {
				return "Error de conexion";
			}
			
		}
		
		protected void onPostExecute(String result){
			
			if(result.equals("Alumno Eliminado")){
				int idnivel= nivel.getSelectedItemPosition();
				int idgrado = grado.getSelectedItemPosition();
				new HandlerCargarAlumnos ().execute(idnivel,idgrado);
    		}
    		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
