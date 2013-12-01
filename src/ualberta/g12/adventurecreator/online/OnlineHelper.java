package ualberta.g12.adventurecreator.online;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import ualberta.g12.adventurecreator.data.Story;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * This is the class in which all our backend web based service functions are
 * located. OnlineHelper provides interfaces with the web server to obtain, edit
 * and retrieve information from. This class is based off of code found
 * here:https://github.com/rayzhangcl/ESDemo
 * 
 * @author Vincent
 */
public class OnlineHelper {


    // Http Connector
    private HttpClient httpclient = new DefaultHttpClient();


    // JSON Utilities
    private Gson gson = new Gson();


    // Addresses for team 12s web server and also a public test server
    private static String testServer = "http://cmput301.softwareprocess.es:8080/testing/";
    private static String ourServer = "http://cmput301.softwareprocess.es:8080/cmput301f13t12/";


    /**
     * insertStory is used to insert a story into our webserver. However, it is
     * also used to update a story because of how the webserver works. The story
     * inserted should have a unique id before being inserted. As our webserver
     * only allows one instance of an object with certain type and id inserting
     * an object with an already existing id will overwrite it.
     * 
     * @param story
     * @throws IllegalStateException
     * @throws IOException
     */
    public void insertStory(Story story) throws IllegalStateException, IOException {
        HttpPost httpPost = new HttpPost(ourServer + "stories/" + story.getId());
        StringEntity stringentity = null;
        try {
            stringentity = new StringEntity(gson.toJson(story));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        httpPost.setHeader("Accept", "application/json");


        httpPost.setEntity(stringentity);
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        String status = response.getStatusLine().toString();
        System.out.println(status);
        HttpEntity entity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
        String output;
        System.err.println("Output from Server -> ");
        /*
         * while ((output = br.readLine()) != null) { //
         * System.err.println(output); }
         */


        try {
            entity.consumeContent();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // httpPost.releaseConnection();
    }


    /**
     * Gets the story by the id from our server. An Id should be unique to that
     * story object. All ids are generated by looking at the title and author of
     * a story. If an id is not in the server then the resulting Story object
     * should be NULL.
     * 
     * @param storyID is the id in which we are searching for ( should be unique
     *            also)
     * @return the story object with the associated id
     */
    public Story getStory(int storyId) {
        Story story = null;
        try {
            HttpGet getRequest = new HttpGet(ourServer + "stories/" + storyId + "?pretty=1");
            getRequest.addHeader("Accept", "application/json");


            HttpResponse response = httpclient.execute(getRequest);


            String status = response.getStatusLine().toString();
            System.out.println(status);


            String json = getEntityContent(response);


            // We have to tell GSON what type we expect
            Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {
            }.getType();
            // Now we expect to get a story response
            ElasticSearchResponse<Story> esResponse = gson
                    .fromJson(json, elasticSearchResponseType);
            // We get the story from it!
            story = esResponse.getSource();


        } catch (ClientProtocolException e) {


            e.printStackTrace();


        } catch (IOException e) {


            e.printStackTrace();
        }


        return story;
    }


    /**
     * This method is used to get ALL stories in our web server. Warning: this
     * can be very slow if stories contain many pictures and media, or if there
     * are a lot of stories.
     * 
     * @return an ArrayList<Story> which contains all stories in the webserver
     */
    public ArrayList<Story> getAllStories() {
        ArrayList<Story> allStories = new ArrayList<Story>();
        try {
            // TODO quick fix right now only displays 10 (default ES behaviour)
            HttpPost searchRequest = new HttpPost(ourServer + "_search?pretty=1&size=1000");
            searchRequest.addHeader("Accept", "application/json");


            HttpResponse response = httpclient.execute(searchRequest);


            String status = response.getStatusLine().toString();
            System.out.println(status);


            String json = getEntityContent(response);


            // We have to tell GSON what type we expect
            Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {
            }.getType();


            ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,
                    elasticSearchSearchResponseType);


            for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
                Story story = s.getSource();
                allStories.add(story);
                System.out.println(story.toString());
            }


        } catch (ClientProtocolException e) {


            e.printStackTrace();


        } catch (IOException e) {


            e.printStackTrace();
        }


        return allStories;
    }


    /**
     * Obtains all stories from the server but is only a partial representation
     * of the object Will search only the storyTitle, author and Id of a story
     * This method is used to quickly find all the different stories in our
     * server without having to also downloading all the potentially large media
     * associated with it
     * 
     * @return an ArraList of partial stories with only the author, title and id
     *         fields filled in
     * @throws ClientProtocolException
     * @throws IOException
     */
    public ArrayList<Story> getAllStoryTitlesIdAuthor() throws ClientProtocolException, IOException {


        ArrayList<Story> resultList = new ArrayList<Story>();


        // lets prepare our query to only get our required fields
        HttpPost searchRequest = new HttpPost(ourServer + "_search?pretty=1&size=1000");
        String query = "{\"fields\" : [\"storyTitle\",\"author\", \"id\"], \"query\" :{ \"match_all\" : {}    }}";
        StringEntity stringentity = new StringEntity(query);


        // set our header let it know json!
        searchRequest.setHeader("Accept", "application/json");
        searchRequest.setEntity(stringentity);


        // get our json string back ..
        HttpResponse response = httpclient.execute(searchRequest);
        String json = getEntityContent(response);


        Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {
        }.getType();
        ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,
                elasticSearchSearchResponseType);


        // now we want to change all our response objects back into stories by
        // obtaining the fields we got back
        for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
            Story story = s.getFields();
            resultList.add(story);
        }
        return resultList;
    }


    /**
     * checks if there is already a story with the same id in our database This
     * function can be used to check for a storyId already on the system to
     * prevent the case of accidental overwriting
     * 
     * @param id int value which is our story id ( should be unique)
     * @return true if a story with id exists, else false
     * @throws ClientProtocolException
     * @throws IOException
     */
    public boolean checkId(int id) throws ClientProtocolException, IOException {


        // lets prepare our query to only get our required fields
        HttpPost searchRequest = new HttpPost(ourServer + "_search?pretty=1");
        String query = "{\"fields\" : [\"id\"], \"query\" :{ \"term\" : { \"id\" : \"" + id
                + "\"}    }  }";
        StringEntity stringentity = new StringEntity(query);


        // set our header let it know json!
        searchRequest.setHeader("Accept", "application/json");
        searchRequest.setEntity(stringentity);


        // get our json string back ..
        HttpResponse response = httpclient.execute(searchRequest);
        System.out.println(response.getStatusLine());
        String json = getEntityContent(response);
        Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {
        }.getType();
        ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,
                elasticSearchSearchResponseType);
        
        return (esResponse.getHits().size() > 0);
    }


    /**
     * This method searches our web server for the input string on fields author
     * and story. We used the input string "search" to in an elastic search
     * query for fields storyTitle and also author to find hits that match the
     * "search" string. Method should be used for some search functionality in
     * our main program
     * 
     * @param search input string
     * @return return a list of stories with input string in the author or story
     * @throws ClientProtocolException
     * @throws IOException
     */
    public ArrayList<Story> searchsearchStories(String search) throws ClientProtocolException,
            IOException {
        ArrayList<Story> resultList = new ArrayList<Story>();
        HttpPost searchRequest = new HttpPost(ourServer + "_search?pretty=1&size=1000");
        String query = "{\"query\" : {\"query_string\" : {\"fields\" : [\"storyTitle\",\"author\"], \"query\": \""
                + search + "\"} } }";
        StringEntity stringentity = new StringEntity(query);


        searchRequest.setHeader("Accept", "application/json");
        searchRequest.setEntity(stringentity);


        HttpResponse response = httpclient.execute(searchRequest);
        String status = response.getStatusLine().toString();
        System.out.println(status);


        String json = getEntityContent(response);


        Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {
        }.getType();
        ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,
                elasticSearchSearchResponseType);
        for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
            Story story = s.getSource();
            resultList.add(story);
        }
        return resultList;
    }


    /**
     * deleteStories deletes ALL stories with the same story ID. If there were a
     * case where there was more than one story with the same ID (which should
     * not happen) ALL stories with the same ID will be deleted from our web
     * server.
     * 
     * @param storyId the id of the story that is to be deleted
     * @throws IOException
     */
    public void deleteStories(int storyId) throws IOException {
        HttpDelete httpDelete = new HttpDelete(ourServer + "/stories/" + storyId);
        httpDelete.addHeader("Accept", "application/json");


        HttpResponse response = httpclient.execute(httpDelete);


        String status = response.getStatusLine().toString();
        System.out.println(status);


        HttpEntity entity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
        String output;
        System.err.println("Output from Server -> ");
        /*
         * while ((output = br.readLine()) != null) { //
         * System.err.println(output); }
         */
        entity.consumeContent();


    }


    /**
     * get the http response and return json string
     */
    String getEntityContent(HttpResponse response) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        String output;
        // System.err.println("Output from Server -> ");
        String json = "";
        while ((output = br.readLine()) != null) {
            System.err.println(output);
            json += output;
        }
        return json;
    }


}
