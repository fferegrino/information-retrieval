package uk.ac.gla.dcs.dsms;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.terrier.matching.dsms.DependenceScoreModifier;
import org.terrier.structures.postings.BlockPosting;
import org.terrier.structures.postings.IterablePosting;

public class MinDistDsm extends DependenceScoreModifier {

	Logger logger = Logger.getLogger(MinDistDsm.class.getName());

	@Override
	public String getName() {
		return "min_dist";
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
		BlockPosting[] postings = ProximityToolbox.getBlocks(ips);

		if (okToUse[0] && okToUse[1]) {

			int[] aPositions = postings[0].getPositions();
			int[] bPositions = postings[1].getPositions();

			double distance = Double.MAX_VALUE;

			for (int a : aPositions) {
				for (int b : bPositions) {
					double d = ProximityToolbox.distance(a, b);
					if (d < distance) {
						distance = d;
					}
				}
			}

			return distance;
		}
		return Double.MAX_VALUE;
	}

	@Override
	protected double scoreFDSD(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
}
