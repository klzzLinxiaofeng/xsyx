package com.xunyunedu.character.pojo;


import lombok.Data;

@Data
public class Picture {
        String pictureId;
        String  pictureUrl;

        public String getPictureId() {
            return pictureId;
        }

        public void setPictureId(String pictureId) {
            this.pictureId = pictureId;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

}
