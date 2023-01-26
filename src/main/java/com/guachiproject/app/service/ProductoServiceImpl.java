package com.guachiproject.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.guachiproject.app.entity.Producto;
import com.guachiproject.app.repository.ProductoRepository;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Integer> implements ProductoService{
	
	@Autowired
	ProductoRepository productoRepository;
	@Override
	public CrudRepository<Producto, Integer> getDao() {
		// TODO Auto-generated method stub
		return productoRepository;
	}

}
