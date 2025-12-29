package com.example.searches.service;

import com.atguigu.to.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModel) throws IOException;
}
