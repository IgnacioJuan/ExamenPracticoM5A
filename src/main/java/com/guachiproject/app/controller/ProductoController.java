package com.guachiproject.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guachiproject.app.entity.Producto;
import com.guachiproject.app.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	@Autowired
	ProductoService productoService;

	@GetMapping("/listar")
	public ResponseEntity<List<Producto>> obtenerLista() {
		return new ResponseEntity<>(productoService.findByAll(), HttpStatus.OK);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Producto> buscar(@PathVariable Integer id) {
		if (productoService.findById(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(productoService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/crear")
	public ResponseEntity<Producto> crear(@Valid @RequestBody Producto c) {
		if(c.getPrecio()<=0 || c.getCantidad()<=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		c.setPrecio(Math.round(c.getPrecio()* 100.0 )/ 100.0);
		c.setValorCompra(Math.round((c.getCantidad() * c.getPrecio())*1000.0)/1000.0);
		
		if(c.getValorCompra()>50) {
			c.setTotal(c.getValorCompra()-(c.getValorCompra()*0.10));
				
			c.setTotal(Math.round(((c.getTotal()*1.12)* 1000.0 ))/ 1000.0);
		}else {
			c.setTotal(Math.round((c.getValorCompra()*1.12)* 1000.0 )/ 1000.0);
		}
		
		Producto asig = productoService.save(c);
		return new ResponseEntity<>(asig, HttpStatus.CREATED);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Producto> eliminar(@PathVariable Integer id) {
		productoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Producto> actualizarUsuario(@PathVariable Integer id, @RequestBody Producto c) {
		Producto producto = productoService.findById(id);
		if (producto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				producto.setDescripcion(c.getDescripcion());
				producto.setCantidad(c.getCantidad());
				producto.setPrecio(Math.round(c.getPrecio() * 100.0 )/ 100.0);
				if(c.getPrecio()<=0 || c.getCantidad()<=0) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				producto.setValorCompra(Math.round((c.getCantidad() * c.getPrecio())*100.0)/100.0);
				if(producto.getValorCompra()>50) {
					producto.setTotal(producto.getValorCompra()-(producto.getValorCompra()*0.10));
						
					producto.setTotal(Math.round(((producto.getTotal()*1.12)* 100.0 ))/ 100.0);
				}else {
					producto.setTotal(Math.round((producto.getValorCompra()*1.12)*100.0)/100.0);
				}
				return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

	}
}
