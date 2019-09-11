package collapsing.toolbar.layout.design;

public class ItemModel
{
    private String callerName,callTime;

    public ItemModel(String callerName, String callTime)
    {
        this.callerName = callerName;
        this.callTime = callTime;
    }

    public String getCallerName()
    {
        return callerName;
    }

    public String getCallTime() {
        return callTime;
    }
}

