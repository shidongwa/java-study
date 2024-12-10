package org.stone.study.algo.ex202411;

import java.util.*;

/**
 * 简易内存池
 */
public class MemoryPool {

    private final int[] memory;
    private final Map<Integer, Integer> allocated;

    public MemoryPool() {
        this.memory = new int[100];
        Arrays.fill(memory, 0);
        this.allocated = new HashMap<>();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<String> cmds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cmds.add(sc.nextLine());
        }

        List<Object> res = new MemoryPool().execute(cmds);
        res.forEach(System.out::println);
    }

    /**
     * 执行引擎
     * @param cmds
     * @return
     */
    public List<Object> execute(List<String> cmds) {
        List<Object> res = new ArrayList<>();
        for(String cmd : cmds) {
            String[] cmdArr = cmd.split("=");
            if("REQUEST".equals(cmdArr[0])) {
                res.add(request(Integer.parseInt(cmdArr[1])));
            } else if("RELEASE".equals(cmdArr[0])) {
                Object errMsg = release(Integer.parseInt(cmdArr[1]));
                if(errMsg != null) {
                    res.add(errMsg);
                }
            }
        }

        return res;
    }

    /**
     * 申请内存
     * @param size
     * @return
     */
    private Object request(int size) {
        int start = 0;
        if(size <= 0 || size > 100) {
            return "error";
        }

        while(start < 100) {
            // 找到连续的空闲内存起点
            while (start < 100 && memory[start] != 0) {
                start++;
            }
            if (start + size > 100) {
                return "error";
            }
            // 找到连续的空闲内存终点
            int end = start;
            while (end < 100 && memory[end] == 0) {
                ++end;
            }
            // 找打一块连续的空闲内存
            if (end - start >= size) {
                allocated.put(start, size);
                for (int i = start; i < start + size; i++) {
                    memory[i] = 1;
                }
                return start;
            }
            // 没有找到继续往后找连续内存
            start = end;
        }

        return "error";
    }

    /**
     * 释放内存
     * @param address
     * @return
     */
    private Object release(int address) {
        if(!allocated.containsKey(address)) {
            return "error";
        }

        int size = allocated.get(address);
        for(int i = address; i < address + size; i++) {
            memory[i] = 0;
        }

        allocated.remove(address);

        return null;
    }
}
