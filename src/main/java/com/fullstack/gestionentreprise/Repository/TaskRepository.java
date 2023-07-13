package com.fullstack.gestionentreprise.Repository;
import com.fullstack.gestionentreprise.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query(value=" select * from task order by due_date desc", nativeQuery= true)
    public List<Task> getAllTasksBuDueDate();

}
