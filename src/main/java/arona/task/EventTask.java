package arona.task;

public class EventTask extends Task{
    protected String from;
    protected String to;
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public EventTask(String description, String from, String to, int isMarked) {
        super(description, isMarked);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from + " " + to + ")";
    }
}