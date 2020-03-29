package com.example.tangboyang1.pojo;

public class Image {
    private Integer id;

    private String productsUuid;

    private String imagurl;

    private Integer imagurlNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductsUuid() {
        return productsUuid;
    }

    public void setProductsUuid(String productsUuid) {
        this.productsUuid = productsUuid == null ? null : productsUuid.trim();
    }

    public String getImagurl() {
        return imagurl;
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl == null ? null : imagurl.trim();
    }

    public Integer getImagurlNum() {
        return imagurlNum;
    }

    public void setImagurlNum(Integer imagurlNum) {
        this.imagurlNum = imagurlNum;
    }
}