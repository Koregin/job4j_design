package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        T found = findById(id);
        boolean res = false;
        String foundkey = null;
        if (found != null) {
            for (String key : mem.keySet()) {
                if (mem.get(key).equals(found)) {
                    foundkey = key;
                    break;
                }
            }
        }
        if (foundkey != null) {
            mem.replace(foundkey, model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        T found = findById(id);
        boolean res = false;
        String foundkey = null;
        if (found != null) {
            for (String key : mem.keySet()) {
                if (mem.get(key).equals(found)) {
                    foundkey = key;
                    break;
                }
            }
        }
        if (foundkey != null) {
            mem.remove(foundkey);
            res = true;
        }
        return res;
    }

    @Override
    public T findById(String id) {
        T findOb = null;
        for (T modelvalue : mem.values()) {
            if (modelvalue.getId().equals(id)) {
                findOb = modelvalue;
                break;
            }
        }
        return findOb;
    }
}
