package com.odn.repositories.EntityRepositories;

import com.odn.common.PaginationSelect;
import com.odn.entities.CategoryEntity;
import io.micrometer.core.instrument.util.StringUtils;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CategoryRepository implements PanacheRepositoryBase<CategoryEntity, Long> {
    public long countByCode(Long recrodId, Long sellerId, String code) {
        Map<String, Object> params = new HashMap<>();
        String query = "sellerId = :sellerId and code = :code";
        params.put("sellerId", sellerId);
        params.put("code", code);

        if (recrodId != null) {
            query += " and id <> :id";
            params.put("id", recrodId);
        }

        return count(query, params);
    }

    public PaginationSelect<List<CategoryEntity>> findPaginated(int page, int size, Long sellerId, String filter) {
        Map<String, Object> params = new HashMap<>();
        String query = "sellerId = :sellerId";
        params.put("sellerId", sellerId);

        if (StringUtils.isNotBlank(filter)) {
            query += " and (upper(code) like '%' || upper(:filter) || '%' or upper(name) like '%' || upper(:filter) || '%')";
            params.put("filter", filter);
        }

        long recordCount = count(query, params);

        query += " order by code, name";
        List<CategoryEntity> records = find(query, params).page(page - 1, size).list();
        return new PaginationSelect<>(recordCount, records);
    }
}
