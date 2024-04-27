package uvigo.si.leagueoflegends.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uvigo.si.leagueoflegends.daos.ServidorDAO;
import uvigo.si.leagueoflegends.daos.PartidaDAO;
import uvigo.si.leagueoflegends.entidades.Servidor;
import uvigo.si.leagueoflegends.entidades.Partida;

@Service
public class ServidorServiceImplementation implements ServidorService{
	
	@Autowired
	ServidorDAO servidorDao;
	
	@Autowired
	PartidaDAO partidaDao;
	
	
	@Override
	@Transactional
	public Servidor crear(Servidor entidad) {
		return servidorDao.save(entidad);
	}

	@Override
	@Transactional
	public Servidor modificar(Servidor entidad) {
		return servidorDao.save(entidad);
	}

	@Override
	@Transactional
	public void eliminar(Servidor entidad) {
		
		List<Partida> partidas=partidaDao.findByServer(entidad.getId());
		
		for(Partida partida : partidas) {
			partidaDao.deleteById(partida.getId());
		}
		servidorDao.delete(entidad);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Servidor> buscarPorId(Long id) {
		return servidorDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servidor> buscarTodos() {
		return servidorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servidor> buscarPorRegion(String region) {
		return servidorDao.findByRegion(region);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servidor> buscarPorVersion(String version) {
		return servidorDao.findByVersion(version);
	}
 
}
