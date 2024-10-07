import org.example.DatabaseQueryService;
import org.example.MaxProjectCountClient;

import java.util.List;

public class TestDatabaseQueryService {
    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();
        List<MaxProjectCountClient> maxProjectCountClients = queryService.findMaxProjectsClient();

        for (MaxProjectCountClient client : maxProjectCountClients) {
            System.out.println("Client: " + client.getName() + ", Project Count: " + client.getProjectCount());
        }
    }
}