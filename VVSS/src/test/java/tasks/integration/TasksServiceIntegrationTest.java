package tasks.integration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.model.TaskStubMock;
import tasks.repository.ArrayTaskList;
import tasks.services.TasksService;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceIntegrationTest {

    private ArrayTaskList taskList;
    private TasksService tasksService;

    private  SimpleDateFormat dateFormat ;

    @BeforeEach
    void setUp() {
        taskList =new ArrayTaskList();
        tasksService = new TasksService(taskList);
        dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @Test
    public void test_integration_get_add(){
        try{
        Task t = new Task("T", dateFormat.parse("2020-03-12 14:00"));
        taskList.add(t);
        ObservableList<Task> obs = FXCollections.observableArrayList();
        obs=tasksService.getObservableList();
        assertEquals(1,obs.size());
        assertTrue(obs.get(0).getTitle().equals("T"));}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test_integration_get_remove(){
        try {


            Task t = new Task("T", dateFormat.parse("2020-03-12 14:00"));
            Task t2 = new Task("Tt", dateFormat.parse("2020-03-12 14:00"));
            taskList.add(t);
            taskList.add(t2);

            ObservableList<Task> obs = FXCollections.observableArrayList();
            obs = tasksService.getObservableList();
            assertEquals(2, obs.size());
            taskList.remove(t);
            obs = tasksService.getObservableList();
            assertEquals(1, obs.size());
            assertTrue(obs.get(0).getTitle().equals("Tt"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}