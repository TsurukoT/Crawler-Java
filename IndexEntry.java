import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.io.*;
import java.util.*;
public class IndexEntry implements Comparable<IndexEntry>{

  protected String term;

  protected ArraySet<String> pages;

  public IndexEntry(String term){
    this.term = term.toLowerCase();
    pages = new ArraySet<String>();
  }
  // Create an empty IndexEntry associated with the given term

    public String getTerm(){
      return this.term;
    }
  // Return a the term associated associated with this entry

    public List<String> getPages(){
      return this.pages.asList();
    }
  // Return a list of pages associated with this entry

    public boolean containsPage(String pageFileName){
      if (pages.contains(pageFileName)){
        return true;
      }
      return false;
    }
  // Return true if the the given page is already present in this
  // entry and false otherwise.

    public boolean addPage(String filename){
      if (containsPage(filename)){
        return false;
      }
      else{
        this.pages.add(filename);
        
        return true;
      }
    }
  // Add the given page to this entry returning true if it was not
  // present and is therefore a new addition; return false if it is
  // already present

    public int compareTo(IndexEntry that){
      //used the compareTo method in the String class to return an integer. 
      String thatterm = that.term;
      return this.term.compareTo(thatterm);
    }
      
  // Compare the entry to that entry. The comparison is based entirely
  // on the term field which should use the built-in String comparison
  // methods to generate the difference.

    public boolean equals(Object other){
      //if not an instance of IndexEntry return false
      if(!(other instanceof IndexEntry)){
        return false;
      }
      //else convert it to an instance of IndexEntry and compare the term
      IndexEntry otherindexentry = (IndexEntry) other;
      if(this.term.compareTo(otherindexentry.term) != 0){
        return false;
      }
      return true;
    }
      
      
  // Return whether the other object is equal to this IndexEntry.
  // This is only the case when other is also an IndexEntry and has an
  // equal term field.

    public String toString(){
      String entrystr = "";
      entrystr += "@ " + this.term;
      entrystr += "\n";
      for(String estr: this.pages.asList()){
      entrystr += estr;
      entrystr += "\n";
    }
    return entrystr;
  }
  // Return a string representation of this entry. The format of the string is

}