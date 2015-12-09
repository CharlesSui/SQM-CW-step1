package g53sqm.chat.client;

import java.util.concurrent.TimeUnit;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import org.junit.Test;

class ServerTest {

	@Test
	public static void main(String[] args) {

	    String[] client1Commands = {"IDEN james", "HAIL Hello from client 1", "QUIT"}; // add more commands in here
	    String[] client2Commands = {"IDEN tom", "LIST", "HAIL Hello from client 2", "QUIT"}; // add more commands in here

	    String[] testStat = {"IDEN charles", "STAT", "HAIL Hello from client 3", "QUIT"}; //test STAT
	    String[] testMesg = {"IDEN porden", "HAIL Hello from client 4", "MESG SQM is good", "QUIT"}; //test MESG

	    String[] testExistName = {"IDEN porden", "HAIL Hello from client 5", "MESG SQM is good", "QUIT"};//porden -- test existing name
	    String[] testNoNameCom = {"IDENE sam", "HAIL Hello from client 6", "QUIT"}; // IDENE -- test invalid command cause no name
	    String[] testNoHailCom = {"IDEN bob", "HL hello from client 7", "QUIT"}; // HL -- test invalid command cause no hail

	    String[] testInvalidCom1 = {"IDE ming","HAIL Hello from client 8", "QUIT" };// IDE -- test invalid command
	    String[] testInvalidCom2 = {"HL hello from client 9", "QUIT"}; //HL -- test invalid command
	    String[] testNotRecognisedCom1 = {"ABCD sam", "HAIL Hello from client 10", "QUIT"}; // ABCD -- test command not recognized






	    ExecutorService executor = Executors.newCachedThreadPool();
	    executor.execute(new StubClient(9000, "localhost", client1Commands, "client1"));
	    executor.execute(new StubClient(9000, "localhost", client2Commands, "client2"));
	    executor.execute(new StubClient(9000, "localhost", testStat, "client3"));
	    executor.execute(new StubClient(9000, "localhost", testMesg, "client4"));
	    executor.execute(new StubClient(9000, "localhost", testExistName, "client5"));
	    executor.execute(new StubClient(9000, "localhost", testNoNameCom, "client6"));
	    executor.execute(new StubClient(9000, "localhost", testNoHailCom, "client7"));
	    executor.execute(new StubClient(9000, "localhost", testInvalidCom1, "client8"));
	    executor.execute(new StubClient(9000, "localhost", testInvalidCom2, "client9"));
	    executor.execute(new StubClient(9000, "localhost", testNotRecognisedCom1, "client10"));



	    try {
	      executor.awaitTermination(1, TimeUnit.MINUTES);
	    } catch(InterruptedException e){
	      e.printStackTrace();
	    }

  }

}
