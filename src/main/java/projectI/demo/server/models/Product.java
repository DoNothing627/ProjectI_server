package projectI.demo.server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Product {
    @Id
    public String id;
    @Field
    public String category;
    @Field
    public int number;
    @Field
    public String name;
    @Field
    public String img;
    @Field
    public String des;
    @Field
    public String ORIGIN;
    @Field
    public String MATERIALS;
    @Field
    public String END_OF_LIFE;
    @Field
    public String PRODUCT_CARE;
    @Field
    public String ADDITIONAL_FEATURES;
    @Field
    public String RETURNS;
    @Field
    public int company;


    public Product() {
    }

    public Product(String category, int number, String name, String img, String des, String ORIGIN, String MATERIALS, String END_OF_LIFE, String PRODUCT_CARE, String ADDITIONAL_FEATURES, String RETURNS, int company) {
        this.category = category;
        this.number = number;
        this.name = name;
        this.img = img;
        this.des = des;
        this.ORIGIN = ORIGIN;
        this.MATERIALS = MATERIALS;
        this.END_OF_LIFE = END_OF_LIFE;
        this.PRODUCT_CARE = PRODUCT_CARE;
        this.ADDITIONAL_FEATURES = ADDITIONAL_FEATURES;
        this.RETURNS = RETURNS;
        this.company = company;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getORIGIN() {
        return ORIGIN;
    }

    public void setORIGIN(String ORIGIN) {
        this.ORIGIN = ORIGIN;
    }

    public String getMATERIALS() {
        return MATERIALS;
    }

    public void setMATERIALS(String MATERIALS) {
        this.MATERIALS = MATERIALS;
    }

    public String getEND_OF_LIFE() {
        return END_OF_LIFE;
    }

    public void setEND_OF_LIFE(String END_OF_LIFE) {
        this.END_OF_LIFE = END_OF_LIFE;
    }

    public String getPRODUCT_CARE() {
        return PRODUCT_CARE;
    }

    public void setPRODUCT_CARE(String PRODUCT_CARE) {
        this.PRODUCT_CARE = PRODUCT_CARE;
    }

    public String getADDITIONAL_FEATURES() {
        return ADDITIONAL_FEATURES;
    }

    public void setADDITIONAL_FEATURES(String ADDITIONAL_FEATURES) {
        this.ADDITIONAL_FEATURES = ADDITIONAL_FEATURES;
    }

    public String getRETURNS() {
        return RETURNS;
    }

    public void setRETURNS(String RETURNS) {
        this.RETURNS = RETURNS;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return String.format("Product[id='%s', category='%s', number='%d', name='%s', img='%s', des='%s', " +
                "ORIGIN='%s', MATERIALS='%s', END_OF_LIFE='%s', PRODUCT_CARE='%s', ADDITIONAL_FEATURES='%s', RETURNS='%s', company='%d']",
                id, category,number, name, img, des, ORIGIN, MATERIALS, END_OF_LIFE, PRODUCT_CARE, ADDITIONAL_FEATURES, RETURNS, company);
    }
}
