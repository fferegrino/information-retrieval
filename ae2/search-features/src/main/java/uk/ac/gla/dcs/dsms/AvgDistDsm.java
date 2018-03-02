package uk.ac.gla.dcs.dsms;

import java.util.logging.Logger;

import org.terrier.matching.dsms.DependenceScoreModifier;
import org.terrier.structures.postings.BlockPosting;
import org.terrier.structures.postings.IterablePosting;

public class AvgDistDsm extends DependenceScoreModifier {

	private final static Logger Log = Logger.getLogger(AvgDistDsm.class.getName());

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

		if (okToUse[0] && okToUse[1]) {
			BlockPosting[] postings = ProximityToolbox.getBlocks(ips);
			int[] aPositions = postings[0].getPositions();
			int[] bPositions = postings[1].getPositions();

			double distance = 0;

			for (int a : aPositions) {
				for (int b : bPositions) {
					distance += ProximityToolbox.distance(a, b);
				}
			}

			return distance / (aPositions.length * bPositions.length);
		}
		return Double.MAX_VALUE;
	}

	@Override
	protected double scoreFDSD(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
