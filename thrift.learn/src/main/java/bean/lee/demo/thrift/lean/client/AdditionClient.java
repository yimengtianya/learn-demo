package bean.lee.demo.thrift.lean.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import bean.lee.demo.thrift.lean.server.AdditionService;

public class AdditionClient {
	public static void main(String[] args) {

		try {
			TTransport transport;

			transport = new TSocket("localhost", 9090);
			transport.open();

			// TProtocol protocol = new TBinaryProtocol(transport);
			TProtocol protocol = new TBinaryProtocol(new TFramedTransport(transport));
			
			
			AdditionService.Client client = new AdditionService.Client(protocol);

			System.out.println(client.add(100, 200));

			transport.close();

		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

}
