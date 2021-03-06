import java.util.*;
public abstract class Crawler {
// Abstract class to crawl linked pages. Descendent crawlers should
// override the crawl(page) method to initiate a crawllestof linked
// pages. The class is intended only to work for locally stored files
// for whic validPageLink(page) should return true while non-local and
// web links will return false. This class makes use of the ArraySet
// class.

  protected ArraySet<String> foundPages;
  // Sets of pages that have been found or have been skipped due to
  // their being invalid or non-existent.

  protected ArraySet<String> skippedPages;
  

  public Crawler(){
    this.skippedPages = new ArraySet<String>();
    this.foundPages = new ArraySet<String>();
  }
  // Public constructor that creates an empty crawler.

  public abstract void crawl(String pageFileName);
  // Initiate a crawl on the given page.  Child classes should
  // override this.

  public List<String> foundPagesList(){
    return this.foundPages.asList();
  }
  // Return the unique pages that have been found so far and are
  // valid. Each item in the returned list should be unique and refer
  // to a valid file that exists.

  public List<String> skippedPagesList(){
    return this.skippedPages.asList();
  }
  // Return the unique pages that have been skipped so far. These may
  // be invalid as per validPageLink(..), non-existent files, or links
  // off of the local file system.

  public String foundPagesString(){
    String fpgs = "";
    for(String fpg: this.foundPages.asList()){
      fpgs += fpg;
      fpgs += "\n";
    }
    return fpgs;
  }
  // Return a string of pages that have been found so far.  Each page
  // is shown on its own line terminated with a \n

  public String skippedPagesString(){
    String spgs = "";
    for(String spg: this.skippedPages.asList()){
      spgs += spg;
      spgs += "\n";
    }
    return spgs;
  }
  // Return a string of pages that have been found so far.  Each page
  // is shown on its own line terminated with a \n

  public static boolean validPageLink(String pageFileName){
    //if the filename starts with these
    if(pageFileName.startsWith("http://")||
      (pageFileName.startsWith("https://"))||
       (pageFileName.startsWith("file://"))){
      return false;
    }
    //or ends with these, return false.
    //used startswith and endswith methods from string class
    if(!(pageFileName.endsWith(".html"))&&
      (!(pageFileName.endsWith(".HTML")))){
      return false;
    }
    else{
      return true;
    }
  }
    
  // Return true if the given pageFileName is valid and false
  // otherwise. Valid pages
  //
  // - Do not start with http://, https://, or file://
  // - End with the extension .html or .HTML
  //
  // Any file not meeting the above criteria should generate a false
  // return value.

}