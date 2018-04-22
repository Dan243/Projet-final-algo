package Affichage_graphique;



import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'afficher des messages à l'utilisateur
 * @author Etonam et Dan
 *
 */
public class Message {

  private String MessageDebut;
  private String MessageFin;
  private String messagePrint="";
  private List<String> Type1;

  

 public Message() {
	 
	Type1=new ArrayList<String>();
	MessageDebut="";
	MessageFin="";
	  
 }
 

public String getMessageDebut() {
	return MessageDebut;
}
public void setMessageDebut(String MessageDebut) {
	this.MessageDebut = MessageDebut;
}
public String getMessageFin() {
	return MessageFin;
}
public void setMessageFin(String MessageFin) {
	this.MessageFin = MessageFin;
}


public List<String> getType1() {
	return Type1;
}


public void setType1(List<String> type1) {
	Type1 = type1;
}

public String getMessagePrint() {
	return messagePrint;
}


public void setMessagePrint(String messagePrint) {
	this.messagePrint = messagePrint;
}


}
