package uvigo.si.leagueoflegends.servicios;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uvigo.si.leagueoflegends.daos.EquipoDAO;
import uvigo.si.leagueoflegends.daos.JugadorDAO;
import uvigo.si.leagueoflegends.daos.PartidaDAO;
import uvigo.si.leagueoflegends.entidades.Equipo;
import uvigo.si.leagueoflegends.entidades.Jugador;
import uvigo.si.leagueoflegends.entidades.Partida;

@Service
public class JugadorServiceImplementation implements JugadorService{
	@Autowired
	JugadorDAO jugadorDao;
	
	@Autowired
	EquipoDAO equipoDao;
	@Autowired
	PartidaDAO partidaDao;
	
	@Override
	@Transactional
	public Jugador crear(Jugador entidad) {
		return jugadorDao.save(entidad);
	}

	@Override
	@Transactional
	public Jugador modificar(Jugador entidad) {
		return jugadorDao.save(entidad);
	}

	@Override
	@Transactional
	public void eliminar(Jugador entidad) {
		List<Equipo> equipos=equipoDao.findByJugador(entidad.getId());
		List<Long> partidas=new LinkedList<>();
		for(Equipo equipo : equipos) {
			
			Partida p=partidaDao.findByEquipo(equipo.getId());
			partidas.add(p.getId());
			
			//habilidadDao.deleteById(habilidad.getId());
		}
		
		//Elimino duplicados 
		List<Long> partidasSinDuplicar = new LinkedList<Long>(new HashSet<Long>(partidas));
		
		//elimino partidas
		for(Long id : partidasSinDuplicar) {
			
			partidaDao.deleteById(id);
		}
		jugadorDao.delete(entidad);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Jugador> buscarPorId(Long id) {
		return jugadorDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> buscarTodos() {
		return jugadorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> buscarPorCountry(String country) {
		return jugadorDao.findByCountry(country);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> buscarPorNickname(String nickname) {
		return jugadorDao.findByNickname(nickname);
	}
 
}
