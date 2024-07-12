package com.example.myblog;

import java.util.*;

class Main {

    static int N = (int)1e5+10;
    static int[] nums = new int[N];

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            nums[i] = scan.nextInt();
        }

        quickSort(1,n);

        for (int i = 1; i <= n; i++) {
            System.out.print(nums[i] + " ");
        }

    }

    public static void quickSort(int l,int r) {
        if (l >= r) {
            return;
        }

        int i = l-1, j = r+1;
        int x = nums[i+j>>1];
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        quickSort(l,j);
        quickSort(j+1,r);

    }

}