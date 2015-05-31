
public class CaesarCipher {
	
	static String GetNameAndPID()
	{
		return("Husein,Zubir,z3084147");
	}

	static double[] table = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7,
		7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};
	
	static int let2nat(char c){
		return "abcdefghijklmnopqrstuvwxyz".indexOf(c);
	}
	
	static char nat2let(int code){
		return "abcdefghijklmnopqrstuvwxyz".charAt(code);
	}
	
	static char shift(int shftAmt, char c){
		//fancy math is to make sure result is always the proper positive index
		return nat2let(((((let2nat(c) + shftAmt) % 26) + 26) % 26));
	}
	
	static String encode(int shftAmt, String str){
		if(str.length() == 0)
			return "";
		//only encode lowercase letters
		if(let2nat(str.charAt(0)) != -1)
			return shift(shftAmt, str.charAt(0)) + encode(shftAmt, str.substring(1));
		return str.charAt(0) + encode(shftAmt, str.substring(1));
	}
	
	static String decode(int shftAmt, String str){
		if(str.length() == 0)
			return "";
		//only decode lowercase letters
		if(let2nat(str.charAt(0)) != -1)
			return shift(shftAmt*-1, str.charAt(0)) + decode(shftAmt, str.substring(1));
		return str.charAt(0) + decode(shftAmt, str.substring(1));
	}
	
	static int lowers(String str){
		if(str.length() == 0)
			return 0;
		return (("abcdefghijklmnopqrstuvwxyz".indexOf(str.charAt(0)) != -1) ? 1 : 0) + lowers(str.substring(1));
	}
	
	static int count(char c, String str){
		if(str.length() == 0)
			return 0;
		return (str.charAt(0) == c ? 1 : 0) + count(c, str.substring(1));
	}
	
	static double percent(int num1, int num2){
		return (double)num1 / (double)num2 * 100.0;
	}
	
	static double[] freqs(String str){
		double[] arr = new double[26];
		
		int nlowercase = lowers(str);
		
		for(int i = 0; i < 26; i++)
			arr[i] = percent(count(nat2let(i),str), nlowercase);
		
		return arr;
	}
	
	static double[] rotate(int n, double[] list){
		double[] arr = new double[26];
		
		for(int i = 0; i < 26; i++)
			arr[((((i - n) % 26) + 26) % 26)] = list[i];
		
		return arr;
	}
	
	static double chisqr(double[] os){
		
		double result = 0.0;
		
		for(int i = 0; i < 26; i++)
			result += ((os[i] - table[i])*(os[i] - table[i])) / table[i];
		
		return result;
	}
	
	static int position(double a, double[] list){
		for(int i = 0; i < list.length; i++)
			if(list[i] == a)
				return i;
			
		return -1;
	}
	
	static String crack(String str){
		
		double[] str_freqs = freqs(str);
		
		double[] str_chisqrs = new double[26];
		
		for(int i = 0; i < 26; i++)
			str_chisqrs[i] = chisqr(rotate(i, str_freqs));
		
		double min_chisqr = str_chisqrs[0];
		for(double d : str_chisqrs)
			if(d < min_chisqr)
				min_chisqr = d;
		
		return decode(position(min_chisqr, str_chisqrs), str);
	}
	
	public static void main(String args[]){
		System.out.println(encode(3, "haskellisfun"));
		System.out.println(decode(3, encode(3, "haskellisfun")));
		System.out.println(crack("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!"));
	}
}
