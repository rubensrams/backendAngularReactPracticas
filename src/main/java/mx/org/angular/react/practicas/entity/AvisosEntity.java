package mx.org.angular.react.practicas.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the obras_cargas_poa database table.
 * 
 */
/**
 * 
 */
@Entity
@Table(name="avisos")
public class AvisosEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String descripcion;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;


	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tema", nullable = false, insertable = true, updatable = true)
    private TemaEntity tema;


	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false, insertable = true, updatable = true)
    private UsuarioEntity usuario;

	public AvisosEntity() {
	
		
	}
	
	

	public AvisosEntity(String descripcion, Date fecha, TemaEntity tema, UsuarioEntity usuario) {
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.tema = tema;
		this.usuario = usuario;
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



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public TemaEntity getTema() {
		return tema;
	}



	public void setTema(TemaEntity tema) {
		this.tema = tema;
	}



	public UsuarioEntity getUsuario() {
		return usuario;
	}



	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}



	
		
	

}