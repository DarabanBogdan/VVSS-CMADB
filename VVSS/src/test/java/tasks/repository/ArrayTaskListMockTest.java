package tasks.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.Task;
import tasks.model.TaskStubMock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class ArrayTaskListMockTest {
    private ArrayTaskList tasksTest ;
    private SimpleDateFormat dateFormat;
    @BeforeEach
    public void setUp() {
        tasksTest=new ArrayTaskList();
        try{
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_add_valid(){
        ArrayTaskList spyTasksTest=spy(tasksTest);
        try {
            Task t = new Task("t1", dateFormat.parse("2020-10-23 13:00"));
            spyTasksTest.add(t);

        }catch (Exception e){
            e.getMessage();
        }
        assertEquals(1,spyTasksTest.size());
        assertTrue(spyTasksTest.getTask(0).getTitle().equals("t1"));

    }
    @Test
    public void test_remove_valid(){
        ArrayTaskList spyTasksTest=spy(tasksTest);
        try {
            Task t = new Task("t1", dateFormat.parse("2020-10-23 13:00"));
            Task tt = new Task("t2", dateFormat.parse("2020-10-23 14:00"));
            spyTasksTest.add(t);
            spyTasksTest.add(tt);
            assertEquals(2,spyTasksTest.size());
            spyTasksTest.remove(t);
            assertEquals(1,spyTasksTest.size());
            assertTrue(spyTasksTest.getTask(0).getTitle().equals("t2"));

        }catch (Exception e){
            e.getMessage();
        }
    }



}