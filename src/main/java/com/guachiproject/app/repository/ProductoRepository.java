package com.guachiproject.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guachiproject.app.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
