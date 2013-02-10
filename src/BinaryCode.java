
import java.util.Arrays;

public class BinaryCode {


	public String[] decode(String message) {
		
		if (message.length() > 50 || message.length() < 1) return new String[] {"NONE", "NONE"};
		for (int i=0; i<message.length(); i++) 
			if ((Character.getNumericValue(message.charAt(i)) < 0) || 
				(Character.getNumericValue(message.charAt(i)) > 3)) return new String[] {"NONE", "NONE"};
		
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		boolean s1Valid = false;
		boolean s2Valid = false;
		int messageLength = message.length();
		int lastIndex = messageLength - 1;
		int p1[] = new int[messageLength];
		int p2[] = new int[messageLength];

		p1[0] = 0;
		s1.append(p1[0]);
		for (int i=0; i<lastIndex; i++) {
			int x = Character.getNumericValue(message.charAt(i));
			if (i==0) p1[i+1] = x - p1[i];
			else p1[i+1] = x - p1[i] - p1[i-1];
			s1.append(p1[i+1]);
		}
		
		p2[0] = 1;
		s2.append(p2[0]);
		for (int i=0; i<lastIndex; i++) {
			int x = Character.getNumericValue(message.charAt(i));
			if (i==0) p2[i+1] = x - p2[i];
			else p2[i+1] = x - p2[i] - p2[i-1];
			s2.append(p2[i+1]);
		}

		if (messageLength > 1) {
			if ( (p1[lastIndex] + p1[lastIndex-1]) == Character.getNumericValue(message.charAt(lastIndex)) 
					&& (Arrays.toString(p1).indexOf('2') < 0 ))
					s1Valid = true;
			
			if ( (p2[lastIndex] + p2[lastIndex-1]) == Character.getNumericValue(message.charAt(lastIndex)) 
					&& (Arrays.toString(p2).indexOf('2') < 0 ))
					s2Valid = true;
		}
		else {
			if ( (p1[lastIndex]) == Character.getNumericValue(message.charAt(lastIndex)) 
					&& ( Arrays.toString(p1).indexOf('2') < 0 ))
					s1Valid = true;
			
			if ( (p2[lastIndex]) == Character.getNumericValue(message.charAt(lastIndex)) 
					&& ( Arrays.toString(p2).indexOf('2') < 0 ))
					s2Valid = true;
		}

		String returnS1 = s1.toString();
		String returnS2 = s2.toString();
		
		if (s1Valid) returnS1 = s1.toString();
		else returnS1 = "NONE";
		
		if (s2Valid) returnS2 = s2.toString();
		else returnS2 = "NONE";
		
		return new String[] {returnS1, returnS2};
	}
	
	public static void main(String[] args) {
		BinaryCode binaryCode = new BinaryCode();
		String[] sA = binaryCode.decode("1222122321");
		System.out.println(sA[0] + ":" + sA[1]);
		sA = binaryCode.decode("123210122");
		System.out.println(sA[0] + ":" + sA[1]);
		sA = binaryCode.decode("123210120");
		System.out.println(sA[0] + ":" + sA[1]);
		sA = binaryCode.decode("22111");
		System.out.println(sA[0] + ":" + sA[1]);
		sA = binaryCode.decode("12221112222221112221111111112221111");
		System.out.println(sA[0] + ":" + sA[1]);
		sA = binaryCode.decode("3");
		System.out.println(sA[0] + ":" + sA[1]);
		sA = binaryCode.decode("11");
		System.out.println(sA[0] + ":" + sA[1]);
		sA = binaryCode.decode("4677838");
		System.out.println(sA[0] + ":" + sA[1]);
		
	}

}
