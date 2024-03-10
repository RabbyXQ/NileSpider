package nilespider.app.ui.pages;

import java.io.*;
import java.util.ArrayList;

public class History implements Serializable {
    private static final long serialVersionUID = 1L;
    private static ArrayList<String> historyList;

    static {
        // Initialize the historyList to an empty ArrayList
        historyList = new ArrayList<>();
    }

    public static void addHistory(String item) {
        historyList.add(item);
    }

    public static String getHistoryAt(int index) {
        return historyList.get(index);
    }

    public static ArrayList<String> getHistoryList() {
        return historyList;
    }

    public static void saveHistory(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(historyList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadHistory(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            historyList = (ArrayList<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
