import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.control.Label; 
import javafx.stage.Stage; 

public class Main extends Application {
	String _regex;
	String _input;

  @Override
  public void start(Stage stage) {
	  // set title for the stage 
	  stage.setTitle("Regex GUI"); 

      // Textfelder werden erstellt
      
      TextField regAusdruck = new TextField("Bitte den regulaeren Ausdruck eingeben"); 
      TextField eingabe = new TextField("Bitte die zu pruefende Eingabe eingeben");
      
      TilePane r = new TilePane(); 

      // Arbeitet Enter ab
      EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
        	  _regex = (regAusdruck.getText()); 
             // regAusdruckFiltern(_regAusdruck);
        	  _input = (eingabe.getText());
              boolean regAusdruck = regAusdruckpruefen(_input);
              if (regAusdruck) {
            	  eingabe.setStyle("-fx-text-fill: green");            	  
              } else {
            	  eingabe.setStyle("-fx-text-fill: red"); 
              
              }
          } 
      }; 

      // Wenn Enter betaetigt wurde
      eingabe.setOnAction(event); 

      r.getChildren().add(regAusdruck); 
      r.getChildren().add(eingabe); 

      Scene sc = new Scene(r, 500, 100); 

      stage.setScene(sc); 

      stage.show(); 
  }
  
  public void regAusdruckFiltern(String regAusdruck) {
	  _regex = regAusdruck.replaceAll("\\(|\\)","");
	  }
  
  public boolean regAusdruckpruefen(String eingabe) {
	  Pattern pattern = Pattern.compile(_regex);
	  Matcher matcher = pattern.matcher(eingabe);
	  boolean regAusdruck = matcher.matches();
	  return regAusdruck;
  }
 
      
  public static void main(String[] args) {
	    Application.launch(args);
	  }
}