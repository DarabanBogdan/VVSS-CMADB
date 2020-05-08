package tasks.integration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.model.TaskStubMock;
import tasks.repository.ArrayTaskList;
import tasks.services.TasksService;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceIntegrationMockTest {
    private ArrayTaskList taskList;
    private TasksService tasksService;


    @BeforeEach
    void setUp() {
        taskList =new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    public void test_integration_get_add(){
        Task t = new TaskStubMock();
        taskList.add(t);
        ObservableList<Task> obs = FXCollections.observableArrayList();
        obs=tasksService.getObservableList();
        assertEquals(1,obs.size());
        assertTrue(obs.get(0).getTitle().equals("T"));
    }

    @Test
    public void test_integration_get_remove(){
        Task t = new TaskStubMock();
        Task t2 = new TaskStubMock();
        taskList.add(t);
        taskList.add(t2);

        ObservableList<Task> obs = FXCollections.observableArrayList();
        obs=tasksService.getObservableList();
        assertEquals(2,obs.size());
        taskList.remove(t);
        obs=tasksService.getObservableList();
        assertEquals(1,obs.size());
        assertTrue(obs.get(0).getTitle().equals("T"));

    }

}