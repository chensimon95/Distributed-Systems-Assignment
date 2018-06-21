import java.io.*;
import java.util.*;
import java.net.*;

public class Server{
	public static void main(String args[]){
      try{
        ServerSocket ss = new ServerSocket(12345);
		
		while(true){
			Socket s1 = ss.accept();
			Socket s2 = ss.accept();
			Thread t1 = new Thread(new ServerThread1( s1,s2));
			Thread t2 = new Thread(new ServerThread2( s1,s2));
			t1.start();
            t2.start();

            t1.join();
            t2.join();
			
		}

		
      }catch(Exception e){
        System.out.println(e);
      }
  }
}

class ServerThread1 extends Thread{
	Socket s1;
	Socket s2;
    public ServerThread1(Socket s1, Socket s2){
        this.s1 = s1;
		this.s2 = s2;
    }
	
	public void run(){
		try{
			ObjectInputStream ois1 = new ObjectInputStream(s1.getInputStream());
			ObjectOutputStream oos2 = new ObjectOutputStream(s2.getOutputStream());
			bookStatus book = (bookStatus)ois1.readObject();
			oos2.writeObject(book);
		}catch(Exception e){
        System.out.println(e);
      }
	}
}

class ServerThread2 extends Thread{
	Socket s1;
	Socket s2;
    public ServerThread2(Socket s1, Socket s2){
        this.s1 = s1;
		this.s2 = s2;
    }
	
	public void run(){
		try{
			ObjectInputStream ois2 = new ObjectInputStream(s2.getInputStream());
			ObjectOutputStream oos1 = new ObjectOutputStream(s1.getOutputStream());
			bookStatus book = (bookStatus)ois2.readObject();
			oos1.writeObject(book);
		}catch(Exception e){
        System.out.println(e);
      }
	}
}