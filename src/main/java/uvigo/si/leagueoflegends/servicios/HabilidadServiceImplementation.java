package uvigo.si.leagueoflegends.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uvigo.si.leagueoflegends.daos.CampeonDAO;
import uvigo.si.leagueoflegends.daos.HabilidadDAO;
import uvigo.si.leagueoflegends.entidades.Habilidad;
import uvigo.si.leagueoflegends.entidades.Campeon;

@Service
public class HabilidadServiceImplementation implements HabilidadService {

	@Autowired
	HabilidadDAO habilidadDao;
	
	@Autowired
	CampeonDAO campeonDao;
	
	public Habilidad crear(Habilidad entidad) {
		return habilidadDao.save(entidad);
	}
	public Habilidad modificar(Habilidad entidad) {
		return habilidadDao.save(entidad);
	}
	public void eliminar(Habilidad entidad) {
		habilidadDao.delete(entidad);
	}
	public Optional<Habilidad> buscarPorId(Long id){
		return habilidadDao.findById(id);
	}
	
	public List<Habilidad> buscarTodos(){
		return habilidadDao.findAll();
	}
	public List<Habilidad> buscarPorName(String name){
		return habilidadDao.findByName(name);
	}
	public List<Habilidad> buscarPorCampeon(Long idcampeon){
		
		return habilidadDao.findByCampeonId(idcampeon);
		/*
			Optional<Campeon> optional=campeonDao.findById(idcampeon);
		
			Campeon campeon=optional.get();
			
			return campeon.getHability();
		*/
				
	}
}
