package mx.org.angular.react.practicas.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * The persistent class for the obras_cargas_poa database table.
 * 
 */
/**
 * 
 */
@Entity
@Table(name="tema")
public class TemaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String descripcion;

	private int estatus;
	

	public TemaEntity() {
	
	}
	
	

	public TemaEntity(String descripcion, int estatus) {
		this.descripcion = descripcion;
		this.estatus = estatus;
	}



	public TemaEntity(Long id) {
		this.id = id;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "RolEntity [id=" + id + ", descripcion=" + descripcion + ", estatus=" + estatus + "]";
	}
	

		
	

}