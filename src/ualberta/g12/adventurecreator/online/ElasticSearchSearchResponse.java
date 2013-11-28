
package ualberta.g12.adventurecreator.online;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents advanced search objects obtained from an elastic search. This is 
 * the expected type we get from a gson to json translation. 
 * 
 * The class provides mothods such as getHits which provides things we found in a search.
 * And also get sources which gets whole objects as well as get fields which gets the fields of
 * an object.
 * Based off of code provided by: https://github.com/rayzhangcl/ESDemo
 *
 * @param <T>
 */
public class ElasticSearchSearchResponse<T> {
    int took;
    boolean timed_out;
    transient Object _shards;
    Hits<T> hits;
    boolean exists;

    /**
     * getHits method is used to get the "hits" or results we get from a search. Each hit 
     * is a result that elastic search matched with our query. This method gets all the results
     * from a query.
     * @return
     */
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits.getHits();
    }
    
    /**
     * getSources obtains all the  object from a elastic search query.
     * This method should be used when we want to get all fields of an object (whole object)
     * from the elastic search
     * @return
     */
    public Collection<T> getSources() {
        Collection<T> out = new ArrayList<T>();
        for (ElasticSearchResponse<T> essrt : getHits()) {
            out.add(essrt.getSource());
        }
        return out;
    }

    /**
     * getFields is used to obtain  results when searching for only the fields in a query.
     * A field represnts a field in a object, and we want to get only these certain ones.
     * @return
     */
    public Collection<T> getFields() {
        Collection<T> out = new ArrayList<T>();
        for (ElasticSearchResponse<T> essrt : getHits()) {
            out.add(essrt.getFields());
        }
        return out;
    }

    
    public String toString() {
        return (super.toString() + ":" + took + "," + _shards + "," + exists + "," + hits);
    }
}
