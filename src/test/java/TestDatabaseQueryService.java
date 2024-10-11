import org.example.DatabaseQueryService;
import org.example.MaxProjectCountClient;

import java.util.List;

public class TestDatabaseQueryService {
    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();
        List<DatabaseQueryService.MaxProjectCountClient> maxProjectCountClients = queryService.findMaxProjectsClient();

        for (DatabaseQueryService.MaxProjectCountClient client : maxProjectCountClients) {
            System.out.println("Client: " + client.getName() + ", Project Count: " + client.getProjectCount());
        }
    }
}