package br.edu.ifce;

import java.util.ArrayList;
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

		String lengthColumnEncoded = "";
		String positionColumnEncoded = "";
		String characterColumnEncoded = "";

		List<Pointer> pointersEncoding = lz77.encode("abbabbabbbaababa");
		System.out.println("LZ77 Pointers:");
		System.out.println(pointersEncoding);

		for (Pointer p : pointersEncoding) {
			lengthColumn += Integer.toString(p.getLength());
			positionColumn += Integer.toString(p.getPosition());

			if (p.getPosition() == -1) {
				characterColumn += p.getValue();
			}
		}

		lengthColumnEncoded = huffmanLength.encode(lengthColumn);
		positionColumnEncoded = huffmanPosition.encode(positionColumn);
		characterColumnEncoded = huffmanCharacter.encode(characterColumn);

		System.out.println("Huffman aplicado a coluna Length:");
		System.out.println(lengthColumnEncoded);
		System.out.println("Huffman aplicado a coluna Position:");
		System.out.println(positionColumnEncoded);
		System.out.println("Huffman aplicado a coluna Position (chars):");
		System.out.println(characterColumnEncoded);

		// decoding.. considerando em outra máquina
		Huffman huffmanPositionDecode = new Huffman();

		String positionColumnDecode = "";
		List<Pointer> pointersDecoding = new ArrayList<Pointer>();
		String valuePointer = "";
		int lengthPointer;
		int positionPointer;


		valuePointer = Character.toString(characterColumnEncoded.charAt(0));
		characterColumnEncoded = characterColumnEncoded.substring(1);


		//        String result = lz77.decode(pointers);
		//        System.out.println(result);

	}

}
