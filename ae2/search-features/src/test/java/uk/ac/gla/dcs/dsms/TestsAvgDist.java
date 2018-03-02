package uk.ac.gla.dcs.dsms;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.terrier.indexing.IndexTestUtils;
import org.terrier.structures.Index;
import org.terrier.structures.postings.IterablePosting;
import org.terrier.tests.ApplicationSetupBasedTest;
import org.terrier.utility.ApplicationSetup;

public class TestsAvgDist extends ApplicationSetupBasedTest {

	@Test
	public void testPaper() throws Exception {
		Index index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" },
				new String[] { "a b c d a b d e f g h a i j" });

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true }, new double[] { 1d, 1d }, false);
		assertEquals(4.33333333, score, 0.0001d);
	}

	@Test
	public void testNonPosting() throws Exception {
		Index index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" },
				new String[] { "a b c d a b d e f g h a i j" });

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, false }, new double[] { 1d, 1d }, false);
		assertEquals(Double.MAX_VALUE, score, 0.0001d);
	}

	@Test
	public void test1() throws Exception {
		Index index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" }, new String[] { "a x y b a z w a x b" });

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true }, new double[] { 1d, 1d }, false);
		assertEquals(4, score, 0.0001d);
	}

	@Test
	public void test2() throws Exception {
		Index index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" }, new String[] { "x y z b b b a x y z a" });

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true }, new double[] { 1d, 1d }, false);
		assertEquals(4, score, 0.0001d);
	}

	@Test
	public void test3() throws Exception {
		Index index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" }, new String[] { "b x y z w x y z a" });

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true }, new double[] { 1d, 1d }, false);
		assertEquals(8, score, 0.0001d);
	}

	@Test
	public void test4() throws Exception {
		Index index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" }, new String[] { "a a a a a a b" });

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true }, new double[] { 1d, 1d }, false);
		assertEquals(3.5, score, 0.0001d);
	}

	@Test
	public void test5() throws Exception {
		Index index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" }, new String[] { "b b a x y b a z b" });

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true }, new double[] { 1d, 1d }, false);
		assertEquals(3.25, score, 0.0001d);
	}

	@Test
	public void test6() throws Exception {
		Index index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" }, new String[] { "a x y z b x y z a" });

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true }, new double[] { 1d, 1d }, false);
		assertEquals(4, score, 0.0001d);
	}

	@Test
	public void test7() throws Exception {
		Index index = IndexTestUtils.makeIndexBlocks(new String[] { "D1" }, new String[] { "a x y z b b x y z a" });

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true }, new double[] { 1d, 1d }, false);
		assertEquals(4.5, score, 0.0001d);
	}

	@Before
	public void before() throws Exception {
		ApplicationSetup.setProperty("termpipelines", "");
	}
}
