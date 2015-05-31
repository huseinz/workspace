public class MakeChange {

    public static void main(String[] args) {

        int[] coins = { 1, 2, 5, 10, 25, 50 };

        System.out.println(BottomUpMakeTheChangeOptimally(coins, 82));

    }

    static int BottomUpMakeTheChangeOptimally(int[] nDenominations, int nSum) {

        if (nSum <= 0) 
            return 0;

        int[] nCoins = new int[nSum + 1];

        for (int s = 1; s <= nSum; s++) 
	{

            nCoins[s] = Integer.MAX_VALUE;

            for (int j = 0; j < nDenominations.length; j++) 
	    {

                if (nDenominations[j] <= s
                    && 1 + nCoins[s - nDenominations[j]] < nCoins[s]) 
		{
                    nCoins[s] = 1 + nCoins[s - nDenominations[j]];
                }
            }
        }

        return nCoins[nSum];
    }

}
