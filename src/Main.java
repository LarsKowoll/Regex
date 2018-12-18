import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
	String _regex;
	String _input;

	@Override
	public void start(Stage stage) {
		
		Scene scene = new Scene(new Group(), 450, 100);
		// set title for the stage
		stage.setTitle("Regex GUI");

		// Textfelder werden erstellt

		TextField regAusdruck = new TextField("Bitte den regulaeren Ausdruck eingeben");
		regAusdruck.setPrefWidth(300);
		TextField eingabe = new TextField("Bitte die zu pruefende Eingabe eingeben");
		eingabe.setPrefWidth(300);

		GridPane grid = new GridPane();
		
		// Arbeitet Enter ab
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				_regex = (regAusdruck.getText());
				// regAusdruckFiltern(_regAusdruck);
				_input = (eingabe.getText());
				boolean regAusdruck = regAusdruckpruefen(_input);
				if (regAusdruck) {
					eingabe.setStyle("-fx-control-inner-background: green");
				} else {
					eingabe.setStyle("-fx-control-inner-background: red");
				}
			}
		};

		// Wenn Enter betaetigt wurde
		eingabe.setOnAction(event);
		
		grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(new Label("Regex: "), 0, 0);
	    grid.add(regAusdruck, 1, 0);
	    grid.add(new Label("Input: "), 0, 1);
	    grid.add(eingabe, 1, 1);
	    
	    Group root = (Group) scene.getRoot();
	    root.getChildren().add(grid);

		stage.setScene(scene);

		stage.show();
	}

	public void regAusdruckFiltern(String regAusdruck) {
		_regex = regAusdruck.replaceAll("\\(|\\)", "");
	}

	public boolean regAusdruckpruefen(String eingabe) {
		Pattern pattern = Pattern.compile(_regex);
		Matcher matcher = pattern.matcher(eingabe);
		boolean regAusdruck = matcher.matches();
		return regAusdruck;
	}

	public static void main(String[] args) {
		launch(args);
	}
}