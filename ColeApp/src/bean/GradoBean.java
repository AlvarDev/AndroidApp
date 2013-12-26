package bean;

public class GradoBean {
	
	int idNivel;
	int idGrado;
	String descripcion;
	
	public GradoBean() {
		idNivel=0;
		idGrado=0;
		descripcion="";
	}

	public int getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
	}

	public int getIdGrado() {
		return idGrado;
	}

	public void setIdGrado(int idGrado) {
		this.idGrado = idGrado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	
}
