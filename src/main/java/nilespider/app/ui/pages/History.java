package nilespider.app.ui.pages;

import java.io.Serializable;
import java.util.ArrayList;

public class History implements Serializable{
    public static ArrayList<String> historyList;

    void addHistory(String item)
    {
        historyList.add(item);
    }
    public static String getHistoryAt(int index)
    {
        return historyList.get(index);
    }
    public static ArrayList<String> getHistoryList() {
        return historyList;
    }

}
