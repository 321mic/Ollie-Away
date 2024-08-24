package ui;

import model.Event;
import model.EventLog;

public class LogPrinter {

    public LogPrinter() {

    }

    public String printLog(EventLog el) {
        String events = "";
        for (Event next : el) {
            events += next.toString() + "\n\n";
        }
        return events;
    }
}
