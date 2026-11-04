package task_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task_management.entity.Task;
import task_management.entity.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByAssigneeId(Long assigneeId);

    List<Task> findByStatusAndAssigneeId(
            TaskStatus status,
            Long assigneeId
    );

}