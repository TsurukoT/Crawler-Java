import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.io.*;
import java.util.*;
public class IterativeCrawler extends Crawler {
// An implementation of a crawler which does not use recursion and
// instead uses internal storage to track the pages that have yet to
// be visited.

  protected ArraySet<String> pendingPages;
  // A list of pages that remain to be visited. As a single page is
  // visited, any valid links that are found are added to this list to
  // be visited later.  This list should only contain valid, existing
  // pages which can be visited and have not yet been visited.

  public IterativeCrawler(){
    super();
    pendingPages = new ArraySet<String> ();
  }

  public void crawl(String pageFileName){
    addPendingPage(pageFileName);
    foundPages.add(pageFileName);
    if (pendingPagesSize() > 0){
      //start callign when pending Pages is bigger than 0, crawlRemaining and crawlNext needs pendingPages to execute code
      //crawlRemaing() will finish the crawl until the there is not pages in pendignPagesList.
      crawlRemaining();
    }
    //crawlRemaining();
  }
  
  // Master crawl method which will start at the given page and visit
  // all reachable pages.  This method should call the
  // crawlRemaining() method as its last action.

  public void crawlRemaining(){
    //as long as the pendingList size is not 0, keep calling crawlNextPage()
    //everytime calls crawlNextPage, pendingPages size will reduce. 
    List <String> pendingplist = pendingPages.asList();
    while (pendingPagesSize() > 0){
      crawlNextPage();
    }
  }
  // Enter a loop that crawls individual pages until there are no
  // pending pages remaining.

  public void addPendingPage(String pageFileName){
    this.pendingPages.add(pageFileName);
  }
// did not check if the given string is exist and is a valid file name yet!!!

  public int pendingPagesSize(){
    return this.pendingPages.size();
  }

  public String pendingPagesString(){
    String ppgs = "";
    for(String ppg: this.pendingPages.asList()){
      ppgs += ppg;
      ppgs += "\n";
    }
    return ppgs;
  }

  public void crawlNextPage(){
    //basically same with the crawl method in RecursiveCrawler class. 
    String linkedfilename= "";
    String linkedPage = "";
    //get the last file in the Pendingpage list and set it to filename
    String filename = pendingPages.asList().get(pendingPagesSize()-1);
    //add it to foundPages and remove it from pendingPages
    foundPages.add(filename);
    pendingPages.asList().remove(pendingPagesSize()-1);
    try{
      //parse the foundfilename
      //in the links found on the file, if it is valid add and exists and is not in skipped, found, pending list yet, add it to pending list
      //otherwise, same with crawls() method in RecursiveCrawler Class. 
      File input = new File(filename);
      Document doc = Jsoup.parse(input, "UTF-8");
      Elements links = doc.select("a[href]");
      for(Element element : links){
        linkedPage = element.attr("href");
        linkedfilename = Util.relativeFileName(filename, linkedPage);
        File linkedFile = new File(linkedfilename);
        if((this.foundPages.contains(linkedfilename)) || ( this.skippedPages.contains(linkedfilename)) || (this.pendingPages.contains(linkedfilename))){
          continue;
        }
        if(super.validPageLink(linkedfilename) == false || linkedFile.exists() == false){
          skippedPages.add(linkedPage);
      }
        else if(!(this.foundPages.contains(linkedfilename)) || !(this.skippedPages.contains(linkedfilename)) || !(this.pendingPages.contains(linkedfilename))){
          addPendingPage(linkedfilename);
        }
    }
    }
    catch( Exception e){
      skippedPages.add(linkedPage);
    }
  }
        
  
  // Crawl a single page which is retrieved and removed from the list
  // of pending pages.  Parse the retrieved page's contents using
  // library functions in the JSoup library.  Examine all links on the
  // page. Any valid page that exists and is unvisited should be added
  // to the pending list. By the time crawlNextPage() finishes, one
  // additional page should be returned the foundPagesXX() methods
  // while the pending pages list may have grown substantially.
  //
  // See the spec for additional implementation details.
/*
  public static void main(String args[]) throws Exception{
    IterativeCrawler c = new IterativeCrawler();
    c.addPendingPage("crawls/small-site/start.html");
    c.crawlRemaining();
    System.out.println("Pending:" + c.pendingPagesString());
    System.out.println(c.foundPages);
    System.out.println(c.skippedPages);
  }*/
  // Optional main method for your own testing

}
