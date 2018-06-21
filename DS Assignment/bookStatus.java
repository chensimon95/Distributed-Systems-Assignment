import java.io.Serializable;

public class bookStatus implements Serializable{
	private String name;
	private String library;
	private String lend;
	private String delivery;
	private String request;
	
	public bookStatus(){
		super();
	}
	
	public bookStatus(String name, String library,	String lend, String delivery, String request){
		super();
		this.name = name;
		this.library = library;
		this.lend = lend;
		this.delivery = delivery;
		this.request = request;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getLibrary(){
		return library;
	}
	
	public void setLibrary(String library){
		this.library = library;
	}
	
	public String getLend(){
		return lend;
	}
	
	public void setLend(String lend){
		this.lend = lend;
	}
	
	public String getDelivery(){
		return delivery;
	}
	
	public void setDelivery(String delivery){
		this.delivery = delivery;
	}
	
	public String getRequest(){
		return request;
	}
	
	public void setRequest(String request){
		this.request = request;
	}
	
	public String show(){
		return "name + ' ' + library + ' ' + lend + ' ' + delivery + ' ' + request + '.'";
	}
}