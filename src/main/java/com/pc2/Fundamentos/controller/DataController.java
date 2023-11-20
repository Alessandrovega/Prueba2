package com.pc2.Fundamentos.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pc2.Fundamentos.entity.Aliados;
import com.pc2.Fundamentos.entity.Productos;
import com.pc2.Fundamentos.entity.Query;
import com.pc2.Fundamentos.util.HttpUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DataController {

    private final ObjectMapper objectMapper;


    private final HttpUtil httpUtil;

    public DataController(ObjectMapper objectMapper, HttpUtil httpUtil) {
        this.objectMapper = objectMapper;
        this.httpUtil = httpUtil;


    }

    @PostMapping("/query")
    public ResponseEntity<String> postQuery(@RequestBody Query query) {
        // Combinar la URL y el código
        String urlCompleta = query.getUrl() + "/" + query.getCode();

        // Realizar la solicitud GET utilizando HttpUtil
        ResponseEntity<String> response = realizarGET(urlCompleta);

        // Verificar el resultado de la solicitud GET
        if (response.getStatusCode().is2xxSuccessful()) {
            if(urlCompleta.contains("productos")){

                List<Productos> productos = obtenerProductosDeRespuesta(response.getBody());
                if (productos != null && !productos.isEmpty()) {
                    // Devolver el primer objeto Productos (asumiendo que es una lista)
                    return ResponseEntity.ok(crearTablaHTMLProductos((productos)));
                } else {
                    return ResponseEntity.badRequest().body("No se pudo convertir la respuesta a un objeto Productos");
                }

            }
            else{
                List<Aliados> aliados = obtenerAliadosDeRespuesta(response.getBody());
                if (aliados != null && !aliados.isEmpty()) {
                    // Devolver el primer objeto Aliados (asumiendo que es una lista)
                    return ResponseEntity.ok(crearTablaHTML(aliados));
                }
                else {
                    return ResponseEntity.badRequest().body("No se pudo convertir la respuesta a un objeto Aliados");
                }
            }
        } else {
            // La solicitud GET falló
            return ResponseEntity.badRequest().body("Error en la solicitud GET. Código: " + response.getStatusCodeValue());
        }
    }

    public ResponseEntity<String> realizarGET(String url) {
        // Utilizar HttpUtil para realizar la solicitud GET
        return httpUtil.realizarGET(url);
    }

    private String crearTablaHTML(List<Aliados> aliados) {
        StringBuilder html = new StringBuilder();

        // Crear la estructura de la tabla HTML
        html.append("<table>");
        html.append("<tr><th>Apertura</th><th>Categoria</th><th>Codigo</th><th>Negocio</th></tr>");

        // Llenar la tabla con los datos de Aliados
        for (Aliados aliado : aliados) {
            html.append("<tr>");
            html.append("<td>").append(aliado.getApertura()).append("</td>");
            html.append("<td>").append(aliado.getCategoria()).append("</td>");
            html.append("<td>").append(aliado.getCodigo()).append("</td>");
            html.append("<td>").append(aliado.getNegocio()).append("</td>");
            html.append("</tr>");
        }

        // Cerrar la tabla HTML
        html.append("</table>");

        return html.toString();
    }

    private List<Aliados> obtenerAliadosDeRespuesta(String jsonResponse) {
        try {
            // Convertir la respuesta JSON a una lista de objetos Aliados
            return objectMapper.readValue(jsonResponse, new TypeReference<List<Aliados>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String crearTablaHTMLProductos(List<Productos> productos) {
        StringBuilder html = new StringBuilder();

        // Crear la estructura de la tabla HTML
        html.append("<table>");
        html.append("<tr><th>Categoria</th><th>Codigo</th><th>Descripcion</th><th>PrecioSol</th><th>Presentacion</th></tr>");

        // Llenar la tabla con los datos de Productos
        for (Productos producto : productos) {
            html.append("<tr>");
            html.append("<td>").append(producto.getCategoria()).append("</td>");
            html.append("<td>").append(producto.getCodigo()).append("</td>");
            html.append("<td>").append(producto.getDescripcion()).append("</td>");
            html.append("<td>").append(producto.getPrecioSol()).append("</td>");
            html.append("<td>").append(producto.getPresentacion()).append("</td>");
            html.append("</tr>");
        }

        // Cerrar la tabla HTML
        html.append("</table>");

        return html.toString();
    }
    private List<Productos> obtenerProductosDeRespuesta(String jsonResponse) {
        try {
            // Convertir la respuesta JSON a una lista de objetos Productos
            return objectMapper.readValue(jsonResponse, new TypeReference<List<Productos>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
