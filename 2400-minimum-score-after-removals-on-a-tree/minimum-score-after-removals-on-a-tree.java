class Solution {
    int res = Integer.MAX_VALUE;
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(new ArrayList<>());
        }
        for(int[] e:edges){
            list.get(e[0]).add(e[1]);
            list.get(e[1]).add(e[0]);
        }
        int sum = 0;
        for(int num:nums){
            sum ^= num;
        }
        dfs(0,-1,nums,list,sum);
        return res;
    }
    private int dfs(int x,int f,int[] nums,List<List<Integer>> list,int sum){
        int s = nums[x];
        for(int y:list.get(x)){
            if(y == f){
                continue;
            }
            s ^= dfs(y,x,nums,list,sum);
        }
        for(int y:list.get(x)){
            if(y == f){
                dfs2(y,x,s,x,nums,list,sum);
            }
        }
        return s;
    }
    private int dfs2(int x,int f,int oth,int anc,int[] nums,List<List<Integer>> list,int sum){
        int s = nums[x];
        for(int y:list.get(x)){
            if(y == f){
                continue;
            }
            s ^= dfs2(y,x,oth,anc,nums,list,sum);
        }
        if(f == anc){
            return s;
        }
        res = Math.min(res,calc(oth,s,sum^oth^s));
        return s;
    }
    private int calc(int part1,int part2,int part3){
        return (Math.max(part1,Math.max(part2,part3))-Math.min(part1,Math.min(part2,part3)));
    }
}