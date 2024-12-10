package org.stone.study.algo.ex202411;

import java.util.*;

public class FileCaceSystem {
    private int maxSize; // 缓存最大容量
    private int curSize; // 当前缓存已使用大小
    private Map<String, FileObj> cache; // 缓存KV
    private PriorityQueue<FileObj> pq; //
    private long time; // 逻辑时钟计数
    public static void main(String[] args) {
        /*
            50
            6
            put a 10
            put b 20
            get a
            get a
            get b
            put c 30
         */
        Scanner scanner = new Scanner(System.in);
        // 最大缓存容量
        int maxSize = Integer.parseInt(scanner.nextLine());
        // 操作次数
        int n = Integer.parseInt(scanner.nextLine());
        FileCaceSystem fileCaceSystem = new FileCaceSystem(maxSize);
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] arr = line.split(" ");
            if("put".equals(arr[0])) {
                fileCaceSystem.put(arr[1], Integer.parseInt(arr[2]));
            } else if("get".equals(arr[0])){
                fileCaceSystem.get(arr[1]);
            }
        }
        // a,c
        fileCaceSystem.printCache();
    }

    public FileCaceSystem(int maxSize) {
        this.maxSize = maxSize;
        this.curSize = 0;
        this.cache = new HashMap<>();
        // 自定义小顶堆
        this.pq = new PriorityQueue<>((f1, f2) -> {
            if(f1.accessCount == f2.accessCount) {
                return Long.compare(f1.lastAccessTime, f2.lastAccessTime);
            } else {
                return f1.accessCount - f2.accessCount;
            }
        });
    }

    public FileObj get(String fileName) {
        if(!cache.containsKey(fileName)) {
            return null;
        }

        // 出堆，入堆实现重新排序
        FileObj file = cache.get(fileName);
        pq.remove(file);
        file.accessCount = file.accessCount + 1;
        file.lastAccessTime = time++;
        pq.offer(file);

        return file;
    }

    public void put(String fileName, int fileSize) {
        if(fileSize > maxSize) {
            return;
        }

        if(cache.containsKey(fileName)) {
            //  存在，则更新访问次数和逻辑时钟
            get(fileName);
            return;
        }
        // 空间不够，移除访问次数最少的元素
        if(curSize + fileSize > maxSize) {
            while(curSize + fileSize > maxSize) {
                FileObj file = pq.poll();
                curSize -= file.fileSize;
                cache.remove(file.fileName);
            }
        }
        // 空间够，新增元素
        FileObj file = new FileObj(fileName, fileSize);
        file.accessCount = 0;
        file.lastAccessTime = time++;
        curSize += fileSize;
        cache.put(fileName, file);
        pq.offer(file);
    }

    // 打印缓存中的元素，按文件名升序排序
    public void printCache() {
        if(cache.isEmpty()) {
            System.out.println("NONE");
        } else {
            List<String> keys = new ArrayList<>(cache.keySet());
            Collections.sort(keys);
            // 打印列表中的元素，以逗号分隔
            System.out.println(String.join(",", keys));
        }
    }
    //使用静态内部类，减少冗长的 get/set 方法
    static class FileObj {
        private String fileName;
        private int fileSize;
        private int accessCount;
        // 逻辑时钟，仅用来比较访问时间
        private long lastAccessTime;

        public FileObj(String fileName, int fileSize) {
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.accessCount = 0;
            this.lastAccessTime = 0;
        }
    }
}
