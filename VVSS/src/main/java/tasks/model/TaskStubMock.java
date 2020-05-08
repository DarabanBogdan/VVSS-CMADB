package tasks.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskStubMock extends Task{

    @Override
    public String getTitle(){
        return "T";
    }

    @Override
    public Date getTime(){
        try{
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return dateFormat.parse("2020-12-29 15:00");

        }catch (ParseException e){
            e.getMessage();
        }
        return null;
    }

}

