package uvigo.si.leagueoflegends.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import uvigo.si.leagueoflegends.entidades.Jugador;
import uvigo.si.leagueoflegends.entidades.Servidor;
import uvigo.si.leagueoflegends.servicios.ServidorService;

import java.net.URI;
import java.util.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(path="/api/servidor", produces="application/json")
public class ServidorController {

	 @Autowired
	 private ServidorService servidorService;

	  @GetMapping
	  public ResponseEntity<List<Servidor>> getAllServidores() {
		  try {
				List<Servidor> resultado = new ArrayList<>();

				resultado = servidorService.buscarTodos();
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<Servidor> getServidorById(@PathVariable("id") long id) {
		  try {
				

				Optional<Servidor> resultado= servidorService.buscarPorId(id);
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

	  @GetMapping("/buscar")
	  public ResponseEntity<List<Servidor>> getServidorByName(@RequestParam(name = "region", required = false)String region,
			  @RequestParam(name = "version", required = false)String version) {
		  try {
				List<Servidor> resultado = new ArrayList<>();

				if(version!=null) {
					resultado = servidorService.buscarPorVersion(version);
				}else if (region!=null) {
					resultado = servidorService.buscarPorRegion(region);
				}
				
				
				if (resultado.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(resultado, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  @PostMapping
	  public ResponseEntity<Servidor> createServidor(@RequestBody Servidor servidor) {
		  try {
				
				//VALIDAR ATRIBUTOS
				
				Servidor nuevoServidor=this.servidorService.crear(servidor);

				URI uri = crearURIServidor(nuevoServidor);

				return ResponseEntity.created(uri).body(nuevoServidor);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }

  
	@PutMapping("/{id}")
	  public ResponseEntity<Servidor> updateServidor(@PathVariable("id") long id, @RequestBody  Servidor servidor) {
		Optional<Servidor> servidorOptional = servidorService.buscarPorId(id);

		if (servidorOptional.isPresent()) {
			Servidor servidorActualizado = servidorService.modificar(servidor);
			return new ResponseEntity<>(servidorActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteServidor(@PathVariable("id") long id) {
	   
		  try {
				Optional<Servidor> servidor = servidorService.buscarPorId(id);
				if (servidor.isPresent()) {
					servidorService.eliminar(servidor.get());
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	  
	  private URI crearURIServidor(Servidor nuevoServidor) {
			// TODO Auto-generated method stub
			return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevoServidor.getId()).toUri();
	  }
	

}
