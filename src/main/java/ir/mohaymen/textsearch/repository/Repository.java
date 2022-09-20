package ir.mohaymen.textsearch.repository;

import ir.mohaymen.textsearch.models.Model;

public interface Repository<M extends Model> {
    public M findById(int id);
    public void save(M m);
}
