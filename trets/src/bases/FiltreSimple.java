package bases;

import java.io.File;
public class FiltreSimple {
	 //Description et extension acceptee par le filtre
	   private String description;
	   private String extension;
	   //Constructeur a partir de la description et de l'extension acceptee
	   public FiltreSimple(String description, String extension){
	      if(description == null || extension ==null){
	         throw new NullPointerException("La description (ou extension) ne peut etre null.");
	      }
	      this.description = description;
	      this.extension = extension;
	   }
	   //Implementation de FileFilter
	   public boolean accept(File file){
	      if(file.isDirectory()) { 
	         return true; 
	      } 
	      String nomFichier = file.getName().toLowerCase(); 
	 
	      return nomFichier.endsWith(extension);
	   }
	      public String getDescription(){
	      return description;
	   }
}
