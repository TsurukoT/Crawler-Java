// my arrayset java
import java.util.*;

public class ArraySet<T extends Comparable<T>>{
// A simple implementation of a Set backed by an array.  As a Set,
// instances track *unique* items so that no duplicates occur.  This
// implementation should keep the underlying array sorted and use
// binary search to quickly identify if items are present or absent to
// maintain uniqueness.  To maintain this, items that go into the set
// must implement the Comparable interface so that they have a
// compareTo(..) method and are compatible with library search and
// sort methods.
// 
// Good choices for private fields are an ArrayList which will manage
// the underlying array size automatically.
  

  private ArrayList <T> data;

  public ArraySet(){
    data = new ArrayList<T>();
  }
  // Create an empty ArraySet

  public int size(){
    return data.size();
  }
  // Return the size of the set which is the number of unique items in
  // it.

  public List<T> asList(){
    Collections.sort(data);
    List<T> emptyl = data;
    return emptyl;
  }
  // Return the contents of the set as a list. The return list does
  // not have to be distinct from the lists pointed to by internal
  // fields of the set (no deep copies need to be made).

  
  //Here used the binary search in collections class, if the return integer is a postitive index return true
  //if is negative, its not in the arraylist, so return false. 
  public boolean contains(T query){
    
    if(Collections.binarySearch(data, query) >= 0){
      
      return true;
    }
    else{
      return false;
    }
  }
    
    
  // Return true if the query item is present in the set and false
  // otherwise. This method should use binary search to efficiently
  // determine presence or absence.

  public boolean add(T item){
    if (item.equals(null)){
      throw new RuntimeException();
    }
    if (data.contains(item) == true){
      return false;
    }
    else{
      data.add(item);
      Collections.sort(data);
      return true;
    }
  }
    
    
  // Ensure the specified item is present in the set.  Maintain the
  // uniqueness of items in the set: do not add duplicates which
  // would be equal to one another.  If the given item is added to
  // the set, return true.  If the item is already present so that the
  // set does not change size, return false.  Throw a RuntimeException
  // in the event a item is null: ArraySet does not support null
  // items.

  public T get(T query){
    if(!(this.contains(query))){
      return null;
    }
    else{
      return query;
    }
  }
      
    
  // Retrieve an item in the set that is equal to the query item.  If
  // no item in the set is equal to the query, return null.

  public String toString(){
    Collections.sort(data);
    return this.asList().toString();
}
}

