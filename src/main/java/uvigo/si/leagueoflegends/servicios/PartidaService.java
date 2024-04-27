package uvigo.si.leagueoflegends.servicios;

import java.util.List;
import java.util.Optional;

import uvigo.si.leagueoflegends.dtos.PartidaDTO;
import uvigo.si.leagueoflegends.entidades.*;

public interface PartidaService {
	public Partida crear(PartidaDTO entidad);
	public Partida modificar(Partida entidad);
	public void eliminar(Partida entidad);
	public Optional<Partida> buscarPorId(Long id);
	public List<Partida> buscarTodos();
	
	public List<Partida> buscarPorServidor(long idServidor);
}

