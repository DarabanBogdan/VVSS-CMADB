package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class TasksServiceMockTest {
    private ArrayTaskList tasksList;
    private TasksService tasksService;
    private SimpleDateFormat dateFormat;

    @BeforeEach
    public void setUp() {
        tasksList = new ArrayTaskList();
        tasksService = new TasksService(tasksList);
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_get_tasks() {
        TasksService spyTasksService = spy(tasksService);

        ObservableList<Task> obs = FXCollections.observableArrayList();
        try {
            Task t = new Task("t1", dateFormat.parse("2020-10-23 13:00"));
            Task tt = new Task("t2", dateFormat.parse("2020-10-23 14:00"));
            tasksList.add(t);
            tasksList.add(tt);
            assertEquals(2, tasksList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        obs = spyTasksService.getObservableList();
        assertEquals(2,obs.size());
        assertTrue(obs.get(0).getTitle().equals("t1"));

    }

    @Test

    public void test_form_time_unit(){
        TasksService spyTasksService = spy(tasksService);
        int time = 40;
        String s=spyTasksService.formTimeUnit(time);
        assertTrue(s.equals("40"));
    }
}