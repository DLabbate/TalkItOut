import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class NetworkSocket {
	
	public static void main(String args[]) 
	{
		int port = 6789;
		NetworkSocket server = new NetworkSocket( port );
		
		while (true) 
		{
			server.startServer();
		}
	}

	// declare a server socket and a client socket for the server

	ServerSocket echoServer = null;
	Socket clientSocket = null;
	int port;

	public NetworkSocket( int port ) 
	{
		this.port = port;
	}

	public void stopServer() 
	{
		System.out.println("Server cleaning up.");
		System.exit(0);
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
	StanfordCoreNLP pipeline;

	public NetworkSocketConnection(Socket clientSocket, NetworkSocket server) 
	{
		this.clientSocket = clientSocket;
		this.server = server;
		
		// Model parameters
		
		Properties modelProperties = new Properties();
		modelProperties.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
		modelProperties.setProperty("coref.algorithm", "neural");

		// Start pipeline
		pipeline = new StanfordCoreNLP(modelProperties);
		
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

				// Get client's input and preprocess it
				Annotation clientInput = new Annotation(preprocessing(line));
				pipeline.annotate(clientInput);

				// Put client's input into a map (split per sentence)
				List<CoreMap> sentences = clientInput.get(SentencesAnnotation.class);
				int sentiment = predictSentiment(sentences);
				os.println(sentiment);
			}
			catch (NullPointerException e)
			{}

			System.out.println( "Connection closed." );
			is.close();
			os.close();
			clientSocket.close();

			if ( serverStop ) server.stopServer();
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}

	/**
	 * The predictions are on a 5 point scale
	 * 1 = very negative, 2 = negative, 3 = neutral, 4 = positive, 5 = very positive
	 */
	public static int predictSentiment(List<CoreMap> sentences)
	{
		int i = 0;
		int[] scores = new int[sentences.size()];
		for(CoreMap sentence: sentences)
		{
			Tree tree = sentence.get(SentimentAnnotatedTree.class);
			scores[i] = 1+(int)RNNCoreAnnotations.getPredictedClass(tree);
			i++;
		}
		return mean(scores);
	}

	public static int mean(int[] array)
	{
		int sum = 0;
		int i = 0;

		while(i < array.length)
		{
			sum += array[i];
			i++;
		}
		return sum/array.length;

	}

	public static String preprocessing(String text)
	{
		String processedString = text.toLowerCase().replaceAll("[^a-zA-Z0-9\\s.\']", "");

		return processedString;
	}
}

