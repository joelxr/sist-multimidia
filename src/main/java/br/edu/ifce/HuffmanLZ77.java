package br.edu.ifce;

import java.util.List;

import br.edu.ifce.huffman.Huffman;
import br.edu.ifce.lz77.LZ77;
import br.edu.ifce.lz77.Pointer;

public class HuffmanLZ77 {

	public static void main(String[] args) { // depois de implementado, passar string no args ou abrir dialog
		
		// encoding...
		
		LZ77 lz77 = new LZ77(4);

		Huffman huffmanLength = new Huffman();
		Huffman huffmanCharacter = new Huffman();
		Huffman huffmanPosition = new Huffman();
		
		String lengthColumn = "";
		String positionColumn = "";
		String characterColumn = "";

        List<Pointer> pointers = lz77.encode("abbabbabbbaababa");
        System.out.println("LZ77 Pointers:");
        System.out.println(pointers);
        
        for (Pointer p : pointers) {
			lengthColumn += Integer.toString(p.getLength());
			
			if (p.getPosition() != -1) {
				positionColumn += Integer.toString(p.getPosition());
			}
			else {
				characterColumn += p.getValue();
			}
		}
        
        System.out.println("Huffman aplicado a coluna Length:");
        System.out.println(huffmanLength.encode(lengthColumn));
        
        System.out.println("Huffman aplicado a coluna Position:");
        System.out.println(huffmanPosition.encode(positionColumn));

        System.out.println("Huffman aplicado a coluna Position (chars):");
        System.out.println(huffmanCharacter.encode(characterColumn));
        
        // decoding..
        
//        String result = lz77.decode(pointers);
//        System.out.println(result);

	}

}
