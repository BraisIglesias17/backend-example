package uvigo.si.leagueoflegends.servicios;

import java.util.List;
import java.util.Optional;

import uvigo.si.leagueoflegends.entidades.Campeon;

public interface CampeonService {
	public Campeon crear(Campeon entidad);
	public Campeon modificar(Campeon entidad);
	public void eliminar(Campeon entidad);
	public Optional<Campeon> buscarPorId(Long id);
	public List<Campeon> buscarTodos();
	public List<Campeon> buscarPorName(String nickname);
}
