package uk.ac.gla.dcs.dsms;

import java.util.logging.Logger;

import org.terrier.matching.dsms.DependenceScoreModifier;
import org.terrier.structures.postings.BlockPosting;
import org.terrier.structures.postings.IterablePosting;

public class AvgDistDsm extends DependenceScoreModifier {

	private final static Logger Log = Logger.getLogger(AvgDistDsm.class.getName());
	private final static Double Large = Double.MAX_VALUE / 20;

	@Override
	public String getName() {
		return "avg_dist";
	}

	/**
	 * This class is passed the postings of the current document, and should return
	 * a score to represent that document.
	 */
	@Override
	protected double calculateDependence(IterablePosting[] ips, // posting lists
			boolean[] okToUse, // is this posting list on the correct document?
			double[] phraseTermWeights, boolean SD // not needed
	) {
		final int numberOfTerms = okToUse.length;
		double score = 0;
		int scores = 0;
		for (int i = 0; i < numberOfTerms; i++) {
			if (!okToUse[i])
				continue;
			for (int j = 0; j < numberOfTerms; j++) {
				if (i == j)
					continue;
				scores++;
				if (!okToUse[j] || !okToUse[i])
					score += Large;
				else
					score += calculateDependence(ips[i], ips[j]);
			}
		}
		return score / scores;
	}

	private double calculateDependence(IterablePosting a, IterablePosting b) {
		BlockPosting aa = ProximityToolbox.getBlocks(a);
		BlockPosting bb = ProximityToolbox.getBlocks(b);

		int[] aPositions = aa.getPositions();
		int[] bPositions = bb.getPositions();

		double distance = 0;

		for (int a_ : aPositions) {
			for (int b_ : bPositions) {
				distance += ProximityToolbox.distance(a_, b_);
			}
		}

		return distance / (aPositions.length * bPositions.length);
	}

	@Override
	protected double scoreFDSD(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
