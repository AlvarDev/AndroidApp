package bean;

public class ListaGradosBean {

	int idNivel;
	String nivel;
	int idGrado;
	String grado;
	int idProfesor;
	String nombre;
	float sueldo;
	
	public ListaGradosBean() {
		idNivel=0;
		nivel="none";
		idGrado=0;
		grado="none";
		idProfesor=0;
		nombre="none";
		sueldo=0;
	}
	
	
	public int getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public int getIdGrado() {
		return idGrado;
	}
	public void setIdGrado(int idGrado) {
		this.idGrado = idGrado;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public int getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getSueldo() {
		return sueldo;
	}
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
	
	
	
	
}
