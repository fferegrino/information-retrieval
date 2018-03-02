package uk.ac.gla.dcs.dsms;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.terrier.matching.dsms.DependenceScoreModifier;
import org.terrier.structures.postings.BlockPosting;
import org.terrier.structures.postings.IterablePosting;

public class DiffAvgPosDsm extends DependenceScoreModifier {

	private final static Logger Log = Logger.getLogger(DiffAvgPosDsm.class.getName());

	@Override
	public String getName() {
		return "diff_avg_pos";
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

			double meanA = ProximityToolbox.average(aPositions);
			double meanB = ProximityToolbox.average(bPositions);

			double distance = ProximityToolbox.distance(meanA, meanB);

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
