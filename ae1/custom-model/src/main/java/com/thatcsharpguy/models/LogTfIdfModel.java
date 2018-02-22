package com.thatcsharpguy.models;

import org.terrier.matching.models.WeightingModel;
import org.terrier.matching.models.WeightingModelLibrary;

/** You should use this sample class to implement a Simple TF*IDF weighting model for Exercise 1
  * of the exercise. You can tell Terrier to use your weighting model by specifying the 
  * property trec.model=uk.ac.gla.dcs.models.MyWeightingModel
  * NB: There is a corresponding unit test that you should also complete to test your model.
  * @author TODO
  */ 
public class LogTfIdfModel extends WeightingModel
{
	public String getInfo() { return this.getClass().getSimpleName(); }
	
	private final double constant = 0.5;
	
	public double score(double tf, double docLength) {

		double score = 0;
		
		double numerator = (numberOfDocuments - documentFrequency) + constant;
		double denominator = documentFrequency + constant;
		
		score = tf * (WeightingModelLibrary.log(numerator / denominator));
		
		return score;
	}

	/** This method is not required, and you are not expected to implement it */
	public double score(double tf, double docLength, double n_t, double F_t, double _keyFrequency) {
		throw new UnsupportedOperationException();
	}
}
