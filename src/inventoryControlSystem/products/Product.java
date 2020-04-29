package inventoryControlSystem.products;

public class Product {
    private String category_name;
    private String description;
    private Integer category_id;
    private String product_name;
    private Integer product_id;
    private Integer unit_price;
    private Integer quantity;


    public String getCategory_name(){return category_name;}

    public void setCategory_name(String category_name) {
        this.category_name = category_name; }

    public String getDescription(){return description;}

    public void setDescription(String description){
        this.description = description; }

    public Integer getCategory_id(){return category_id;}

    public void setCategory_id(Integer Category_id){
        this.category_id = Category_id; }

    public Integer getProduct_id(){return product_id;}

    public void setProduct_id(Integer product_id){
        this.product_id = product_id; }

    public String getProduct_name(){return product_name;}

    public void setProduct_name(String product_name){
        this.product_name = product_name; }

    public Integer getUnit_price(){return unit_price;}

    public void setUnit_price(Integer unit_price){
        this.unit_price = unit_price; }

    public Integer getQuantity(){return quantity;}

    public void setQuantity(Integer quantity){
        this.quantity = quantity; }


}
