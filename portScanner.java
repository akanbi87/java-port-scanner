import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class portScanner {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ENTER host: ");
        String host = scanner.nextLine();

        System.out.print("ENTER start port: ");
        int startPort = scanner.nextInt();

        System.out.print("ENTER end port: ");
        int endPort = scanner.nextInt();

        long startTime = System.currentTimeMillis();
        AtomicInteger openCount = new AtomicInteger(0);
        Map<Integer, String> services = new HashMap<>();
        ExecutorService pool = Executors.newFixedThreadPool(50);
        services.put(21, "FTP");
        services.put(22, "SSH");
        services.put(25,"SMTP");
        services.put(53,"DNS");
        services.put(80,"HTTP");
        services.put(135,"RPC");
        services.put(139,"NetBIOS");
        services.put(443,"HTTPS");
        services.put(445,"SMB");
        services.put(3306,"MySQL");
        services.put( 3389,"RDP");


        for  (int p = startPort; p <= endPort; p++) {
            final int port= p;
            pool.submit(() ->{
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(host, port), 500);
                    String name = services.getOrDefault(port, "Unknown");
                    System.out.println("Port "+port+" is OPEN (" +name + ")");
                    openCount.incrementAndGet();
                } catch (Exception e) {
                    //System.out.println("Port "+port +" is CLOSED");
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.MINUTES);

        long endTime = System.currentTimeMillis();
        System.out.println("Scan finished in "+(endTime - startTime)+ " ms. Open ports: "+openCount.get());



    }

}