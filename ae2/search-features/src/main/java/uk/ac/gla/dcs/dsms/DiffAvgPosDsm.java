package uk.ac.gla.dcs.dsms;

import org.terrier.matching.dsms.DependenceScoreModifier;
import org.terrier.structures.postings.BlockPosting;
import org.terrier.structures.postings.IterablePosting;

public class DiffAvgPosDsm extends DependenceScoreModifier {
	
	@Override
	public String getName() {
		return "diff_avg_pos";
	}
	
	/** This class is passed the postings of the current document,
	 * and should return a score to represent that document.
	 */
	@Override
	protected double calculateDependence(
			IterablePosting[] ips, //posting lists
			boolean[] okToUse,  //is this posting list on the correct document?
			double[] phraseTermWeights, boolean SD //not needed
		) 
	{
		BlockPosting[] postings = ProximityToolbox.getBlocks(ips);
		int[] aPositions = postings[0].getPositions();
		int[] bPositions = postings[1].getPositions();
		
		double meanA = ProximityToolbox.average(aPositions);
		double meanB = ProximityToolbox.average(bPositions);
		
		double distance = ProximityToolbox.distance(meanA, meanB);
		
		return distance;
	}
	
	@Override
	protected double scoreFDSD(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
