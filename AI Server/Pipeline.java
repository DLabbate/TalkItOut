import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.CoreMap;
import java.util.*;


class Pipeline 
{

	public static String Placeholder = null;
	
	public static void main(String[] args)
	{
		
		// Start pipeline and parameters
		StanfordCoreNLP pipeline = new StanfordCoreNLP(properties());
		
		// Get client's input and preprocess it
		Annotation clientInput = new Annotation(preprocessing(Placeholder));
		pipeline.annotate(clientInput);
		
		// Put client's input into a map (split per sentence)
		List<CoreMap> sentences = clientInput.get(SentencesAnnotation.class);
						
		System.out.println(predictSentiment(sentences));
		
	}
	
	/**
	 * The predictions are on a 5 point scale
	 * 1 = very negative, 2 = negative, 3 = neutral, 4 = positive, 5 = very positive
	 * 0 indicates an error (usually, an empty string or null was given as input)
	 */
	public static int predictSentiment(List<CoreMap> sentences)
	{
		int x;
		int i = 0;
		int[] scores = new int[sentences.size()];
		for(CoreMap sentence: sentences)
		{
			Tree tree = sentence.get(SentimentAnnotatedTree.class);
			scores[i] = 1+(int)RNNCoreAnnotations.getPredictedClass(tree);
			i++;
		}
		try
		{
			x = mean(scores);
		}
		catch(ArithmeticException ex)
		{
			x = 0;
		}
		return x;
	}
	
	public static int mean(int[] array) throws ArithmeticException
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
		String processedString = null;
		try
		{
			processedString = text.toLowerCase().replaceAll("[^a-zA-Z0-9\\s.\']", "");
		}
		catch(NullPointerException e)
		{
			processedString = "";
		}
		return processedString;
	}
	
	public static Properties properties()
	{
		Properties modelProperties = new Properties();
		modelProperties.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
		modelProperties.setProperty("coref.algorithm", "neural");
		
		return modelProperties;
	}
}
