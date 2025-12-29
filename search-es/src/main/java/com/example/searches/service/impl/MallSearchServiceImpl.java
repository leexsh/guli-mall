package com.example.searches.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.searches.config.MallEsSearchConfig;
import com.example.searches.constant.EsConstant;
import com.example.searches.feign.ProductFeignService;
import com.example.searches.service.MallSearchService;
import com.example.searches.vo.SearchParam;
import jakarta.annotation.Resource;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;

@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Autowired
    private RestHighLevelClient esClient;
    @Resource
    private ProductFeignService productFeignService;



    @Override
    public SearchResult search(SearchParam param) {
        SearchResult res = null;
        SearchRequest req = getSearchRequest(param);
        try {
            SearchResponse resp = esClient.search(req, MallEsSearchConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private SearchRequest getSearchRequest(SearchParam param) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(param.getKeyword())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("skuTitle", param.getKeyword()));
        }
        if (null != param.getCatalog3Id()) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("catalogId", param.getCatalog3Id()));
        }
        if (null != param.getBrandId()) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("brandId", param.getBrandId()));
        }

        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            param.getAttrs().forEach(attr -> {
                BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
                String[] attrs = attr.split("-");
                String attrId = attrs[0];
                String[] attrValues = attrs[1].split(":");
                boolQuery.must(QueryBuilders.termsQuery("attrs." + attrId + ".keyword", attrValues));
                NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("attrs", boolQuery, ScoreMode.None);
                boolQuery.must(nestedQueryBuilder);
            });
        }
        if (param.getHasStock() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hasStock", param.getHasStock()));
        }
        if(!StringUtils.isEmpty(param.getSkuPrice())) {
            RangeQueryBuilder rqb = QueryBuilders.rangeQuery("skuPrice");
            String[] skuPrice = param.getSkuPrice().split("-");
            if (skuPrice.length == 2) {
                rqb.gte(skuPrice[0]).lte(skuPrice[1]);
            } else if (skuPrice.length == 1) {
                if (param.getSkuPrice().split("-").length == 2) {
                    rqb.gte(skuPrice[0]).lte(skuPrice[1]);
                }
                if (param.getSkuPrice().split("-").length == 3) {
                    rqb.gte(skuPrice[0]).lte(skuPrice[1]);
                }
                boolQueryBuilder.filter(rqb);
            }
        }
        searchSourceBuilder.query(boolQueryBuilder);
        if (!StringUtils.isEmpty(param.getSort())) {
            String sort = param.getSort();
            // sort=hotScore_asc/desc
            String[] sortFields = sort.split("_");

            SortOrder sortOrder = "asc".equalsIgnoreCase(sortFields[1]) ? SortOrder.ASC : SortOrder.DESC;
            searchSourceBuilder.sort(sortFields[0], sortOrder);
        }

        // 2.2 分页 from = (pageNum - 1) * pageSize
        searchSourceBuilder.from((param.getPageNum() - 1) * EsConstant.PRODUCT_PAGE_SIZE);
        searchSourceBuilder.size(EsConstant.PRODUCT_PAGE_SIZE);

        // 2.3 高亮
        if (!StringUtils.isEmpty(param.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");

            searchSourceBuilder.highlighter(highlightBuilder);
        }

        System.out.println("构建的DSL语句" + searchSourceBuilder.toString());

        /**
         * 聚合分析
         */
        //1. 按照品牌进行聚合
        TermsAggregationBuilder brand_agg = AggregationBuilders.terms("brand_agg");
        brand_agg.field("brandId").size(50);

        //1.1 品牌的子聚合-品牌名聚合
        brand_agg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        //1.2 品牌的子聚合-品牌图片聚合
        brand_agg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        searchSourceBuilder.aggregation(brand_agg);

        //2. 按照分类信息进行聚合
        TermsAggregationBuilder catalog_agg = AggregationBuilders.terms("catalog_agg");
        catalog_agg.field("catalogId").size(20);
        catalog_agg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(1));
        searchSourceBuilder.aggregation(catalog_agg);

        // 3. 按照属性信息进行聚合
        NestedAggregationBuilder attr_agg = AggregationBuilders.nested("attr_agg", "attrs");
        //3.1 按照属性ID进行聚合
        TermsAggregationBuilder attr_id_agg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId");
        attr_agg.subAggregation(attr_id_agg);
        //3.1.1 在每个属性ID下，按照属性名进行聚合
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        //3.1.2 在每个属性ID下，按照属性值进行聚合
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(50));
        searchSourceBuilder.aggregation(attr_agg);


        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, searchSourceBuilder);
        return searchRequest;
    }
}
