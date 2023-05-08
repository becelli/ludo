package ludo.model.socket;
public class Host implements Runnable {
    private final Connection connection;
    public Host(Connection connection) {
        this.connection = connection;
    }
    public void run() {
        try {
            connection.startConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
