package ch.akratash.viergewinnt.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * implementierung verschiedener Testverfahren zum schnellen validieren des eigenen Codes
 */

public class BoardTest {

	@Test
	public void testSetFirstStoneExceptTrue() {
		Board board = new Board();

		assertTrue(board.setStone(0));
	}

	/**
	 * Test um die Spielfeldgrenze zu testen
	 * erwartet wird False da -1 und 7 out of Field ist.
	 * [0 1 2 3 4 5 6] 
	 */
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

	/**
	 * @Test erwartet True für den letzten Spielstein in der Höhe zu setzen
	 * an Index 5
	 */
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

	/**
	 * @Test erwartet false für einen Stein der überhalb des Arrays plaziert werden will
	 * [0 1 2 3 4 5] 6
	 */
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

	/**
	 * 
	 * N N N N N N N 
	 * N N N N N N N
	 * R N N N N N N
	 * R Y N N N N N
	 * R Y N N N N N
	 * R Y N N N N N
	 */
	@Test
	public void testIsGameOverInSameColExpectTrue() {
		Board board = new Board();

		board.setStone(0); // RED
		board.setStone(1);// YELLOW
		board.setStone(0);
		board.setStone(1);
		board.setStone(0);
		board.setStone(1);
		board.setStone(0);// RED Wins

		assertTrue(board.isGameOver());
	}

	/**
	 * 0 0 0 0 0 0 0
	 * 0 0 0 0 0 0 0 
	 * R 0 0 0 0 0 0
	 * R Y 0 0 0 0 0
	 * R Y 0 0 0 0 0
	 * R Y 0 0 0 0 0
	 */
	@Test
	public void testRedWinsInSameColExpectTrue() {
		Board board = new Board();

		board.setStone(0); // RED
		board.setStone(1);// YELLOW
		board.setStone(0);
		board.setStone(1);
		board.setStone(0);
		board.setStone(1);
		board.setStone(0);// RED Wins

		assertEquals(Color.RED, board.getWinner());
	}

	/**
	 * 0 0 0 0 0 0 0
	 * 0 0 0 0 0 0 0 
	 * 0 Y 0 0 0 0 0
	 * R Y 0 0 0 0 0
	 * R Y 0 0 0 0 0l
	 * R Y R 0 0 0 0
	 */
	@Test
	public void testYellowWinsInSameColExpectTrue() {
		Board board = new Board();

		board.setStone(0); // RED
		board.setStone(1);// YELLOW
		board.setStone(0);
		board.setStone(1);
		board.setStone(0);
		board.setStone(1);
		board.setStone(2);
		board.setStone(1);// Yellow Wins

		assertEquals(Color.YELLOW, board.getWinner());
	}

	/**
	 * 0 0 0 0 0 0 0
	 * 0 Y 0 0 0 0 0 
	 * 0 Y 0 0 0 0 0
	 * 0 Y 0 0 0 0 0
	 * R Y 0 0 0 0 0
	 * R R R 0 0 0 0
	 */
	@Test
	public void testYellowWinsInSameColRedIsLowestExpectTrue() {
		Board board = new Board();

		board.setStone(1); // RED
		board.setStone(1); // YELLOW
		board.setStone(0);
		board.setStone(1);
		board.setStone(0);
		board.setStone(1);
		board.setStone(2);
		board.setStone(1); // Yellow Wins

		assertEquals(Color.YELLOW, board.getWinner());
	}
}
