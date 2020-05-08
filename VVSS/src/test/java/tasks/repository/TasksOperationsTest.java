package tasks.repository;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TasksOperationsTest {
    private ObservableList<Task> ot;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private  TasksOperations to;

    @BeforeEach
    void setUp() {
        List<Task> listTask = new ArrayList<>();
        ot = FXCollections.observableList(listTask);
        try {
            to = new TasksOperations(ot);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    void WBT_TC1() {

        try {
            Date d1 = dateFormat.parse("2020-05-14 15:00");
            Date d2 = dateFormat.parse("2020-05-20 15:00");
            Task t1 = new Task("t1", dateFormat.parse("2020-05-14 16:00"));
            t1.setActive(true);
            Task t2 = new Task("t2", dateFormat.parse("2020-05-19 15:00"), dateFormat.parse("2020-06-19 12:00"), 30);
            t2.setActive(true);
            to.tasks.add(t1);
            to.tasks.add(t2);
            Iterable<Task> filterTasksTest = to.incoming(d1, d2);
            assertEquals(2,filterTasksTest.spliterator().getExactSizeIfKnown());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void WBT_TC2() {
        try {
            Date d1 = dateFormat.parse("2020-05-14 15:00");
            Date d2 = dateFormat.parse("2020-05-20 15:00");
            Task t3 = new Task("t3", dateFormat.parse("2020-05-20 16:00"));
            t3.setActive(true);
            to.tasks.add(t3);
            Iterable<Task> filterTasksTest = to.incoming(d1, d2);
            assertEquals(0,filterTasksTest.spliterator().getExactSizeIfKnown());
        } catch (ParseException e) {
            e.getMessage();
        }
    }

    @Test
    void WBT_TC3() {
        try {
            Date d1 = dateFormat.parse("2020-05-14 15:00");
            Date d2 = dateFormat.parse("2020-05-20 15:00");
            Task t4 = new Task("t4", dateFormat.parse("2020-05-20 15:00"));
            t4.setActive(true);
            to.tasks.add(t4);
            Iterable<Task> filterTasksTest = to.incoming(d1, d2);
            assertEquals(1,filterTasksTest.spliterator().estimateSize());
            for (Task t:filterTasksTest) {
                assertTrue(t.getTitle().equals("t4"));
            }
        } catch (ParseException e) {
            e.getMessage();
        }
    }

    @Test
    void WBT_TC4() {
        try {
            Date d1 = dateFormat.parse("2020-05-14 15:00");
            Date d2 = dateFormat.parse("2020-05-20 15:00");

            Iterable<Task> filterTasksTest = to.incoming(d1, d2);
            assertEquals(0,filterTasksTest.spliterator().estimateSize());

        } catch (ParseException e) {
            e.getMessage();
        }
    }
    @Test
    void WBT_TC5() {
        try {
            Date d1 = dateFormat.parse("2020-06-14 15:00");
            Date d2 = dateFormat.parse("2020-06-20 15:00");
            Task t5 = new Task("t5", dateFormat.parse("2020-05-19 15:00"), dateFormat.parse("2020-06-13 12:00"), 30);
            t5.setActive(true);
            to.tasks.add(t5);
            Iterable<Task> filterTasksTest = to.incoming(d1, d2);
            assertEquals(0,filterTasksTest.spliterator().estimateSize());

        } catch (ParseException e) {
            e.getMessage();
        }
    }
}