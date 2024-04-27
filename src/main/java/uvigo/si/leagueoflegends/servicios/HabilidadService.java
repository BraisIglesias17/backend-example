package uvigo.si.leagueoflegends.servicios;

import java.util.List;
import java.util.Optional;

import uvigo.si.leagueoflegends.entidades.Habilidad;

public interface HabilidadService {

	public Habilidad crear(Habilidad entidad);
	public Habilidad modificar(Habilidad entidad);
	public void eliminar(Habilidad entidad);
	public Optional<Habilidad> buscarPorId(Long id);
	public List<Habilidad> buscarTodos();
	public List<Habilidad> buscarPorName(String nickname);
	public List<Habilidad> buscarPorCampeon(Long idcampeon);
	
}
