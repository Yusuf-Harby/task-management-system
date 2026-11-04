package task_management.service;

import task_management.dto.UpdateTaskRequest;
import org.springframework.stereotype.Service;
import task_management.entity.Task;
import task_management.repository.TaskRepository;
import task_management.dto.UpdateTaskStatusRequest;
import task_management.entity.TaskStatus;
import task_management.exception.TaskNotFoundException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Task not found with id: " + id
                ));
    }

    public Task updateTask(Long id, UpdateTaskRequest request) {

        Task task = getTaskById(id);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setAssigneeId(request.getAssigneeId());
        task.setPriority(request.getPriority());

        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Long id, UpdateTaskStatusRequest request) {

        Task task = getTaskById(id);

        task.setStatus(request.getStatus());

        return taskRepository.save(task);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByAssigneeId(Long assigneeId) {
        return taskRepository.findByAssigneeId(assigneeId);
    }

    public List<Task> getTasksByStatusAndAssigneeId(
            TaskStatus status,
            Long assigneeId
    ) {
        return taskRepository.findByStatusAndAssigneeId(
                status,
                assigneeId
        );
    }

    public void deleteTask(Long id) {

        Task task = getTaskById(id);

        taskRepository.delete(task);
    }
}