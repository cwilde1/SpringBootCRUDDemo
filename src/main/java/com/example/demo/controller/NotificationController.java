package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.TemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        logger.info("getAll()");
        return ResponseEntity.ok(notificationRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        logger.info("getById() called with id: {}", id);
        Optional<Notification> result = notificationRepository.findById(id);

        if (result.isPresent()) {
            Notification n = result.get();
            String content = n.getTemplate().getBody()
                    .replace("(personal)", Optional.ofNullable(n.getPersonalization()).orElse(""));

            Map<String, Object> response = new HashMap<>();
            response.put("id", n.getId());
            response.put("phoneNumber", n.getPhoneNumber());
            response.put("templateId", n.getTemplate().getId());
            response.put("personalization", n.getPersonalization());
            response.put("content", content);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found");
        }
    }

    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification n) {
        logger.info("create notification");
        // Fetch the full Template entity if only id is provided
        if (n.getTemplate() != null && n.getTemplate().getId() != null) {
            templateRepository.findById(n.getTemplate().getId()).ifPresent(n::setTemplate);
        }
        Notification saved = notificationRepository.save(n);
        // Ensure the returned Notification has a fully populated Template
        if (saved.getTemplate() != null && saved.getTemplate().getId() != null) {
            templateRepository.findById(saved.getTemplate().getId()).ifPresent(saved::setTemplate);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Notification updatedNotification) {
        logger.info("update notification with id: {}", id);
        Optional<Notification> existing = notificationRepository.findById(id);

        if (existing.isPresent()) {
            Notification n = existing.get();
            n.setPhoneNumber(updatedNotification.getPhoneNumber());
            n.setTemplate(updatedNotification.getTemplate());
            n.setPersonalization(updatedNotification.getPersonalization());
            notificationRepository.save(n);
            return ResponseEntity.ok(n);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchNotification(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Notification> optional = notificationRepository.findById(id);
        if (optional.isPresent()) {
            Notification n = optional.get();
            if (updates.containsKey("phoneNumber")) {
                n.setPhoneNumber((String) updates.get("phoneNumber"));
            }
            if (updates.containsKey("personalization")) {
                n.setPersonalization((String) updates.get("personalization"));
            }
            // You could also allow updating templateId here, but it’s trickier and not recommended without validation

            return ResponseEntity.ok(notificationRepository.save(n));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found");
        }
    }
}