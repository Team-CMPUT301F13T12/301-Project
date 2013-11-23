package ualberta.g12.adventurecreator.online;

public class ElasticSearchResponse<T> {
	String _index;
	String _type;
	String _id;
	int _version;
	boolean exists;
	T _source;
	T fields;
	double max_score;
	public T getSource() {
		return _source;
	}
	
	public T getFields() {
		return fields;
	}
}