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

		assertTrue(board.makeMove(0));
	}

	/**
	 * Test um die Spielfeldgrenze zu testen
	 * erwartet wird False da -1 und 7 out of Field ist.
	 * [0 1 2 3 4 5 6] 
	 */
	@Test
	public void testSetFirstStoneToLeftExpectFalse() {
		Board board = new Board();

		assertFalse(board.makeMove(-1));
	}

	@Test
	public void testSetFirstStoneToRightExpectFalse() {
		Board board = new Board();

		assertFalse(board.makeMove(7));
	}

	/**
	 * @Test erwartet True für den letzten Spielstein in der Höhe zu setzen
	 * an Index 5
	 */
	@Test
	public void testSetLastStoneInColExceptTrue() {
		Board board = new Board();

		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);

		assertTrue(board.makeMove(0));
	}

	/**
	 * @Test erwartet false für einen Stein der überhalb des Arrays plaziert werden will
	 * [0 1 2 3 4 5] 6
	 */
	@Test
	public void testSetLastStoneAlreadySetInColExceptFalse() {
		Board board = new Board();

		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0);
		board.makeMove(0); // this is the last

		assertFalse(board.makeMove(0));
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

		board.makeMove(0); // RED
		board.makeMove(1);// YELLOW
		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(0);// RED Wins

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

		board.makeMove(0); // RED
		board.makeMove(1);// YELLOW
		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(0);// RED Wins

		assertEquals(Color.RED, board.getWinner());
	}

	/**
	 * 0 0 0 0 0 0 0
	 * 0 0 0 0 0 0 0 
	 * 0 Y 0 0 0 0 0
	 * R Y 0 0 0 0 0
	 * R Y 0 0 0 0 0
	 * R Y R 0 0 0 0
	 */
	@Test
	public void testYellowWinsInSameColExpectTrue() {
		Board board = new Board();

		board.makeMove(0); // RED
		board.makeMove(1);// YELLOW
		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(1);// Yellow Wins

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

		board.makeMove(1); // RED
		board.makeMove(1); // YELLOW
		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(0);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(1); // Yellow Wins

		assertEquals(Color.YELLOW, board.getWinner());
	}

	@Test
	public void testRedWinsInSameRowExpectTrue() {
		Board board = new Board();

		board.makeMove(0); // RED
		board.makeMove(0);// YELLOW
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(2);
		board.makeMove(3);// RED Wins

		assertEquals(Color.RED, board.getWinner());
	}

	@Test
	public void testAfterGameOverWinnerCannotBeChanged() {
		Board board = new Board();

		board.makeMove(0); // RED
		board.makeMove(0); // YELLOW
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(2);
		board.makeMove(2);
		board.makeMove(3); // RED Wins
		board.makeMove(3); // YELLOW do not override winner

		assertEquals(Color.RED, board.getWinner());
	}

	@Test
	public void testRedWinsInSameRowExpectTrue2() {
		Board board = new Board();

		board.makeMove(3); // RED
		board.makeMove(3);// YELLOW
		board.makeMove(2);
		board.makeMove(2);
		board.makeMove(1);
		board.makeMove(1);
		board.makeMove(0);// RED Wins

		assertEquals(Color.RED, board.getWinner());
	}

	/**
	 * 0 0 0 0 0 0 0
	 * Y 0 0 0 0 0 0 
	 * R 0 0 0 0 0 0
	 * R R 0 0 0 0 0
	 * Y Y R 0 0 0 0
	 * Y R Y R Y 0 0
	 */
	@Test
	public void testRedWinsDiagonalBackslashExpectTrue() {
		Board board = new Board();

		board.makeMove(3); // RED
		board.makeMove(2);// YELLOW

		board.makeMove(1);
		board.makeMove(0);

		board.makeMove(2);
		board.makeMove(1);

		board.makeMove(1);
		board.makeMove(0);

		board.makeMove(0);
		board.makeMove(4);

		board.makeMove(0);
		board.makeMove(0);

		assertEquals(Color.RED, board.getWinner());
	}

	/**
	 * 0 0 0 0 0 0 0
	 * 0 0 0 0 0 0 0 
	 * 0 0 0 0 0 0 Y
	 * 0 0 0 0 0 Y R
	 * 0 0 0 0 Y R Y
	 * 0 0 0 Y R R R
	 */
	@Test
	public void testYellowWinsDiagonalForwardslashExpectTrue() {
		Board board = new Board();

		board.makeMove(4); // RED
		board.makeMove(3);// YELLOW

		board.makeMove(5);
		board.makeMove(4);

		board.makeMove(5);
		board.makeMove(5);

		board.makeMove(6);
		board.makeMove(6);

		board.makeMove(6);
		board.makeMove(6);

		assertEquals(Color.YELLOW, board.getWinner());
	}

}