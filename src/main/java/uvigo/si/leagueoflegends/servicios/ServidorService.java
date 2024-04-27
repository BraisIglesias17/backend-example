package uvigo.si.leagueoflegends.servicios;

import java.util.List;
import java.util.Optional;



import uvigo.si.leagueoflegends.entidades.*;


public interface ServidorService {

	public Servidor crear(Servidor almacen);
	public Servidor modificar(Servidor almacen);
	public void eliminar(Servidor almacen);
	public Optional<Servidor> buscarPorId(Long id);
	public List<Servidor> buscarTodos();
	public List<Servidor> buscarPorRegion(String region);
	public List<Servidor> buscarPorVersion(String region);


}
