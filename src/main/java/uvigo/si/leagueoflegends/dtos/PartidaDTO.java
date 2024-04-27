package uvigo.si.leagueoflegends.dtos;

import java.util.List;

import uvigo.si.leagueoflegends.entidades.*;

public class PartidaDTO {
	
	
	List<Participante> equipoAzul;
	List<Participante> equipoRojo;
	Servidor servidor;
	
	
	public PartidaDTO(Servidor servidor,List<Participante> equipoAzul, List<Participante> equipoRojo) {
		
		this.equipoAzul = equipoAzul;
		this.equipoRojo = equipoRojo;
		this.servidor=servidor;
	}
	public PartidaDTO() {
	
	}
	

	public Servidor getServidor() {
		return servidor;
	}
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	public List<Participante> getEquipoAzul() {
		return equipoAzul;
	}
	public void setEquipoAzul(List<Participante> equipoAzul) {
		this.equipoAzul = equipoAzul;
	}
	public List<Participante> getEquipoRojo() {
		return equipoRojo;
	}
	public void setEquipoRojo(List<Participante> equipoRojo) {
		this.equipoRojo = equipoRojo;
	}
	
	
	

}
