import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.migratorydata.client.*;

public class Reply {
	public static final String SERVER_ADDRESS = "127.0.0.1:8800";
	public static final String SUBJECT = "/system/search";

	public static void main(String[] args) throws Exception {

		final MigratoryDataClient client = new MigratoryDataClient();

		client.setLogListener(new MigratoryDataLogListener() {
			private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");

			@Override
			public void onLog(String log, MigratoryDataLogLevel level) {
				String isoDateTime = sdf.format(new Date(System.currentTimeMillis()));
				System.out.println(String.format("[%1$s] [%2$s] %3$s", isoDateTime, level, log));
			}
		}, MigratoryDataLogLevel.DEBUG);

		client.setEntitlementToken("some-token");

		client.setListener(new MigratoryDataListener() {
			@Override
			public void onStatus(String status, String info) {
				System.out.println("Got Status: " + status + " - " + info);
			}

			@Override
			public void onMessage(MigratoryDataMessage message) {
				System.out.println("Got Request Message: " + message);

				String replySubject = message.getReplySubject();

				if (replySubject != null) {

					String content = new String(message.getContent());

					MigratoryDataMessage replyMessage = new MigratoryDataMessage(replySubject, ("echo: " + content).getBytes());
					try {
						client.publish(replyMessage);

						System.out.println("Sending Reply Message: " + replyMessage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		client.setServers(new String[] { SERVER_ADDRESS });

		client.subscribe(Arrays.asList(SUBJECT));

		client.connect();

		Thread.sleep(1000000);
	}
}
