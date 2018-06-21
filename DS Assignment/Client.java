import java.util.*;
import java.io.*;
import java.net.*;

class Client{
	public static void main(String[] args) throws Exception{
		BlockChain blockChain = new BlockChain();
		try {
			Socket s = new Socket("localhost", 12345);
			//ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			//ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			while(true){
				System.out.println("Hello");
				Thread t1 = new ClientToSrvThread(blockChain,s);
				Thread t2 = new SrvToClientThread(blockChain,s);

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

class ClientToSrvThread extends Thread{
	BlockChain blockChain;
	Socket s;
	
	ClientToSrvThread(BlockChain blockChain, Socket s){
		this.blockChain = blockChain;
		this.s = s;
	}
	
	public void run() {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			
			System.out.println("What you want to change?(name? library? lend? delivery? request?)");
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
			Block lastBlock = blockChain.getBlock(blockChain.getSize()-1);
			bookStatus book = lastBlock.getBook();
			if(choice.equals("name")){
				System.out.println("Write new name");
				Scanner sc1 = new Scanner(System.in);
				String ans = sc1.nextLine();
				book.setName(ans);
				oos.writeObject(book);
				
				blockChain.addBlock(book);
			}else if(choice.equals("library")){
				System.out.println("Write new library");
				Scanner sc1 = new Scanner(System.in);
				String ans = sc1.nextLine();
				book.setLibrary(ans);
				oos.writeObject(book);
				blockChain.addBlock(book);
			}else if(choice.equals("lend")){
				System.out.println("Write new lend");
				Scanner sc1 = new Scanner(System.in);
				String ans = sc1.nextLine();
				book.setLend(ans);
				oos.writeObject(book);
				blockChain.addBlock(book);
			}else if(choice.equals("delivery")){
				System.out.println("Write new delivery");
				Scanner sc1 = new Scanner(System.in);
				String ans = sc1.nextLine();
				book.setDelivery(ans);
				oos.writeObject(book);
				blockChain.addBlock(book);
			}else if(choice.equals("request")){
				System.out.println("Write new request");
				Scanner sc1 = new Scanner(System.in);
				String ans = sc1.nextLine();
				book.setRequest(ans);
				oos.writeObject(book);
				blockChain.addBlock(book);
			}else{
				System.out.println("Don't have this options");
			}
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
}

class SrvToClientThread extends Thread{
	Socket s;
	BlockChain blockChain;
	
	SrvToClientThread(BlockChain blockChain, Socket s){
		this.s = s;
		this.blockChain = blockChain;
	}
	
	public void run(){
		
		try{
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			bookStatus book = (bookStatus)ois.readObject();
			System.out.println("name: " + book.getName() + " library: " + book.getLibrary() + " lend: " + book.getLend() + " delivery: " + book.getDelivery() + " request: " + book.getRequest());
			blockChain.addBlock(book);
			System.out.println("hash: " + blockChain.getBlock(blockChain.getSize()-1).getHash());
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
}