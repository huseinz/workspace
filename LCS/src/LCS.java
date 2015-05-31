
public class LCS {
	
	public static String lcs(String[] str1, String[] str2)
    {
        int m = str1.length;
        int n = str2.length;
 
        int[][] arr = new int[m + 1][n + 1];
 
        for (int i = m - 1; i >= 0; i--)
        {
            for (int j = n - 1; j >= 0; j--)
            {
                if (str1[i] == str2[j])
                    arr[i][j] = arr[i + 1][j + 1] + 1;
                else 
                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);
            }
        }
        
        StringBuffer sb = new StringBuffer();
        
        for(int i = 0, j = 0; i < m && j < n;) 
        {
            if (str1[i] == str2[j]) 
            {
                sb.append(str1[i] + " ");
                i++; j++;
            }
            else if (arr[i + 1][j] >= arr[i][j + 1]) 
                i++;
            else
                j++;
        }
        
        return sb.toString();
    }
	
	public static void main(String[] args) {
		String[] string1 = {"in", "ni", "nat", "jag", "dep", "y", "togo", "il", "ru", "pur", "de", "tuh", "jon" ,"stewart"};
		String[] string2 = {"nat", "in", "jag", "dep", "huh", "tuh", "il", "de", "tuh", "de", "huh", "jon", "y", "stewart"};
		System.out.println(lcs(string1, string2));
    }
}
