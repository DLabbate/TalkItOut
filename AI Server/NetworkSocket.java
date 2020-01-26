import java.io.*;
import java.net.*;
import java.util.List;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class NetworkSocket extends Pipeline 
{
	ServerSocket echoServer = null;
	Socket clientSocket = null;
	int port;

	// declare a server socket and a client socket for the server

	public NetworkSocket( int port ) 
	{
		this.port = port;
	}

	public void stopServer() 
	{
		System.out.println("Server cleaning up.");
		System.exit(0);
	}
	
	public static void main(String args[]) 
	{
		int port = 6789;
		NetworkSocket server = new NetworkSocket(port);
		
		while (true) 
		{
			server.startServer();
		}
	}

	public void startServer() 
	{
		// Try to open a server socket on the given port
		// Note that we can't choose a port less than 1024 if we are not
		// privileged users (root)

		try 
		{
			echoServer = new ServerSocket(port);
		}
		catch (IOException e) 
		{
			System.out.println(e);
		}   

		System.out.println( "Waiting for connections. Only one connection is allowed." );

		try 
		{
			clientSocket = echoServer.accept();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
				
		NetworkSocketConnection oneconnection = new NetworkSocketConnection(clientSocket, this);
				
		oneconnection.run();

	}
}

class NetworkSocketConnection {
	
	BufferedReader is;
	PrintStream os;
	Socket clientSocket;
	NetworkSocket server;
	StanfordCoreNLP model;

	public NetworkSocketConnection(Socket clientSocket, NetworkSocket server) 
	{
		this.clientSocket = clientSocket;
		this.server = server;

		// Start pipeline and parameters
		model = new StanfordCoreNLP(Pipeline.properties());
		
		System.out.println( "Connection established with: " + clientSocket );
		
		try 
		{
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}

	public void run() 
	{
		String line;
		try 
		{
			boolean serverStop = false;

			try 
			{
				line = is.readLine();
				System.out.println("Demand was: " + line);

				// Get client's input and preprocess it
				Annotation clientInput = new Annotation(Pipeline.preprocessing(line));
				model.annotate(clientInput);

				// Put client's input into a map (split per sentence)
				List<CoreMap> sentences = clientInput.get(SentencesAnnotation.class);
				int sentiment = Pipeline.predictSentiment(sentences);
				os.println(sentiment);
				System.out.println("Response was: " + sentiment);
			}
			catch (NullPointerException e)
			{}

			System.out.println("Connection closed.");
			is.close();
			os.close();
			clientSocket.close();

			if (serverStop)
				server.stopServer();
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}
}

