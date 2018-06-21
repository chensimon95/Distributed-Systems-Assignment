import java.util.Date;
import java.util.*;
import java.security.MessageDigest;
//import com.google.gson.GsonBuilder;

public class Block{
	public String hash;
	public String preHash;
	public bookStatus book;
	private long timeStamp;
	
	public Block(bookStatus book, String preHash){
		this.book = book;
		this.preHash = preHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String getHash(){
		return hash;
	}
	
	public String getPreHash(){
		return preHash;
	}
	
	public bookStatus getBook(){
		return book;
	}
	
	public long timeStamp(){
		return timeStamp;
	}
	
	public String calculateHash(){
		String calhash = applySha256(preHash + Long.toString(timeStamp) + book.getName());
		return calhash;
	}
	
	public static String applySha256(String input){
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			
			StringBuffer hexString = new StringBuffer();
			for(int i = 0;i < hash.length; i++){
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1)hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/*public static String getJson(object o){
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}*/
}