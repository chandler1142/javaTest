package com.chandler;

import com.list.sample.TestEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<TestEntity> list1 = new ArrayList<>();
        TestEntity entity1 = new TestEntity(1L,"name1");
        TestEntity entity2 = new TestEntity(2L,"name2");
        TestEntity entity3 = new TestEntity(3L,"name3");
        list1.add(entity1);
        list1.add(entity2);
        list1.add(entity3);

        List<String> nameList = list1.parallelStream()
                .filter(l -> l.getId() > 1L)
                .map(l -> l.getName())
                .collect(Collectors.toList());

        nameList.forEach(
                l -> {
                    System.out.println(l);
                }
        );
    }
}
