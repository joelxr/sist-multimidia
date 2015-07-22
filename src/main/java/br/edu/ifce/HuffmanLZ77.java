package br.edu.ifce;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifce.huffman.Huffman;
import br.edu.ifce.lz77.LZ77;
import br.edu.ifce.lz77.Pointer;

public class HuffmanLZ77 {

	public static void main(String[] args) { // depois de implementado, passar string no args ou abrir dialog
		
		LZ77 lz77 = new LZ77();

		Huffman huffmanLength = new Huffman();
		Huffman huffmanCharacter = new Huffman();
		Huffman huffmanPosition = new Huffman();
		
		String lengthColumn = "";
		String positionColumn = "";
		String characterColumn = "";

        List<Pointer> pointers = lz77.encode("abbabbabbbaababa", 4);
        System.out.println("LZ77 Pointers:");
        System.out.println(pointers);
        
        for (Pointer p : pointers) {
			lengthColumn.concat(Integer.toString(p.getLength()));
			
			if (p.getPosition() != -1) {
				positionColumn.concat(Integer.toString(p.getPosition()));
			}
			else {
				characterColumn.concat(p.getValue());
			}
		}
        
        huffmanLength.encode(lengthColumn);
        huffmanCharacter.encode(characterColumn);
        huffmanPosition.encode(positionColumn);
        
        System.out.println("Huffman aplicado a coluna Length:");
        System.out.println(huffmanLength.getDictionary());
        System.out.println("Entropia: " + huffmanLength.calculateEntropy());
        System.out.println("Comprimento médio: " + huffmanLength.getAverageLength());
        
        System.out.println("Huffman aplicado a coluna Position:");
        System.out.println(huffmanPosition.getDictionary());
        System.out.println("Entropia: " + huffmanPosition.calculateEntropy());
        System.out.println("Comprimento médio: " + huffmanPosition.getAverageLength());
        
        System.out.println("Huffman aplicado a coluna Position (chars):");
        System.out.println(huffmanCharacter.getDictionary());
        System.out.println("Entropia: " + huffmanCharacter.calculateEntropy());
        System.out.println("Comprimento médio: " + huffmanCharacter.getAverageLength());
        
        String result = lz77.decode(pointers, 4);
        System.out.println(result);
        new Huffman().encode("123");

	}

}
