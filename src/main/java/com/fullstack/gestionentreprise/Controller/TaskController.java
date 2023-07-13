package com.fullstack.gestionentreprise.Controller;
import com.fullstack.gestionentreprise.entity.Service.TaskService;
import com.fullstack.gestionentreprise.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class TaskController {
    private TaskService taskService;
    @GetMapping("/task")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requête non trouvée"));
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable Long id) {
        if (taskService.existById(id)) {
            Task existingTask = taskService.getTaskById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Requête non trouvée"));

            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setType(task.getType());

            taskService.save(existingTask);

            return ResponseEntity.ok().body(existingTask);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + " - Tâche non trouvée ou non valide");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id)
    {
        if (taskService.existById(id)) {
            taskService.deleteTask(id);
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + " - supprimé avec succés");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);}
        else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + " - Tâche non trouvée ou non valide");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}




