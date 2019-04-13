package ch.akratash.viergewinnt.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testSetFirstStoneExceptTrue() {
		Board board = new Board();

		assertTrue(board.setStone(0));
	}

	@Test
	public void testSetFirstStoneToLeftExpectFalse() {
		Board board = new Board();

		assertFalse(board.setStone(-1));
	}

	@Test
	public void testSetFirstStoneToRightExpectFalse() {
		Board board = new Board();

		assertFalse(board.setStone(7));
	}

	@Test
	public void testSetLastStoneInColExceptTrue() {
		Board board = new Board();

		board.setStone(0);
		board.setStone(0);
		board.setStone(0);
		board.setStone(0);
		board.setStone(0);

		assertTrue(board.setStone(0));
	}

	@Test
	public void testSetLastStoneAlreadySetInColExceptFalse() {
		Board board = new Board();

		board.setStone(0);
		board.setStone(0);
		board.setStone(0);
		board.setStone(0);
		board.setStone(0);
		board.setStone(0); // this is the last

		assertFalse(board.setStone(0));
	}

}
