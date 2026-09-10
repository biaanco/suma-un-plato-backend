package com.sumaunplato.categoria;

import com.sumaunplato.common.exception.RecursoNoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categorias", description = "Gestion de categorias de insumos")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Listar todas las categorias")
    @GetMapping
    public List<Categoria> listar() {
        return repository.findAll();
    }

    @Operation(summary = "Crear una categoria")
    @PostMapping
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(repository.save(categoria), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una categoria por su id")
    @GetMapping("/{id}")
    public Categoria obtener(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la categoria con id " + id));
    }

    @Operation(summary = "Eliminar una categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe la categoria con id " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
