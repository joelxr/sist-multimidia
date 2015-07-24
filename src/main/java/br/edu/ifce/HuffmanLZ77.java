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
		System.out.println("## String original: abbabbabbbaababa");
		System.out.println("LZ77 Pointers encoded:");
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

		System.out.println("\n## Huffman aplicado a coluna Length:");
		System.out.println(lengthColumnEncoded);
		System.out.println("\n## Huffman aplicado a coluna Position:");
		System.out.println(positionColumnEncoded);
		System.out.println("\n## Huffman aplicado a coluna Position (chars):");
		System.out.println(characterColumnEncoded);

		// decoding..
		String positionColumnDecode = huffmanPosition.decode(positionColumnEncoded);
		String characterColumnDecode = huffmanCharacter.decode(characterColumnEncoded);
		String lengthColumnDecode = huffmanLength.decode(lengthColumnEncoded);
		System.out.println("\n## Coluna Position decoded: " + positionColumnDecode);
		System.out.println("\n## Coluna Character decoded: " + characterColumnDecode);
		System.out.println("\n## Coluna Length decoded: " + lengthColumnDecode);

		List<Pointer> pointersDecoding = new ArrayList<Pointer>();
		String valuePointer = "";
		int lengthPointer;
		int positionPointer;

		do {
			// gambs pra acomodar o "-1"
			String s = "";
			if(positionColumnDecode.charAt(0) == '-') {
				s = positionColumnDecode.substring(0, 2);
				positionColumnDecode = positionColumnDecode.substring(2);
				positionPointer = Integer.parseInt(s);

				valuePointer = Character.toString(characterColumnDecode.charAt(0));
				characterColumnDecode = characterColumnDecode.substring(1);
			}
			else {
				positionPointer = Integer.parseInt( Character.toString(positionColumnDecode.charAt(0)) );
				positionColumnDecode = positionColumnDecode.substring(1);

				valuePointer = "";
			}
			lengthPointer = Integer.parseInt( Character.toString(lengthColumnDecode.charAt(0)) );
			lengthColumnDecode = lengthColumnDecode.substring(1);

			pointersDecoding.add(new Pointer(valuePointer, positionPointer, lengthPointer));
		} while(positionColumnDecode.length() > 0);

		String resultFinal = lz77.decode(pointersDecoding);
		System.out.println("\n## String original: " + resultFinal);

	}

}
