import java.util.*;

class SegmentTree{
    int seg[];
    SegmentTree(int n){
        seg=new int[4*n];
    }

    void build(int ind, int low,int high,int arr[]){
        if(low==high){
            seg[ind]=arr[low];
            return;
        }

        int mid=(low+high)/2;
        build(2*ind+1,low,mid,arr);
        build(2*ind+2,mid+1,high,arr);
        seg[ind]=Math.min(seg[2*ind+1],seg[2*ind+2]);
    }

    int query(int ind,int low,int high,int arr[],int l,int r){
        //no overlap
        if(l>high || r<low){
            return Integer.MAX_VALUE;
        }
        //complete overlap
        if(low>=l && high<=r){
            return seg[ind];
        }

        int mid=(low+high)/2;
        int left=query(2*ind+1,low,mid,arr,l,r);
        int right=query(2*ind+2,mid+1,high,arr,l,r);
        return Math.min(left,right);
    }

    void update(int ind,int low,int high,int arr[],int i,int val){
        if(low==high){
            seg[ind]=val;
            return;
        }
        int mid=(low+high)/2;
        if(i<=mid){
            update(2*ind+1,low,mid,arr,i,val);
        }
        else{
            update(2*ind+2,mid+1,high,arr,i,val);
        }
        seg[ind]=Math.min(seg[2*ind+1],seg[2*ind+2]);
    }

}

public class segmentTreeImpl{
    public static void main(String args[]) 
    { 
        Scanner sc=new Scanner(System.in);
        int n;
        n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        SegmentTree sg1=new SegmentTree(n);
        sg1.build(0, 0, n-1, arr);
        int q;
        q=sc.nextInt();
        while(q>0){
            int type;
            type=sc.nextInt();
            if(type==1){
                int l,r;
                l=sc.nextInt();
                r=sc.nextInt();
                System.out.println(sg1.query(0, 0, n-1, arr, l, r));
            }
            else{
                int i,val;
                i=sc.nextInt();
                val=sc.nextInt();
                arr[i]=val;
                sg1.update(0, 0, n-1, arr, i, val);
            }
            q--;
        }
        sc.close();
    }
}