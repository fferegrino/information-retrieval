package uk.ac.gla.dcs.dsms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		Index index = getIndex("a b c d a b d e f g h a i j");

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
		String document = "a b c d a b d e f g h a i j";
		Index index = getIndex(document);

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, false }, new double[] { 1d, 1d }, false);
		assertTrue(score > document.length());
	}

	@Test
	public void test1() throws Exception {
		Index index = getIndex("a x y b a z w a x b");

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
		Index index = getIndex("x y z b b b a x y z a");

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
		Index index = getIndex("a a a a a a b");

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
		Index index = getIndex("b b a x y b a z b");

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
		Index index = getIndex("a x y z b x y z a");

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
		Index index = getIndex("a x y z b b x y z a");

		IterablePosting[] ips = new IterablePosting[2];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[0].next();
		ips[1].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true }, new double[] { 1d, 1d }, false);
		assertEquals(4.5, score, 0.0001d);
	}

	@Test
	public void testSimpleMultipleTerms() throws Exception {
		Index index = getIndex("a b c");

		IterablePosting[] ips = new IterablePosting[3];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[2] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("c"));
		ips[0].next();
		ips[1].next();
		ips[2].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true, true }, new double[] { 1d, 1d }, false);
		assertEquals(1.3333, score, 0.0001d);
	}

	@Test
	public void testMultipleMultipleTerms() throws Exception {
		Index index = getIndex("c c c a a a b b b");

		IterablePosting[] ips = new IterablePosting[3];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[2] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("c"));
		ips[0].next();
		ips[1].next();
		ips[2].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true, true }, new double[] { 1d, 1d }, false);
		assertEquals(4, score, 0.0001d);
	}

	@Test
	public void testComplexMultipleTerms1() throws Exception {
		Index index = getIndex("b x y z a x y c");

		IterablePosting[] ips = new IterablePosting[3];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[2] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("c"));
		ips[0].next();
		ips[1].next();
		ips[2].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true, true }, new double[] { 1d, 1d }, false);
		assertEquals(4.66666, score, 0.0001d);
	}

	@Test
	public void testComplexMultipleTerms2() throws Exception {
		Index index = getIndex("b x y z a x y c");

		IterablePosting[] ips = new IterablePosting[3];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[2] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("c"));
		ips[0].next();
		ips[1].next();
		ips[2].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true, true }, new double[] { 1d, 1d }, false);
		assertEquals(4.66666, score, 0.0001d);
	}

	@Test
	public void testComplexMultipleTerms3() throws Exception {
		Index index = getIndex("b b x x x a x a x x a c");

		IterablePosting[] ips = new IterablePosting[3];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[2] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("c"));
		ips[0].next();
		ips[1].next();
		ips[2].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true, true }, new double[] { 1d, 1d }, false);
		assertEquals(6.999999, score, 0.0001d);
	}

	@Test
	public void testComplexMultipleTerms4() throws Exception {
		Index index = getIndex("b a x x b a x x c x x x b c x c x");

		IterablePosting[] ips = new IterablePosting[3];
		ips[0] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("a"));
		ips[1] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("b"));
		ips[2] = index.getInvertedIndex().getPostings(index.getLexicon().getLexiconEntry("c"));
		ips[0].next();
		ips[1].next();
		ips[2].next();

		AvgDistDsm dsm = new AvgDistDsm();

		double score = dsm.calculateDependence(ips, new boolean[] { true, true, true }, new double[] { 1d, 1d }, false);
		assertEquals(7.07407, score, 0.0001d);
	}

	private Index getIndex(String doc) throws Exception {
		return IndexTestUtils.makeIndexBlocks(new String[] { "D1" }, new String[] { doc });
	}

	@Before
	public void before() throws Exception {
		ApplicationSetup.setProperty("termpipelines", "");
	}
}
