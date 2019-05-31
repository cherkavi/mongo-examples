package com.cherkashyn.vitalii.education.mongo;

import junit.framework.Assert;
import org.eclipse.jetty.util.ArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionBooleanOperationTest {

    @Test
    public void checkUnion(){
        // given
        List<Integer> firstList= new ArrayList<Integer>();
        firstList.add(new Integer(1));
        List<Integer> secondList= Arrays.asList(1,2,3,4,5);

        // when
        Collection<Integer> result=CollectionBooleanOperation.union(firstList, secondList);

        // then
        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.size());
    }

    @Test
    public void checkIntersection(){
        // given
        List<Integer> firstList= new ArrayList<Integer>();
        firstList.add(new Integer(1));
        List<Integer> secondList= Arrays.asList(1,2,3,4,5);

        // when
        Collection<Integer> result=CollectionBooleanOperation.intersection(firstList, secondList);

        // then
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        System.out.println(result);
    }

    @Test
    public void checkIntersectionWithEntity(){
        // given
        List<CollectionBooleanOperation.Entity> firstList= new ArrayList<CollectionBooleanOperation.Entity>();
        firstList.add(new CollectionBooleanOperation.Entity(1,"one++"));
        List<CollectionBooleanOperation.Entity> secondList= Arrays.asList(new CollectionBooleanOperation.Entity(1,"one"),new CollectionBooleanOperation.Entity(2,"two"),new CollectionBooleanOperation.Entity(3,"three"));

        // when
        Collection<CollectionBooleanOperation.Entity> result=CollectionBooleanOperation.intersection(firstList, secondList);

        // then
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        System.out.println(result);
    }
}
