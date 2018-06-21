import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Array;

public class BlockChain{
	private ArrayList<Block> blockChain ;
	
	public BlockChain(){
		this.blockChain = new ArrayList<Block>();
		bookStatus book = new bookStatus("name", "library", "don't lend", "ready", "null");
		Block genesisBlock = new Block(book,"0");
		this.blockChain.add(genesisBlock);
		System.out.println("ready");
	}
	
	public void addBlock(bookStatus book){
		Block lastBlock = this.blockChain.get(blockChain.size()-1);
		Block nextBlock = new Block(book, lastBlock.getHash());
		this.blockChain.add(nextBlock);
	}
	
	public bookStatus getNewestBook(){
		Block lastBlock = this.blockChain.get(blockChain.size()-1);
		bookStatus newestBook = lastBlock.getBook();
		return newestBook;
	}
	
	public Block getBlock(int a){
		Block b = this.blockChain.get(0);
		return b;
	}
	
	public int getSize(){
		return this.blockChain.size();
	}
}