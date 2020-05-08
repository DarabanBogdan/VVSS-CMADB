import org.junit.jupiter.api.*;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;
import tasks.repository.TaskList;

import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArrayTaskListTest {
    private Task task;
    private String title;
    private int interval;
    private Date dateStart,dateEnd;
    private SimpleDateFormat sdf;
    @BeforeEach
    void setUp() {
        sdf = Task.getDateFormat();
        try
        {
            dateStart = sdf.parse("2020-04-07 08:00");
            title = "task";

        }catch (ParseException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Ecp interval")
    void ECP_Valid(){
        sdf = Task.getDateFormat();
        TaskList taskList = new ArrayTaskList();
        try {
            dateEnd = sdf.parse("2020-04-08 08:00");
        }catch (ParseException e)
        {
            fail(e.getMessage());
        }
        taskList.add(new Task(title,dateStart,dateEnd,1800));
        assertEquals(taskList.size(),1);
    }
    @Test
    void ECP_nonvalid(){
        sdf = Task.getDateFormat();
        TaskList taskList = new ArrayTaskList();
        try {
            dateEnd = sdf.parse("2020-04-08 08:00");
        }catch (ParseException e)
        {
            fail(e.getMessage());
        }
        try {
            taskList.add(new Task(title, dateStart, dateEnd, -3));
        }catch (IllegalArgumentException e){
            assertEquals(e.getMessage(),"interval should me > 1");
        }

    }
    @Test
    @Order(1)
    void ECP_Valid2(){
        sdf = Task.getDateFormat();
        TaskList taskList = new ArrayTaskList();
        try {
            dateEnd = sdf.parse("2020-04-08 08:00");
        }catch (ParseException e)
        {
            fail(e.getMessage());
        }
        taskList.add(new Task(title,dateStart,dateEnd,200));
        assertEquals(taskList.size(),1);
    }
    @Test
    @Order(2)
    void ECP_nonValid2(){
        sdf = Task.getDateFormat();
        TaskList taskList = new ArrayTaskList();
        try {
            dateEnd = sdf.parse("2020-04-08 -08:00");
        }catch (ParseException e)
        {
            fail(e.getMessage());

        }
        try{
        taskList.add(new Task(title,dateStart,dateEnd,200));}
        catch (IllegalArgumentException e){
            assertEquals(e.getMessage(),"Time cannot be negative");
        }

    }

    @Test
    void BVA_valid() {
        sdf = Task.getDateFormat();
        TaskList taskList = new ArrayTaskList();
        try {
            dateEnd = sdf.parse("2020-04-08 08:00");
        } catch (ParseException e) {
            fail(e.getMessage());
        }
        taskList.add(new Task(title, dateStart, dateEnd,2 ));
        assertEquals(taskList.size(), 1);
    }

    @Test

        void BVA_nonValid(){
            sdf = Task.getDateFormat();
            TaskList taskList = new ArrayTaskList();
            try {
                dateEnd = sdf.parse("2020-04-08 08:00");
            }catch (ParseException e)
            {
                fail(e.getMessage());
            }
            try {
                taskList.add(new Task(title, dateStart, dateEnd, 0));
            }catch (IllegalArgumentException e){
                assertEquals(e.getMessage(),"interval should me > 1");
            }

        }
    @Test

    void BVA_nonValid3(){
        sdf = Task.getDateFormat();
        TaskList taskList = new ArrayTaskList();
        try {
            dateEnd = sdf.parse("2020-04-08 08:00");
        }catch (ParseException e)
        {
            fail(e.getMessage());
        }
        try {
            taskList.add(new Task(title, dateStart, dateEnd, 1));
        }catch (IllegalArgumentException e){
            assertEquals(e.getMessage(),"interval should me > 1");
        }

    }


    @RepeatedTest(2)
    void BVA_valid2(){
            sdf = Task.getDateFormat();
            TaskList taskList = new ArrayTaskList();
            try {
                dateEnd = sdf.parse("2020-04-08 00:01");
            }catch (ParseException e)
            {
                fail(e.getMessage());
            }
                taskList.add(new Task(title, dateStart, dateEnd, 5));
            assertEquals(taskList.size(),1);

        }
        @Test
    void BVA_nonValid2(){
            sdf = Task.getDateFormat();
            TaskList taskList = new ArrayTaskList();
            try {
                dateEnd = sdf.parse("2020-04-08 00:00");
            }catch (ParseException e)
            {
                fail(e.getMessage());
            }
            try {
                taskList.add(new Task(title, dateStart, dateEnd, 5));
            }catch (IllegalArgumentException e){
                assertEquals(e.getMessage(),"Time cannot be negative");
            }
        }
}