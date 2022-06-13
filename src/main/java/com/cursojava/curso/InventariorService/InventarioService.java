package com.cursojava.curso.InventariorService;

import com.cursojava.curso.Models.Inventario;
import com.cursojava.curso.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public Inventario insertar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }
    public Inventario actualizar (Inventario inventario){
        return inventarioRepository.save(inventario);
    }
    public List<Inventario> listar(){
        return (List<Inventario>) inventarioRepository.findAll();
    }
    public void eliminar (Inventario inventario){
        inventarioRepository.delete(inventario);
    }
}


