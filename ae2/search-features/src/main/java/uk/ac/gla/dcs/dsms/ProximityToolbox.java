package uk.ac.gla.dcs.dsms;

import org.terrier.structures.postings.BlockPosting;
import org.terrier.structures.postings.IterablePosting;

public class ProximityToolbox {
	public static BlockPosting[] getBlocks(IterablePosting[] postings) {
		BlockPosting[] blocks = new BlockPosting[postings.length];

		for (int i = 0; i < postings.length; i++) {
			blocks[i] = (BlockPosting) postings[i];
		}

		return blocks;
	}

	public static double distance(int a, int b) {
		return (double) Math.abs(a - b);
	}

	public static double distance(double a, double b) {
		return Math.abs(a - b);
	}

	public static double average(int[] ints) {
		double d = 0;
		for (int i : ints) {
			d += i;
		}
		return d / ints.length;
	}
}
