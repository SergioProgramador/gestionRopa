package com.finalproject.backend.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.backend.entity.Productos;
import com.finalproject.backend.service.ProductosService;

@RestController
@RequestMapping("/productos")
public class ProductosController {
	
	@Autowired
	@Qualifier("productosService")
	private ProductosService productosService;
	
	//LISTAR TODOS LOS PRODUCTOS
	@CrossOrigin
	@GetMapping("/showproductos")
    public List<Productos> showProductos(){
        return productosService.listAllProductos();
    }
	
	//LISTA UN PRODUCTO
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<?> oneProducto(@PathVariable(name = "id") int id) {

		String id_str = String.valueOf(id);
		Productos productos = null;
		Map<String, Object> response = new HashMap<>();

		try {
			productos = productosService.findProductosById(id);
		} catch (DataAccessException e) {
			response.put("info", "Error al realizar la consulta.");
			response.put("e", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (productos == null) {
			response.put("info", "El producto ID: ".concat(id_str.concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Productos>(productos, HttpStatus.OK);
	}
	
	//BORRA UN PRODUCTO
	@CrossOrigin
	@DeleteMapping("/removeProducto/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable("id") int id) {
	
		Map<String, Object> response = new HashMap<>();
	
		try {
			Productos producto = productosService.findProductosById(id);
			String nombreImagenAnterior = producto.getImagen();
			
			if(nombreImagenAnterior != null && nombreImagenAnterior.length() > 0) {
				Path rutaImagenAnterior = Paths.get("C:\\Users\\Sergio\\Desktop\\gestionRopa\\backend\\src\\main\\resources\\multimedia").resolve(nombreImagenAnterior);
				File fileAnterior = rutaImagenAnterior.toFile();
				if(fileAnterior.exists() && fileAnterior.canRead()) {
					fileAnterior.delete();
				}
			}			
			productosService.removeProductos(id);			
		}catch(DataAccessException e) {
			response.put("info", "Error al eliminar el producto.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		response.put("info", "El producto se ha eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	//CREA UN PRODUCTO
	@CrossOrigin
	@PostMapping("/addProducto")
	public ResponseEntity<?> addProducto(@RequestBody @Valid Productos productos) {

		Productos producto = null;
		Map<String, Object> response = new HashMap<>();

		try {
			producto = productosService.addProductos(productos);
		} catch (DataAccessException e) {
			response.put("info", "Error al añadir el producto, intentalo de nuevo.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("info", "Se ha añadido el producto correctamente.");
		response.put("producto", producto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//ACTUALIZA UN PRODUCTO
	@CrossOrigin
	@PutMapping("/updateProducto/{id}")
	public ResponseEntity<?> updateProducto(@PathVariable("id") int id, @RequestBody @Valid Productos productos) {
		
		String id_str = String.valueOf(id);
		Productos currentProducto = productosService.findProductosById(id);		
		Map<String, Object> response = new HashMap<>();		
		Productos productoUpdated = null;
		
		if(currentProducto == null) {
			response.put("info", "Error: no se puedo editar, el producto ID:".concat(id_str.concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentProducto.setNombre(productos.getNombre());
			currentProducto.setDescripcion(productos.getDescripcion());
			currentProducto.setTalla(productos.getTalla());
			currentProducto.setMaterial(productos.getMaterial());
			currentProducto.setPrecio(productos.getPrecio());
			currentProducto.setColor(productos.getColor());
			currentProducto.setStock(productos.getStock());
			currentProducto.setImagen(productos.getImagen());
			currentProducto.setCreate_time(productos.getCreate_time());
			currentProducto.setUpdate_time(productos.getUpdate_time());
			
			productoUpdated = productosService.addProductos(currentProducto);
			
		}catch(DataAccessException e) {
			response.put("info", "Error al actualizar el producto.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("info", "Se ha actualizado el producto correctamente.");
		response.put("producto", productoUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//METODO PARA SUBIR UNA IMAGEN
	@PostMapping("/uploadImagen")
	@CrossOrigin
	public ResponseEntity<?> uploadImagen(@RequestParam(name="file") MultipartFile file, @RequestParam(name="id") Long id){
		
		Map<String, Object> response = new HashMap<>();	
		
		int id_int = id.intValue();
		Productos producto = productosService.findProductosById(id_int);
		
		if(!file.isEmpty()) {
			String nombreFile = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
			//OBTENEMOS LA RUTA DONDE SE VA A UARDAR LOS FILES SUBIDOS, Y LO "CONCATENAMOS" CON EL NOMBRE DEL ARCHIVO QUE VAMOS A SUBIR Y TENDRIAMOS LA RUTA ABSOLUTA
			Path ruta = Paths.get("C:\\Users\\Sergio Gil Andamoyo\\Desktop\\gestionRopa\\backend\\src\\main\\resources\\multimedia").resolve(nombreFile).toAbsolutePath();
			try {
				Files.copy(file.getInputStream(), ruta);
			} catch (IOException e) {
				response.put("info", "Error al subir la image: "+ nombreFile);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreImagenAnterior = producto.getImagen();
			
			if(nombreImagenAnterior != null && nombreImagenAnterior.length() > 0) {
				Path rutaImagenAnterior = Paths.get("C:\\Users\\Sergio Gil Andamoyo\\Desktop\\gestionRopa\\backend\\src\\main\\resources\\multimedia").resolve(nombreImagenAnterior);
				File fileAnterior = rutaImagenAnterior.toFile();
				if(fileAnterior.exists() && fileAnterior.canRead()) {
					fileAnterior.delete();
				}
			}
			
			//SI SE SUBE LA FOTO CORRECTAMENTE, SETEAMOS EL CAMPO IMAGEN EN PRODUCTO
			producto.setImagen(nombreFile);
			productosService.addProductos(producto);
			
			response.put("producto", producto);
			response.put("info", "La imagen " + nombreFile + " se ha subido correctamente");
			 
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);		
	}
	
	//METODO PARA VER LA IMAGEN
	//:.+ --> INDICA QUE ESE PARAMETRO VA A TENER UN PUNTO Y UNA EXTENSION
	@GetMapping("/uploadImagen/{nombreImagen:.+}")
	@CrossOrigin
	public ResponseEntity<Resource> seeImagen(@PathVariable String nombreImagen){
		
		Path ruta = Paths.get("C:\\Users\\Sergio Gil Andamoyo\\Desktop\\gestionRopa\\backend\\src\\main\\resources\\multimedia").resolve(nombreImagen).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(ruta.toUri()); 	
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error la imagen no se pudo cargar: "+ nombreImagen);
		}
		
		//PASAMOS LAS CABECERAS PARA FORZAR A LA IMAGEN PARA QUE SE PUEDA DESCARGAR  	
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
		

}
