package com.cursojava.curso.Repository;

import com.cursojava.curso.Models.Inventario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends CrudRepository<Inventario, Integer> {

    }

