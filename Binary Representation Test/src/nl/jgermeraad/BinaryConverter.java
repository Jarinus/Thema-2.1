package nl.jgermeraad;

public class BinaryConverter {
	private static String[] toConvert = {"1-245", "9999", "00001", "872441"};
	
	public static void main(String[] args) {
		for(String s : toConvert) {
			System.out.println(convertToBinary(s));
		}
		System.out.println(convertToString(convertToBinary(toConvert[0])));
	}
	
	private static String convertToString(String binary) {
		String temp = "";
		
		String[] bits = new String[binary.length() / 6];
		
		int i = binary.length() / 6;
		
		while(i > 0) {
			bits[i - 1] = binary.substring((i - 1) * 6, (i * 6));
			i--;
		}
		
		for(String s : bits) {
			int j = Integer.parseInt(s, 2);
			temp += (char) j;
		}
		
		return temp;
	}
	
	private static String convertToBinary(String s) {
		String temp = "";
		
		byte[] bytes = s.getBytes();
		
		for(byte b : bytes) {
			temp += Integer.toBinaryString(b);
		}
		
		return temp;
	}
}
