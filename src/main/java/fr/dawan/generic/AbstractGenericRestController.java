package fr.dawan.generic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class AbstractGenericRestController<
        D,
        S extends GenericService<D>
        > {
    
    protected final S service;
    
    @GetMapping
    protected ResponseEntity<Page<D>> findAll(Pageable pageable) {
        Page<D> all = service.findAll(pageable);
        return all.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(all);
    }
    
    @GetMapping("{id}")
    protected ResponseEntity<D> findById(@PathVariable long id) {
        return ResponseEntity.of(service.findById(id));
    }
    
    @PostMapping
    protected ResponseEntity<D> saveOrUpdate(@RequestBody D dto) {
        return ResponseEntity.ok(service.saveOrUpdate(dto));
    }
    
    @DeleteMapping("{id}")
    protected void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
