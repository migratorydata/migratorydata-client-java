import java.text.SimpleDateFormat;
import java.util.Date;

import com.migratorydata.client.*;

public class Request {
	public static final String SERVER_ADDRESS = "127.0.0.1:8800";
	public static final String SUBJECT = "/system/search";
	public static final String REPLY_SUBJECT = "/system/unique";

	public static void main(String args[]) throws Exception {

		MigratoryDataClient client = new MigratoryDataClient();

		client.setLogListener(new MigratoryDataLogListener() {
			private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");

			@Override
			public void onLog(String log, MigratoryDataLogLevel level) {
				String isoDateTime = sdf.format(new Date(System.currentTimeMillis()));
				System.out.println(String.format("[%1$s] [%2$s] %3$s", isoDateTime, level, log));
			}
		}, MigratoryDataLogLevel.DEBUG);

		client.setEntitlementToken("some-token");

		client.setTimestamp(true);

		client.setListener(new MigratoryDataListener() {
			@Override
			public void onStatus(String status, String info) {
				System.out.println("Got Status: " + status + " " + info);
			}

			@Override
			public void onMessage(MigratoryDataMessage message) {
				System.out.println("Got Reply Message: " + message);
			}
		});

		client.setServers(new String[] { SERVER_ADDRESS });

		client.connect();

		while(true) {
			Thread.sleep(2000);

			MigratoryDataMessage requestMessage = new MigratoryDataMessage(SUBJECT, String.valueOf(System.currentTimeMillis()).getBytes(), null, MigratoryDataMessage.QoS.GUARANTEED, false , REPLY_SUBJECT);

			System.out.println("Sending Request Message: " + requestMessage);

			client.publish(requestMessage);
		}

	}

}
