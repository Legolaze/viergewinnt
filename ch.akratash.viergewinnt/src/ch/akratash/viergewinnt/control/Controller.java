package ch.akratash.viergewinnt.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

	public Label testLabel = null;

	@FXML
	private void handleTestButtonAction(ActionEvent event) {
		System.out.println("test auf console");
		testLabel.setText("und auf dem label");
	}
}
