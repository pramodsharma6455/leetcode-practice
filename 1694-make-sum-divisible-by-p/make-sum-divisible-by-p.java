class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int[] pre = new int[n];
        
        pre[0] = nums[0] % p;
        for(int i = 1; i < n; i++)
         pre[i] = (nums[i] % p + pre[i - 1]) % p;

        int need = pre[n - 1];
        if(need == 0) return 0;

        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int len = n;
        for(int i = 0; i < n; i++){
            int preNeed = (pre[i] - need + p) % p;
            if(mp.containsKey(preNeed)){
                int l = mp.get(preNeed);
                len = Math.min(len, i - l);
            }
            mp.put(pre[i], i);
        }

        return len == n ? -1 : len;
    }
}