package br.edu.ifce.lz77;

public class Pointer {
    public String value;
    public int position;
    public int length;

    public Pointer(String value, int position, int length) {
        this.value = value;
        this.position = position;
        this.length = length;
    }

    @Override
    public String toString() {
        return "(" + length + "," + position + ")" + value;
    }

	public String getValue() {
		return value;
	}

	public int getPosition() {
		return position;
	}

	public int getLength() {
		return length;
	}
    
    
}