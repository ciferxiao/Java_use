package cifer.com.java_use.EventBus;

/**
 * Created by lvzhiqiang on 2018/4/16 13:40.
 */

public class Event {
    private String message;
    public Event(String event){
        this.message = event;
    }

    public String getMessage(){
        return message;
    }
}
