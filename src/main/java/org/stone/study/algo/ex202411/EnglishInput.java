package org.stone.study.algo.ex202411;

import java.util.*;

/**
 * 主管期望你来实现英文输入法单词联想功能，需求如下：
 *
 * 依据用户输入的单词前缀，从已输入的英文语句中联想出用户想输入的单词。
 * 按字典序输出联想到的单词序列，如果联想不到，请输出用户输入的单词前缀。
 * 注意
 *
 * 英文单词联想时区分大小写
 * 缩略形式如"don't" 判定为两个单词 "don"和 "t"
 * 输出的单词序列不能有重复单词，且只能是英文单词，不能有标点符号
 */
public class EnglishInput {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // The furthest distance in the world,Is not between life and death,But when I stand in front or you,Yet you don't know that I love you.
        String words = scanner.nextLine();
        // 待搜素的单词：f
        String word = scanner.nextLine();
        List<String> ans = search(words, word);

        // front furthest
        System.out.println(String.join(" ", ans));
    }

    private static List<String> search(String words, String word) {
        // 按非单词字符分词
        String[]  wordArr = words.split("\\W+");
        // 支持字典序排序
        TreeSet<String> wordSet = new TreeSet<>(Arrays.asList(wordArr));

        List<String> ans = new ArrayList<>();
        // 遍历字典，找到符合条件的单词
        for(String wordStr : wordSet) {
            if(wordStr.startsWith(word)) {
                ans.add(wordStr);
            }
        }

        return ans.isEmpty() ? Collections.singletonList(word) : ans;
    }
}
