package uvigo.si.leagueoflegends.servicios;

import java.util.List;
import java.util.Optional;

import uvigo.si.leagueoflegends.entidades.Jugador;

public interface JugadorService {

	public Jugador crear(Jugador entidad);
	public Jugador modificar(Jugador entidad);
	public void eliminar(Jugador entidad);
	public Optional<Jugador> buscarPorId(Long id);
	public List<Jugador> buscarTodos();
	public List<Jugador> buscarPorCountry(String country);
	public List<Jugador> buscarPorNickname(String nickname);
}
