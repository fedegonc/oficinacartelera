package com.example.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
public class MainController {

    private Map<String, Map<String, Object>> servicios;
    
    @PostConstruct
    public void init() {
        try {
            // Cargar el archivo JSON
            Resource resource = new ClassPathResource("static/data/servicios.json");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Map<String, Map<String, Object>>> data = mapper.readValue(resource.getInputStream(), new TypeReference<Map<String, Map<String, Map<String, Object>>>>() {});
            this.servicios = data.get("servicios");
        } catch (IOException e) {
            // Si hay error al cargar el JSON, inicializar un mapa vacío
            this.servicios = new HashMap<>();
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        try {
            Resource resource = new ClassPathResource("static/data/banner_principal.json");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, List<Map<String, String>>> data = mapper.readValue(resource.getInputStream(), new TypeReference<Map<String, List<Map<String, String>>>>() {});
            List<Map<String, String>> banners = data.get("banners");
            model.addAttribute("banners", banners);
            // Para compatibilidad con el template actual, también envío el primero
            if (banners != null && !banners.isEmpty()) {
                model.addAttribute("bannerTitulo", banners.get(0).get("titulo"));
                model.addAttribute("bannerDescripcion", banners.get(0).get("descripcion"));
            }
        } catch (IOException e) {
            model.addAttribute("banners", null);
            model.addAttribute("bannerTitulo", "SOLUCIONES VISUALES INTEGRALES");
            model.addAttribute("bannerDescripcion", "Desde letras corpóreas, vinilos impresos y murales hasta demarcaciones de canchas y trabajos en altura. Transformamos espacios con soluciones visuales creativas y de alto impacto.");
        }
        return "home";
    }
    
    @GetMapping("/publicidad-diseno")
    public String publicidadDiseno() {
        return "fragments/publicidad-diseno :: publicidad-diseno";
    }
    
    
    @GetMapping("/creatividad")
    public String creatividad() {
        return "fragments/creatividad :: creatividad";
    }
    
    @GetMapping("/servicio/{id}")
    public String detalleServicio(@PathVariable("id") String id, Model model) {
        Map<String, Object> servicio = servicios.get(id);
        
        if (servicio == null) {
            return "redirect:/";
        }
        
        model.addAttribute("id", servicio.get("id"));
        model.addAttribute("nombre", servicio.get("nombre"));
        model.addAttribute("descripcion", servicio.get("descripcion"));
        model.addAttribute("caracteristicas", servicio.get("caracteristicas"));
        model.addAttribute("imagenes", servicio.get("imagenes"));
        
        return "fragments/servicio-detalle :: servicio-detalle";
    }
}
