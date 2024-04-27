package uvigo.si.leagueoflegends.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import uvigo.si.leagueoflegends.entidades.Habilidad;
import uvigo.si.leagueoflegends.entidades.Jugador;
import uvigo.si.leagueoflegends.servicios.JugadorService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(path="/api/jugador", produces="application/json")
public class JugadorController {
	
	 @Autowired
	 private JugadorService jugadorService;

	  @GetMapping
	  public ResponseEntity<List<Jugador>> getAllJugadores() {
		  try {
				List<Jugador> resultado = new ArrayList<>();

				resultado = jugadorService.buscarTodos();
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<Jugador> getJugadorById(@PathVariable("id") long id) {
		  try {
				

				Optional<Jugador> resultado= jugadorService.buscarPorId(id);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @PostMapping
	  public ResponseEntity<Jugador> createJugador(@RequestBody Jugador jugador) {
		  try {
				
				//VALIDAR ATRIBUTOS
				
			   Jugador nuevoJugador=this.jugadorService.crear(jugador);

				URI uri = crearURIJugador(nuevoJugador);

				return ResponseEntity.created(uri).body(nuevoJugador);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @GetMapping("/buscar")
	  public ResponseEntity<List<Jugador>> getJugadorByName(@RequestParam(name = "nickname", required = false)String name,
			  @RequestParam(name = "country", required = false)String country) {
		  try {
				List<Jugador> resultado = new ArrayList<>();

				if(name!=null) {
					resultado = jugadorService.buscarPorNickname(name);
				}else if (country!=null) {
					resultado = jugadorService.buscarPorCountry(country);
				}
				
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  /*@GetMapping("/buscar")
	  public ResponseEntity<List<Jugador>> getJuagdorByCountry(@RequestParam(name = "country", required = false)String country) {
		  try {
				List<Jugador> resultado = new ArrayList<>();

				resultado = jugadorService.buscarPorCountry(country);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }*/
  
	@PutMapping("/{id}")
	  public ResponseEntity<Jugador> updateJugador(@PathVariable("id") long id, @RequestBody  Jugador jugador) {
		Optional<Jugador> jugadorOptional = jugadorService.buscarPorId(id);

		if (jugadorOptional.isPresent()) {
			Jugador jugadorActualizado = jugadorService.modificar(jugador);
			return new ResponseEntity<>(jugadorActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteJugador(@PathVariable("id") long id) {
	   
		  try {
				Optional<Jugador> jugador = jugadorService.buscarPorId(id);
				if (jugador.isPresent()) {
					jugadorService.eliminar(jugador.get());
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  private URI crearURIJugador(Jugador nuevoJugador) {
			// TODO Auto-generated method stub
			return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevoJugador.getId()).toUri();
	  }
}
