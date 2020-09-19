package com.learn.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class BasicArrays {
    /**
     * @param nums [1, 1, 0, 1, 1, 1]
     * @return 3
     */
    public int consecutiveOnesInBinaryArray(int[] nums) {
        int counter = 0;
        int maxNum =0;
        for (int num : nums) {
            if (num == 1) {
                ++counter;
            } else {
                if (maxNum < counter) {
                    maxNum = counter;
                }
                counter =0;
            }
        }
        if (maxNum < counter) {
            maxNum = counter;
        }
        return maxNum;
    }

    /**
     * @param nums [555,901,482,1771]
     * @return 1
     */
    public int findNumbersWithEvenNumberOfDigits(int[] nums) {
        int counter = 0;
        for (int num : nums) {
            if (Integer.toString(num).length() % 2 == 0) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @param arr [1,0,2,3,0,4,5,0] => [1,0,0,2,3,0,0,4]
     */
    public void duplicateZeros(int[] arr) {
        int possibleDups = 0;
        int length_ = arr.length - 1;

        // Find the number of zeros to be duplicated
        // Stopping when left points beyond the last element in the original array
        // which would be part of the modified array
        for (int left = 0; left <= length_ - possibleDups; left++) {

            // Count the zeros
            if (arr[left] == 0) {

                // Edge case: This zero can't be duplicated. We have no more space,
                // as left is pointing to the last element which could be included  
                if (left == length_ - possibleDups) {
                    // For this zero we just copy it without duplication.
                    arr[length_] = 0;
                    length_ -= 1;
                    break;
                }
                possibleDups++;
            }
        }

        printArray(arr);
    

        // Start backwards from the last element which would be part of new array.
        int last = length_ - possibleDups;
        System.out.println(possibleDups + ", " + length_ + ", " + last);

        // Copy zero twice, and non zero once.
        for (int i = last; i >= 0; i--) {
            if (arr[i] == 0) {
                arr[i + possibleDups] = 0;
                possibleDups--;
                arr[i + possibleDups] = 0;
            } else {
                arr[i + possibleDups] = arr[i];
            }
        }

        printArray(arr);
    }

    /**
     * @param nums1 = [1,2,3,0,0,0] => Sorted array
     * @param m = 3
     * @param nums2 = [2,5,6] => Sorted array
     * @param n = 3
     * Output = [1,2,2,3,5,6]
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2 
            // and add the largest one in nums1 
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    /**
     * @param nums [2,3,3,2]
     * @param val 3
     * @return 2 => [2,2,...]
     */
    public int removeElement(int[] nums, int val) {
        int p1 = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != val) {
                nums[p1] = nums[i];
                p1++;
            }
        }
        return p1;
    }

    /**
     * 
     * @param nums [1,1,2,2,3,3,4,4]  => sorted array
     * @return 4 => [1,2,3,4,......
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int p=0;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] != nums[p]) {
                p++;
                nums[p] = nums[i];
            }
        }
    
        return p+1;
    }

    public boolean checkIfExist(int[] arr) {
        Hashtable<Integer, Boolean> table = new Hashtable<Integer, Boolean>();
        for (int i=0;i<arr.length;i++) {
            int d = arr[i] * 2;
            if (arr[i] % 2 == 0) {
                int half = arr[i] / 2;
                if (table.get(d) != null || table.get(half) != null) {
                    return true;
                }
            } else if (table.get(d) != null) {
                return true;
            }
            table.put(arr[i], false);
        }
        return false;
    }

    public boolean validMountainArray(int[] A) {
        int i=0;
        int length = A.length;
        // Walk Up
        for(;i<length-1; i++) {
            if (A[i] >= A[i+1]) {
                break;
            }
        }
        
        if (i==0 || i == length-1) return false;
        
        // Walk Down
        for(;i<length-1; i++) {
            if (A[i] <= A[i+1]) {
                break;
            }
        }
        
        return i == length-1;
    }

    /**
     * Replace Elements with Greatest Element on Right Side
     * @param arr = [56903,18666,60499,57517,26961] 
     * @return = [60499,60499,57517,26961,-1]
     */
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int maxValue = arr[n-1];
        for (int i=n-1; i>0; i--) {
            if (arr[i-1] > maxValue) {
                //arr[i-1] =arr[i];
                int temp = arr[i-1];
                arr[i-1] = maxValue;
                maxValue = temp;
                
            } else {
                arr[i-1] = maxValue;
            }
        }
        arr[n-1] = -1;
        return arr;
    }

    /**
     * Move Zeroes to the end of the array
     * @param nums = [0,1,0,3,12] => [1,3,12,0,0]
     */
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundIndex=0;
        for (int i=0; i<nums.length;i++) {
            if (nums[i] != 0) {   
                if (lastNonZeroFoundIndex != i) {
                    nums[lastNonZeroFoundIndex] = nums[i];
                    nums[i] = 0;
                }
                lastNonZeroFoundIndex++; 
            }
        }
    }

    /**
     * Sort Array By Parity (Move all the even numbers to left of the array and odd numbers to right of the array)
     * @param A = [3,1,2,4]
     * @return [4,2,3,1] or [2,4,1,3] or [4,2,1,3] or [2,4,3,1]
     */
    public int[] sortArrayByParity(int[] A) {
        int odds = A.length-1;
        int temp = 0;
        for(int i=0; i<=odds; i++) {
            if (A[i] % 2 != 0) {
                while(odds > i) {
                    if (A[odds] %2 == 0) {
                        temp = A[odds];
                        A[odds--] = A[i];
                        A[i] = temp;
                        break;
                    }
                    odds--;
                };
            }
        }
        return A;
    }

    /**
     * Height Checker - reorder in any possible way between themselves and the non selected items remain on their positions
     * @param heights [1,1,4,2,1,3] | [5,1,2,3,4] | [1,2,3,4,5]
     * @return 3 | 5 | 0
     */
    public int heightChecker(int[] heights) {
        if (heights.length < 2) return 0;

        int[] copyHeights = new int[heights.length];
        System.arraycopy(heights, 0, copyHeights, 0, heights.length);

        Arrays.sort(copyHeights);
        int counter =0;
        for (int i= 0; i< heights.length; i++) {
            if (heights[i] != copyHeights[i]) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Max Consecutive Ones - Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0
     * @param nums [1,0,1,1,0]
     * @return 4
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int counter = 0;
        int zeros = 0;
        int start = 0;
        int k =1; // Number of 1s to flip
        for (int i=0; i< nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;   
            }
            while (zeros > k) {
                if (nums[start] == 0) {
                    zeros--;
                }
                start++;
            };
            counter = Math.max(counter, i-start+1);
        }
        System.out.println(counter);
        return counter;
    }

    /**
     * Third Maximum Number - Given a non-empty array of integers, return the third maximum number in this array. 
     * If it does not exist, return the maximum number. The time complexity must be in O(n).
     * @param nums [2, 2, 3, 1] | [3, 2, 1] | [1, 2]
     * @return 1 | 1 | 2
     */
    public int thirdMax(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num:nums) {
            set.add(num);
            if (set.size() > 3) {
                set.remove(Collections.max(set));
            }
        }

        int maxNum  = Collections.min(set);
        System.out.println(set + ", " + maxNum);
        return maxNum;
    }

    /**
     * Find All Numbers Disappeared in an Array - 
     * Find all the elements of [1, n] inclusive that do not appear in this array.
     * @param nums [4,3,2,7,8,2,3,1]
     * @return [5,6]
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int temp = 0;
        int val = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != i+1) {
                val = nums[i];
                while(val != nums[val-1]) {
                    temp = nums[val-1];
                    nums[val-1] = val;
                    val = temp;
                }
            }
        }

        List<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<nums.length;i++) {
            if (nums[i] != i+1) {
                list.add(i+1);
            }
        }
        return list;
    }

    /**
     * Find All Numbers Disappeared in an Array - 
     * Find all the elements of [1, n] inclusive that do not appear in this array.
     * @param nums [4,3,2,7,8,2,3,1]
     * @return [5,6]
     */
    public List<Integer> findDisappearedNumbersII(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            int newIndex = Math.abs(nums[i]) -1;
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }

        List<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<nums.length;i++) {
            if (nums[i] > 0) {
                list.add(i+1);
            }
        }
        return list;
    }

    private void printArray(int[] arr) {
        for (int i=0; i<arr.length;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}