package uk.ac.gla.dcs.dsms;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.terrier.indexing.IndexTestUtils;
import org.terrier.structures.Index;
import org.terrier.structures.postings.IterablePosting;
import org.terrier.structures.postings.BlockPosting;
import org.terrier.tests.ApplicationSetupBasedTest;
import org.terrier.utility.ApplicationSetup;

public class TestPaperProximityFeatures extends ApplicationSetupBasedTest {

	Index index;
	IterablePosting[] ips;

	@Before
	public void createIndex() throws Exception {
		ApplicationSetup.setProperty("termpipelines", "");

		index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" }, new String[] { "a b c d a b d e f g h a i j" });

		ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();
	}

	@Test
	public void testMinDist() throws Exception {
		MinDistDsm sample = new MinDistDsm();
		double score = sample.calculateDependence(ips, // posting lists
				new boolean[] { true, true }, // is this posting list on the correct document?
				new double[] { 1d, 1d }, false// doesnt matter
		);
		assertEquals(1.0, score, 0.0d);
	}

	@Test
	public void testDiffAvgPos() throws Exception {
		DiffAvgPosDsm sample = new DiffAvgPosDsm();
		double score = sample.calculateDependence(ips, // posting lists
				new boolean[] { true, true }, // is this posting list on the correct document?
				new double[] { 1d, 1d }, false// doesnt matter
		);
		assertEquals(2.0, score, 0.0d);
	}

	@Test
	public void testAvgDist() throws Exception {
		AvgDistDsm sample = new AvgDistDsm();
		double score = sample.calculateDependence(ips, // posting lists
				new boolean[] { true, true }, // is this posting list on the correct document?
				new double[] { 1d, 1d }, false// doesnt matter
		);
		assertEquals((double) 4.333333333333, score, 0.000000001d);
	}

	@Test
	public void testMatchDist() throws Exception {
		AvgMinDistDsm sample = new AvgMinDistDsm();
		double score = sample.calculateDependence(ips, // posting lists
				new boolean[] { true, true }, // is this posting list on the correct document?
				new double[] { 1d, 1d }, false// doesnt matter
		);
		assertEquals(1.0, score, 0.000000001d);
	}
}
