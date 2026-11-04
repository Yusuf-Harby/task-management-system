
package task_management.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import task_management.dto.CreateTaskRequest;
import task_management.dto.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task_management.entity.Task;
import task_management.service.TaskService;
import org.springframework.web.bind.annotation.PutMapping;
import task_management.dto.UpdateTaskRequest;
import org.springframework.web.bind.annotation.PatchMapping;
import task_management.dto.UpdateTaskStatusRequest;
import org.springframework.web.bind.annotation.RequestParam;
import task_management.entity.TaskStatus;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(
        name = "Task Management",
        description = "APIs for creating, retrieving, updating, and filtering tasks"
)
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(
            summary = "Get all tasks with optional filters",
            description = "Retrieves all tasks. Results can be filtered by task status, assignee ID, or both."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tasks retrieved successfully"
    )
    public List<Task> getAllTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) Long assigneeId
    ) {

        if (status != null && assigneeId != null) {
            return taskService.getTasksByStatusAndAssigneeId(
                    status,
                    assigneeId
            );
        }

        if (status != null) {
            return taskService.getTasksByStatus(status);
        }

        if (assigneeId != null) {
            return taskService.getTasksByAssigneeId(assigneeId);
        }

        return taskService.getAllTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new task",
            description = "Creates a task with a title, description, assignee, and priority."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Task created successfully"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request data",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public Task createTask(@Valid @RequestBody CreateTaskRequest request) {

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setAssigneeId(request.getAssigneeId());

        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }

        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a task by ID",
            description = "Retrieves a single task using its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task retrieved successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing task",
            description = "Updates the title, description, assignee, and priority of an existing task."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task updated successfully"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request data",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public Task updateTask(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request
    ) {
        return taskService.updateTask(id, request);
    }

    @PatchMapping("/{id}/status")
    @Operation(
            summary = "Update task status",
            description = "Updates the status of an existing task to TODO, IN_PROGRESS, or COMPLETED."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task status updated successfully"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid task status",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public Task updateTaskStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskStatusRequest request
    ) {
        return taskService.updateTaskStatus(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a task",
            description = "Deletes an existing task using its unique ID."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Task deleted successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}
