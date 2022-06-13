package com.cursojava.curso.controllers;

import com.cursojava.curso.InventariorService.InventarioService;
import com.cursojava.curso.Models.Inventario;
import com.cursojava.curso.Models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
       @RequestMapping(value = "/Inventario")
       public class InventarioControllers {

        @Autowired
        private InventarioService inventarioService;
        @GetMapping(value = "/listar", consumes = MediaType.APPLICATION_JSON_VALUE)
        public List<Inventario> listar(){
            List<Inventario> recorer = inventarioService.listar();
            for (Inventario i: recorer) {
                RestTemplate restTemplate = new RestTemplate();
                RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
                restTemplate = restTemplateBuilder.build();
                // objeto de donde va a ser url ,objeto en este caso JsonAJava
                List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
                MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
                messageConverters.add(converter);
                restTemplate.setMessageConverters(messageConverters);
                    Response response = restTemplate.getForObject("https://itunes.apple.com/search?term=" + i.getMarca(), Response.class);
                    if (response.getResults().size() > 0) {
                        String primerElemento = response.getResults().get(0).getArtistViewUrl();
                        i.setUrl(primerElemento);
                        if (primerElemento == null) {
                            String segundoElemento = response.getResults().get(0).getTrackViewUrl();
                            i.setUrl(segundoElemento);
                        } else {
                            i.setUrl(primerElemento);
                        }
                    } else {
                        i.setUrl("Resultados :" + response.getResults().size());
                    }
            }
            return recorer;
        }
        @PostMapping(value = "/insertar", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Object> insertar(@RequestBody Inventario inventario){
            return ResponseEntity.ok(this.inventarioService.insertar(inventario));
        }
        @PutMapping("/actualizar")
        public Inventario actualizar(@RequestBody Inventario inventario){
            return inventarioService.actualizar(inventario);
        }
        @DeleteMapping("/eliminar")
        public void eliminar(@RequestBody Inventario inventario){
            inventarioService.eliminar(inventario);
        }

        @GetMapping("/filtrar")
        public List<Inventario> filtrar(@RequestParam (required = false, name = "marca") String marca, @RequestParam (required = false, name = "memoriaRam") String memoriaRam) {
            List<Inventario> filtro =  inventarioService.listar().stream().filter(x ->x.getMemoriaRam().equalsIgnoreCase(memoriaRam) || x.getMarca().equalsIgnoreCase(marca)).collect(Collectors.toList());
            for (Inventario a : filtro ) {
                RestTemplate restTemplate = new RestTemplate();
                RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
                restTemplate = restTemplateBuilder.build();
                // objeto de donde va a ser url ,objeto en este caso JsonAJava
                List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
                MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
                messageConverters.add(converter);
                restTemplate.setMessageConverters(messageConverters);
                Response response = restTemplate.getForObject("https://itunes.apple.com/search?term=" + a.getMarca(), Response.class);
                String primerElemento;
                if (response.getResults().size() > 0) {
                    primerElemento = response.getResults().get(0).getArtistViewUrl();
                    a.setUrl(primerElemento);
                } else {
                    a.setUrl("Resultados :" + response.getResults().size());
                }
            }
            return filtro;
        }




    //return users.stream().filter(x -> x.getCity().equals(x)).collect(Collectors.toList());
       //filter en este caso se filtra toda la informacion por ciudad de mi lista pero tambien se pueden otras cosas
       //estudiar stream en java
}
   /* public List<Inventario> listar(){
        List<Inventario> recorer = inventarioRepository.listar();
        for (Inventario i: recorer ){
            i.setUrl("https://itunes.apple.com/search?term="+i.getMarca());
        }
        return recorer;

        //itunes;https://itunes.apple.com/s;earch?term=lenovo
        // return inventarioRepository.listar().stream().map(computador -> computador.setUrl("name")).collect(Collectors.toList());

          for (Inventario i: recorer ){
                i.setUrl("https://itunes.apple.com/search?term="+i.getMarca());
            }
    }*/