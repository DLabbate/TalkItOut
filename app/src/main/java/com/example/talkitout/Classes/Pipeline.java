package com.example.talkitout.Classes;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.CoreMap;
import java.util.*;


class Pipeline 
{

	public static String Placeholder = "I want to kill myself. " +
									   "I am having a bad day. " +
									   "My day could be going better. " +
									   "My day is going okay. " +
									   "Thank you for all your help. ";
	
	public static void main(String[] args)
	{
		// Model parameters
		Properties modelProperties = new Properties();
		modelProperties.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
		modelProperties.setProperty("coref.algorithm", "neural");
		
		// Start pipeline
		StanfordCoreNLP pipeline = new StanfordCoreNLP(modelProperties);
		
		// Get client's input and preprocess it
		Annotation clientInput = new Annotation(Placeholder);
		pipeline.annotate(clientInput);
		
		// Put client's input into a map (split per sentence)
		List<CoreMap> sentences = clientInput.get(SentencesAnnotation.class);
						
		System.out.println(predictSentiment(sentences));
		
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
}
