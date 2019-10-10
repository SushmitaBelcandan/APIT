package com.example.shushmita.apit.adapters;

public class Images {

    private int img_id;
    private String img_name;
    private String img_url;

   public Images(int imgId, String imgName, String imgUrl)
   {
       this.img_id = imgId;
       this.img_name = imgName;
       this.img_url = imgUrl;
   }

    public Integer getImagId() {
        return img_id;
    }

    public void setImageId(int id) {
        this.img_id = id;
    }

    public void setImgName(String name) {
        this.img_name = name;
    }

    public String getImageName() {
        return img_name;
    }

    public String getImgUrl() {
        return img_url;
    }

    public void setImgUrl(String url) {
        this.img_url = url;
    }

}
