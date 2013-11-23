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

import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.Story;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;



public class OnlineHelper {


	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();

	// JSON Utilities
	private Gson gson = new Gson();
	
	private static String testServer = "http://cmput301.softwareprocess.es:8080/testing/";
	private static String ourServer = "http://cmput301.softwareprocess.es:8080/cmput301f13t12/";	
	
	
	  private Story initializeRecipe() {

          Story r = new Story();
          Fragment f = new Fragment();
          Fragment f1 = new Fragment();
          r.setId(1);
          r.setAuthor("Vincent");
          r.setStoryTitle("1st story ");
          r.setStartFragPos(0);
          
          f.setTitle("fragment1");
          f.setId(0);
          f1.setTitle("Second fRagment");
          
          r.addFragment(f);
          r.addFragment(f1);
         
          


          return r;
  }

	/**
	 * Consumes the POST/Insert operation of the service
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void insertStory(Story story) throws IllegalStateException, IOException{
		HttpPost httpPost = new HttpPost(ourServer+"stories/"+story.getId());
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(story));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setHeader("Accept","application/json");

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
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}

		try {
			entity.consumeContent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//httpPost.releaseConnection();
	}

	/**
	 * Consumes the Get operation of the service
	 */
	public Story getStory(int storyId){
		Story story = null;
		try{
			//HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/testing/lab02/999?pretty=1");//S4bRPFsuSwKUDSJImbCE2g?pretty=1
			HttpGet getRequest = new HttpGet(ourServer+storyId+"?pretty=1");//S4bRPFsuSwKUDSJImbCE2g?pretty=1
			getRequest.addHeader("Accept","application/json");

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>(){}.getType();
			// Now we expect to get a Recipe response
			ElasticSearchResponse<Story> esResponse = gson.fromJson(json, elasticSearchResponseType);
			// We get the recipe from it!
			story = esResponse.getSource();
			System.out.println(story.toString());
			//getRequest.releaseConnection();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return story;
	}
	
	public ArrayList<Story> getAllStories(){
		ArrayList<Story> allStories = new ArrayList<Story>();
		try{
			//HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/testing/lab02/999?pretty=1");//S4bRPFsuSwKUDSJImbCE2g?pretty=1
			HttpGet getRequest = new HttpGet(ourServer+"_search?pretty=1");//S4bRPFsuSwKUDSJImbCE2g?pretty=1
			getRequest.addHeader("Accept","application/json");

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>(){}.getType();
			// Now we expect to get a Recipe response
			ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
			// We get the recipe from it!
            for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
                Story story = s.getSource();
                allStories.add(story);
                System.out.println(story.toString());
            }
			
			//getRequest.releaseConnection();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return allStories;
	}
	
	public ArrayList<String> getAllStoryTitlesIdAuthor() throws ClientProtocolException, IOException{
		ArrayList<String> resultList = new ArrayList<String>();
		HttpPost searchRequest = new HttpPost(ourServer+"_search?pretty=1");
		String query =         "{\"query_string\" :  {\"fields\" : \"[storyTitle.*\"],\"query\" : \"" + "story" + "\"}}";
		StringEntity stringentity = new StringEntity(query);

		searchRequest.setHeader("Accept","application/json");
		searchRequest.setEntity(stringentity);

		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<String>>(){}.getType();
		ElasticSearchSearchResponse<String> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<String> s : esResponse.getHits()) {
			String story = s.getSource();
			resultList.add(story);
			System.err.println(story);
		}
		return resultList;
		//searchRequest.releaseConnection();
	}        
	

	/**
	 * search by keywords
	 */
	public ArrayList<Story> searchStories(String str) throws ClientProtocolException, IOException {
		//HttpGet searchRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/testing/lab02/_search?pretty=1&q=" +
		//                java.net.URLEncoder.encode(str,"UTF-8"));
		ArrayList<Story> resultList = new ArrayList<Story>();
		HttpGet searchRequest = new HttpGet(ourServer+"_search?pretty=1");
		searchRequest.setHeader("Accept","application/json");
		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>(){}.getType();
		ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
			Story story = s.getSource();
			resultList.add(story);
			System.err.println(story);
		}
		return resultList;
		//searchRequest.releaseConnection();
	}        

	/**
	 * advanced search (logical operators)
	 */
	public ArrayList<Story> searchsearchStories(String str) throws ClientProtocolException, IOException {
		ArrayList<Story> resultList = new ArrayList<Story>();
		HttpPost searchRequest = new HttpPost(ourServer+"_search?pretty=1");
		String query =         "{\"query\" : {\"query_string\" : {\"default_field\" : \"ingredients\",\"query\" : \"" + str + "\"}}}";
		StringEntity stringentity = new StringEntity(query);

		searchRequest.setHeader("Accept","application/json");
		searchRequest.setEntity(stringentity);

		HttpResponse response = httpclient.execute(searchRequest);
		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);

		Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>(){}.getType();
		ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);
		System.err.println(esResponse);
		for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
			Story story = s.getSource();
			resultList.add(story);
			System.err.println(story);
		}
		return resultList;
		//searchRequest.releaseConnection();
	}        


//	/**
//	 * update a field in a Story
//	 */
//	public void updateStories(String str) throws ClientProtocolException, IOException {
//		//HttpPost updateRequest = new HttpPost("http://cmput301.softwareprocess.es:8080/testing/lab02/1/_update");
//		HttpPost updateRequest = new HttpPost(testServer+"1/_update");
//		String query =         "{\"script\" : \"ctx._source." + str + "}";
//		StringEntity stringentity = new StringEntity(query);
//
//		updateRequest.setHeader("Accept","application/json");
//		updateRequest.setEntity(stringentity);
//
//		HttpResponse response = httpclient.execute(updateRequest);
//		String status = response.getStatusLine().toString();
//		System.out.println(status);
//
//		String json = getEntityContent(response);
//		//updateRequest.releaseConnection();
//	}        


	/**
	 * delete an entry specified by the id
	 */
	public void deleteStories() throws IOException {
		//HttpDelete httpDelete = new HttpDelete("http://cmput301.softwareprocess.es:8080/testing/lab02/1");
		HttpDelete httpDelete = new HttpDelete(ourServer+"/1");
		httpDelete.addHeader("Accept","application/json");

		HttpResponse response = httpclient.execute(httpDelete);

		String status = response.getStatusLine().toString();
		System.out.println(status);

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}
		entity.consumeContent();

		//httpDelete.releaseConnection();
	}

	/**
	 * get the http response and return json string
	 */
	String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json += output;
		}
		System.err.println("JSON:"+json);
		return json;
	}




	// Main Test
	public static void main(String [] args) throws IOException{
		System.out.println("?");
		OnlineHelper client = new OnlineHelper();
		/*                Recipe recipe = client.initializeRecipe();
            System.out.println("Recipe is -> "+ recipe.toString());

            //insert a recipe
            try {
                    client.insertRecipe(recipe);
            } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }

            //get a recipe
            client.getRecipe();*/
		//Story s = client.initializeRecipe();
		//client.insertStory(s);
		ArrayList<Story>stories  = client.getAllStories();
		client.getAllStoryTitlesIdAuthor();
		//client.deleteStories();
		

		//search by keywords
		/*                try {
                client.searchRecipes("egg");
            } catch (Exception e) {
                e.printStackTrace();
            }*/


		//advanced search
		/*                try {
                    client.searchsearchRecipes("cheese AND egg");
            } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }*/

		//update a new field
		/*                                try {
                    client.updateRecipes("directions = \\\"chop and fry\\\"\"");
            } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }*/

		//delete a recipe
		/*
		try {
			client.deleteRecipe();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}



}