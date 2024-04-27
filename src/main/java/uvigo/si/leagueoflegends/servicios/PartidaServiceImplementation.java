package uvigo.si.leagueoflegends.servicios;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uvigo.si.leagueoflegends.daos.*;
import uvigo.si.leagueoflegends.dtos.*;
import uvigo.si.leagueoflegends.entidades.*;

@Service
public class PartidaServiceImplementation implements PartidaService{
	
	@Autowired
	PartidaDAO partidaDao;
	
	@Autowired
	ParticipanteDAO participanteDAO;
	
	@Autowired
	EquipoDAO equipoDAO;

	@Override
	@Transactional
	public Partida crear(PartidaDTO entidad) {
	
		List<Participante> equipoRojo=entidad.getEquipoRojo();
		
		//necesito esta lista ya que la anterior tenia el ID a nulo
		List<Participante> equipoRojoCompleto=new LinkedList<>();
		for(Participante participante : equipoRojo) {
			Participante x=participanteDAO.save(participante);
			equipoRojoCompleto.add(x);
		}
		
		Equipo nuevoRojo=new Equipo("rojo",equipoRojoCompleto);
		nuevoRojo=equipoDAO.save(nuevoRojo);
		
		List<Participante> equipoAzul=entidad.getEquipoAzul();
		
		List<Participante> equipoAzulCompleto=new LinkedList<>();
		
		for(Participante participante : equipoAzul) {
			Participante x=participanteDAO.save(participante);
			equipoAzulCompleto.add(x);
		}
		

		Equipo nuevoAzul=new Equipo("azul",equipoAzulCompleto);
		nuevoAzul=equipoDAO.save(nuevoAzul);
		
		Servidor servidor=entidad.getServidor();
		
		Partida toAdd=new Partida(servidor,nuevoRojo,nuevoAzul);
			
		return partidaDao.save(toAdd);
	}
	@Override
	@Transactional
	public Partida modificar(Partida entidad) {
		return partidaDao.save(entidad);
	}
	
	@Override
	@Transactional
	public void eliminar(Partida entidad) {
		partidaDao.delete(entidad);
	}
	@Override
	@Transactional
	public Optional<Partida> buscarPorId(Long id){
		return partidaDao.findById(id);
	}
	@Override
	@Transactional
	public List<Partida> buscarTodos(){
		return partidaDao.findAll();
	}

	@Override
	@Transactional
	public List<Partida> buscarPorServidor(long idServidor){
		return partidaDao.findByServer(idServidor);
	}


}
