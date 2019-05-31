package com.cherkashyn.vitalii.education.mongo;

import java.util.ArrayList;
import java.util.List;

public class CollectionBooleanOperation {

    public static <T> List<T> union(List<T> first, List<T> second){
        List<T> result=new ArrayList<T>(first);
        result.addAll(second);
        return result;
    }

    public static <T> List<T> intersection(List<T> first, List<T> second){
        List<T> result=new ArrayList<T>(first);
        result.retainAll(second);
        return result;
    }

    static class Entity{
        private Integer id;
        private String description;

        public Entity(Integer id, String one) {
            this.id = id;
            this.description=one;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "id=" + id +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entity entity = (Entity) o;

            return id != null ? id.equals(entity.id) : entity.id == null;

        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }

}
