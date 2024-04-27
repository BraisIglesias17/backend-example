package uvigo.si.leagueoflegends.servicios;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uvigo.si.leagueoflegends.daos.CampeonDAO;
import uvigo.si.leagueoflegends.daos.HabilidadDAO;
import uvigo.si.leagueoflegends.daos.*;
import uvigo.si.leagueoflegends.entidades.Campeon;
import uvigo.si.leagueoflegends.entidades.*;

@Service
public class CampeonServiceImplementation implements CampeonService {
	@Autowired
	CampeonDAO campeonDao;
	@Autowired
	HabilidadDAO habilidadDao;
	@Autowired
	EquipoDAO equipoDao;
	@Autowired
	PartidaDAO partidaDao;
	
	@Override
	@Transactional
	public Campeon crear(Campeon entidad) {
		return campeonDao.save(entidad);
	}

	@Override
	@Transactional
	public Campeon modificar(Campeon entidad) {
		return campeonDao.save(entidad);
	}

	@Override
	@Transactional
	public void eliminar(Campeon entidad) {
		
		//elimino primero todas las habilidades
		if(entidad != null) {
			List<Habilidad> habilidades=habilidadDao.findByCampeonId(entidad.getId());
			for(Habilidad habilidad : habilidades) {
				habilidadDao.deleteById(habilidad.getId());
			}
			
			//Recupero los equipos a los que pertenecio este campeon para obtener posteriormente su partida
			List<Equipo> equipos=equipoDao.findByCampeon(entidad.getId());
			List<Long> partidas=new LinkedList<>();
			//Obtengo las partidas en las que participo
			for(Equipo equipo : equipos) {
				
				Partida p=partidaDao.findByEquipo(equipo.getId());
				partidas.add(p.getId());
			}
			
			//Elimino duplicados 
			List<Long> partidasSinDuplicar = new LinkedList<Long>(new HashSet<Long>(partidas));
			
			//elimino partidas
			for(Long id : partidasSinDuplicar) {
				
				partidaDao.deleteById(id);
			}
			//elimino las partidas asociadas a este campeon
			campeonDao.delete(entidad);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Campeon> buscarPorId(Long id) {
		return campeonDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Campeon> buscarTodos() {
		return campeonDao.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Campeon> buscarPorName(String nickname) {
		return campeonDao.findByName(nickname);
	}
 
}
