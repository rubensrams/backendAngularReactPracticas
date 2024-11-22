package mx.org.angular.react.practicas.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


/**
 * The persistent class for the obras_cargas_poa database table.
 * 
 */
/**
 * 
 */
@Entity
@Table(name="usuario")
public class UsuarioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	

	@Column(name="usuario", unique = true)
	private String usuario;

	private String password;
	
	private String nombre;
	
	private String paterno;
	
	private String materno;
	
	private int estatus;

	private String foto;
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_rol",
	joinColumns = @JoinColumn(name="id_usuario"),
	inverseJoinColumns = @JoinColumn(name="id_rol"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"id_usuario", "id_rol"})}) 
	private List<RolEntity> roles;
	
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sexo", nullable = false, insertable = true, updatable = true)
    private SexoEntity sexo;

	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_edo_civil", nullable = false, insertable = true, updatable = true)
    private EstadoCivilEntity estadoCivil;

	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name="id_usuario") private List<AvisosEntity> avisos;
	 */


	public UsuarioEntity() {

	}
	
	
	public UsuarioEntity(Long id) {
		this.id= id;
	}


	public UsuarioEntity(String usuario, String password, String nombre, String paterno, String materno, int estatus,
			String foto, List<RolEntity> roles, SexoEntity sexo, EstadoCivilEntity estadoCivil) {
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.paterno = paterno;
		this.materno = materno;
		this.estatus = estatus;
		this.foto = foto;
		this.roles = roles;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUusario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPaterno() {
		return paterno;
	}


	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}


	public String getMaterno() {
		return materno;
	}


	public void setMaterno(String materno) {
		this.materno = materno;
	}


	public int getEstatus() {
		return estatus;
	}


	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}



	public List<RolEntity> getRoles() {
		return roles;
	}


	public void setRoles(List<RolEntity> roles) {
		this.roles = roles;
	}


	public SexoEntity getSexo() {
		return sexo;
	}


	public void setSexo(SexoEntity sexo) {
		this.sexo = sexo;
	}

	
	public EstadoCivilEntity getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(EstadoCivilEntity estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	
	/*
	 * public List<AvisosEntity> getAvisos() { return avisos; }
	 * 
	 * 
	 * public void setAvisos(List<AvisosEntity> avisos) { this.avisos = avisos; }
	 */
	

}