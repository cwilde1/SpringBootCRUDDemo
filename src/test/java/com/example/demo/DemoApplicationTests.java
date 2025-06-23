package com.example.demo;
 
import com.example.demo.model.Notification;
import com.example.demo.model.Template;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.TemplateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationRepository notificationRepository;

    @MockBean
    private TemplateRepository templateRepository;

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
                .andExpect(status().isCreated())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Created Body")));
    }

    @Test
    void testGetAllTemplates() throws Exception {
        Template t1 = new Template();
        t1.setId(1L);
        t1.setBody("Template 1");
        Template t2 = new Template();
        t2.setId(2L);
        t2.setBody("Template 2");
        Mockito.when(templateRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        mockMvc.perform(get("/template"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Template 1")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Template 2")));
    }

    @Test
    void testGetTemplateById_found() throws Exception {
        Template t = new Template();
        t.setId(1L);
        t.setBody("Template 1");
        Mockito.when(templateRepository.findById(1L)).thenReturn(Optional.of(t));

        mockMvc.perform(get("/template/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Template 1")));
    }

    @Test
    void testGetTemplateById_notFound() throws Exception {
        Mockito.when(templateRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/template/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateTemplate() throws Exception {
        Template t = new Template();
        t.setId(1L);
        t.setBody("Created Template");
        Mockito.when(templateRepository.save(Mockito.any(Template.class))).thenReturn(t);

        String json = "{\"body\":\"Created Template\"}";
        mockMvc.perform(post("/template")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Created Template")));
    }

    @Test
    void testUpdateTemplate() throws Exception {
        Template t = new Template();
        t.setId(1L);
        t.setBody("Updated Template");
        Mockito.when(templateRepository.findById(1L)).thenReturn(Optional.of(t));
        Mockito.when(templateRepository.save(Mockito.any(Template.class))).thenReturn(t);

        String json = "{\"body\":\"Updated Template\"}";
        mockMvc.perform(put("/template/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Updated Template")));
    }

    @Test
    void testPatchTemplate() throws Exception {
        Template t = new Template();
        t.setId(1L);
        t.setBody("Patched Template");
        Mockito.when(templateRepository.findById(1L)).thenReturn(Optional.of(t));
        Mockito.when(templateRepository.save(Mockito.any(Template.class))).thenReturn(t);

        String json = "{\"body\":\"Patched Template\"}";
        mockMvc.perform(patch("/template/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Patched Template")));
    }

    @Test
    void testDeleteTemplate() throws Exception {
        Template t = new Template();
        t.setId(1L);
        t.setBody("To Delete");
        Mockito.when(templateRepository.findById(1L)).thenReturn(Optional.of(t));
        Mockito.when(templateRepository.existsById(1L)).thenReturn(true);
        Mockito.doNothing().when(templateRepository).deleteById(1L);

        mockMvc.perform(delete("/template/1"))
                .andExpect(status().isOk());
    }
}
