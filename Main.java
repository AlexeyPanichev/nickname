import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger count3 = new AtomicInteger(0);
    public static AtomicInteger count4 = new AtomicInteger(0);
    public static AtomicInteger count5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread thread1 = new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text) || isSameChar(text) || isAscending(text)) {
                    if (text.length() == 3) {
                        count3.incrementAndGet();
                    } else if (text.length() == 4) {
                        count4.incrementAndGet();
                    } else if (text.length() == 5) {
                        count5.incrementAndGet();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (String text : texts) {
                if (isSameChar(text)) {
                    if (text.length() == 3) {
                        count3.incrementAndGet();
                    } else if (text.length() == 4) {
                        count4.incrementAndGet();
                    } else if (text.length() == 5) {
                        count5.incrementAndGet();
                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (String text : texts) {
                if (isAscending(text)) {
                    if (text.length() == 3) {
                        count3.incrementAndGet();
                    } else if (text.length() == 4) {
                        count4.incrementAndGet();
                    } else if (text.length() == 5) {
                        count5.incrementAndGet();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Красивых слов с длиной 3: " + count3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + count4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + count5.get() + " шт");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left)!= text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isSameChar(String text) {
        char c = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i)!= c) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAscending(String text) {
        char c = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) < c) {
                return false;
            }
            c = text.charAt(i);
        }
        return true;
    }
}