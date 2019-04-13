package ch.akratash.viergewinnt.model;

public class Board {

	private FieldState[][] m_grid;
	private boolean m_gameOver;

	public Board() {
		m_gameOver = false;
		m_grid = new FieldState[7][6];

		for (FieldState[] column : m_grid) {
			for (FieldState field : column) {
				field = FieldState.NONE;
			}
		}

	}

}
