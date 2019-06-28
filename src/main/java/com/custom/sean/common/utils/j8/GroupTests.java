package com.custom.sean.common.utils.j8;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupTests {
    public static void main(String[] args) {
            List<BlogPost> blogPostList = Lists.newArrayList();
            blogPostList.add(new BlogPost("post1", "zhuoli", 1, 30));
            blogPostList.add(new BlogPost("post2", "zhuoli", 1, 40));
            blogPostList.add(new BlogPost("post3", "zhuoli", 2, 15));
            blogPostList.add(new BlogPost("post4", "zhuoli", 3, 33));
            blogPostList.add(new BlogPost("post5", "Alice", 1, 99));
            blogPostList.add(new BlogPost("post6", "Michael", 3, 65));

            Map<BlogPost, List<BlogPost>> postsPerTypeAndAuthor = blogPostList.stream()
                    .collect(Collectors.groupingBy(post -> new BlogPost(post.getP1(), post.getP2())));
            System.out.println(postsPerTypeAndAuthor);
            //

        TestData testData1 = new TestData(1, 1, 100L);
        TestData testData2 = new TestData(1, 2, 1000L);
        TestData testData3 = new TestData(1, 3, 100L);
        TestData testData4 = new TestData(1, 1, 80L);

        TestData testData5 = new TestData(2, 1, 1600L);
        TestData testData6 = new TestData(2, 2, 1030L);
        TestData testData7 = new TestData(2, 2, 1001L);
        TestData testData8 = new TestData(2, 2, 1500L);

        TestData testData9 = new TestData(3, 5, 1500L);

        List<TestData> testDataList = Stream.of(testData1, testData2, testData3, testData4, testData5, testData6, testData7, testData8, testData9).collect(Collectors.toList());
    }
}
