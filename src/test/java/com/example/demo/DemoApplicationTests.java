package com.example.demo;

import com.example.demo.controller.NotificationController;
import com.example.demo.model.Notification;
import com.example.demo.model.Template;
import com.example.demo.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationRepository notificationRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAllNotifications() throws Exception {
        Template t1 = new Template();
        t1.setId(1L);
        t1.setBody("Body 1");
        Notification n1 = new Notification();
        n1.setId(1L);
        n1.setTemplate(t1);
        Template t2 = new Template();
        t2.setId(2L);
        t2.setBody("Body 2");
        Notification n2 = new Notification();
        n2.setId(2L);
        n2.setTemplate(t2);
        Mockito.when(notificationRepository.findAll()).thenReturn(Arrays.asList(n1, n2));

        mockMvc.perform(get("/notification"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Body 1")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Body 2")));
    }

    @Test
    void testGetNotificationById_found() throws Exception {
        Template t = new Template();
        t.setId(1L);
        t.setBody("Body 1");
        Notification n = new Notification();
        n.setId(1L);
        n.setTemplate(t);
        Mockito.when(notificationRepository.findById(1L)).thenReturn(Optional.of(n));

        mockMvc.perform(get("/notification/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Body 1")));
    }

    @Test
    void testGetNotificationById_notFound() throws Exception {
        Mockito.when(notificationRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/notification/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateNotification() throws Exception {
        Template t = new Template();
        t.setId(1L);
        t.setBody("Created Body");
        Notification n = new Notification();
        n.setId(1L);
        n.setTemplate(t);
        Mockito.when(notificationRepository.save(Mockito.any(Notification.class))).thenReturn(n);

        String json = "{\"template\":{\"id\":1,\"body\":\"Created Body\"}}";
        mockMvc.perform(post("/notification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Created Body")));
    }
}
