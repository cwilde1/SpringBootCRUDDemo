package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.Template;
import com.example.demo.repository.TemplateRepository;

@RestController
@RequestMapping("/template")
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private TemplateRepository templateRepository;

    @GetMapping
    public ResponseEntity<List<Template>> getAll() {
        logger.info("getAll()");
        return ResponseEntity.ok(templateRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        logger.info("getById() called with id: {}", id);
        Optional<Template> result = templateRepository.findById(id);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Template not found");
        }
    }

    @PostMapping
    public ResponseEntity<Template> create(@RequestBody Template t) {
        logger.info("create template");
        return new ResponseEntity<>(templateRepository.save(t), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Template updatedTemplate) {
        logger.info("update template with id: {}", id);

        Optional<Template> existing = templateRepository.findById(id);
        if (existing.isPresent()) {
            Template t = existing.get();
            t.setBody(updatedTemplate.getBody());
            templateRepository.save(t);
            return ResponseEntity.ok(t);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Template not found");
        }
    }

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteTemplate(@PathVariable Long id) {
    if (templateRepository.existsById(id)) {
        templateRepository.deleteById(id);
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Template not found");
    }
}
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchTemplate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Template> optional = templateRepository.findById(id);
        if (optional.isPresent()) {
            Template template = optional.get();
            if (updates.containsKey("body")) {
                template.setBody((String) updates.get("body"));
            }
            return ResponseEntity.ok(templateRepository.save(template));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Template not found");
        }
    }

    
}
