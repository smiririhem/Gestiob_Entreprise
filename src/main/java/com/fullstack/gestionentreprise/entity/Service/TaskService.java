package com.fullstack.gestionentreprise.entity.Service;

import com.fullstack.gestionentreprise.Repository.TaskRepository;
import com.fullstack.gestionentreprise.entity.Task;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.getAllTasksBuDueDate();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    public Task save(Task task)
    {
        return taskRepository.saveAndFlush(task);
    }
    public boolean existById( Long id)
    {return taskRepository.existsById(id) ;}
    public void deleteTask (Long id)
    {taskRepository.deleteById(id) ;}

}
