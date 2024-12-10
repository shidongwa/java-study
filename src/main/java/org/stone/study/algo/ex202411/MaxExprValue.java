package org.stone.study.algo.ex202411;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提取字符串中的最长合法简单数学表达式字符串长度最长的，并计算表达式的值。如果没有返回 0
 * 简单数学表达式只能包含以下内容
 * 0-9 数字，符号+-*
 * 说明:
 * 1.所有数字，计算结果都不超过 long
 * 2.如果有多个长度一样的，请返回第一个表达式的结果
 * 3.数学表达式，必须是最长的，合法的
 * 4.操作符不能连续出现，如 +--+1 是不合法的
 */
public class MaxExprValue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1-2abcd
        String str = scanner.nextLine();
        long ans = maxExprValue(str);
        // -1
        System.out.println(ans);
    }

    public static long maxExprValue(String str) {
        int ans = 0;
        // 利用正则表达式找出所有合法的表达式
        String regex = "\\d+(?:[-+*]\\d+)*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        String longestExpr = "";
        int maxLen = 0;
        while (matcher.find()) {
            String expr = matcher.group();
            if(expr.length() > maxLen) {
                longestExpr = expr;
                maxLen = expr.length();
            }
        }

        if(longestExpr.isEmpty()) {
            return 0;
        }

        // 求最长合法表达式的值
        try {
            return evaluateExpr(longestExpr);
        } catch (Exception e) {
            return 0;
        }
    }

    // 计算表达式的值
    private static long evaluateExpr(String expr) {
        // 利用操作数栈和运算符栈对表达式求值
        Stack<Long> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        int n = expr.length();
        long num = 0;
        boolean hasNum = false;

        for(int i = 0; i < n; i++) {
            char c = expr.charAt(i);
            // 处理操作数
            if(Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                hasNum = true;
            } else { // 处理运算符
                if(!hasNum) {
                    throw new RuntimeException("Invalid expression");
                }
                // 先将操作数入栈
                numStack.push(num);
                num = 0;
                hasNum = false;
                // 先计算高优先级的运算符，再将当前运算符入栈
                while(!opStack.isEmpty() && priority(opStack.peek()) >= priority(c)) {
                    numStack.push(applyOp(numStack.pop(), numStack.pop(), opStack.pop()));
                }
                opStack.push(c);
            }
        }
        // 处理最后一个操作数
        if(hasNum) {
            numStack.push(num);
        }
        // 处理剩余的运算符
        while(!opStack.isEmpty()) {
            numStack.push(applyOp(numStack.pop(), numStack.pop(), opStack.pop()));
        }

        return numStack.pop();
    }

    // 定义运算符的运算规则
    private static long applyOp(long b, long a, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                throw new RuntimeException("Invalid operator");
        }
    }

    // 定义运算符的优先级
    private static int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
                return 2;
            default:
                return 0;
        }
    }
}
