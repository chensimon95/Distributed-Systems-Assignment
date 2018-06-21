import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Array;

public class ceshi{
	public static void main(String[] args){
		//BlockChain blockChain = new BlockChain();
		ArrayList<Block> blockChain = new ArrayList<Block>();
		bookStatus book = new bookStatus("name", "library", "don't lend", "ready", "null");
		Block block = new Block(book,"0");
		blockChain.add(block);
		//blockChain.addBlock(book);
		BlockChain blockChain1 = new BlockChain();
		System.out.println(blockChain1.getSize());
		
	}
}