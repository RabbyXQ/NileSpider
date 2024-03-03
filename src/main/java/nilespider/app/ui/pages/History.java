package nilespider.app.ui.pages;

import java.io.Serializable;
import java.util.ArrayList;

public class History {
}
class Items<T> implements Serializable
{
    private ArrayList<T> historyList;

    void addHistory(T item)
    {
        historyList.add(item);
    }
    T getHistoryAt(int index)
    {
        return historyList.get(index);
    }
    public ArrayList<T> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(ArrayList<T> historyList) {
        this.historyList = historyList;
    }
}