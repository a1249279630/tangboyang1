package com.example.tangboyang1.dao;

import com.example.tangboyang1.mapper.ImageMapper;
import com.example.tangboyang1.pojo.Image;
import com.example.tangboyang1.pojo.ImageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageDao {
    @Autowired
    private ImageMapper imageMapper;

    public Integer AddImage(Image image) {
        return imageMapper.insert(image);
    }

    public List<Image> FindimageByProductUuid(String uuid){
        ImageExample imageExample=new ImageExample();
        imageExample.createCriteria().andProductsUuidEqualTo(uuid);
        return imageMapper.selectByExample(imageExample);
    }

    public Integer deleteImageByUuid(String uuid) {
        ImageExample imageExample=new ImageExample();
        imageExample.createCriteria().andProductsUuidEqualTo(uuid);
        return imageMapper.deleteByExample(imageExample);
    }
}
