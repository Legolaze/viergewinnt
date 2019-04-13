package ch.akratash.viergewinnt.model;

public class Board {

	private Color[][] m_grid;
	private boolean m_gameOver;

	public Board() {
		m_gameOver = false;
		m_grid = new Color[7][6];

		for (Color[] column : m_grid) {
			for (Color field : column) {
				field = Color.NONE;
			}
		}

	}

}
