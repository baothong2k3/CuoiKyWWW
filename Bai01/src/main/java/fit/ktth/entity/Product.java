/*
 * @ (#) Product.java    1.0    19/12/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package fit.ktth.entity;/*
 * @description:
 * @author: Bao Thong
 * @date: 19/12/2024
 * @version: 1.0
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "Pro\\d{3}-(0[1-9]|1[0-2])", message = "Theo cấu trúc ProXXX-MM")
    private String code;

    @Size(max = 20, message = "Tối đa 20 ký tự")
    private String name;

    @NotBlank(message = "Không được rỗng")
    private String description;

    //@Future(message = "Ngày đăng ký phải sau ngày hiện tại")
    @Past(message = "Ngày đăng ký phải trước ngày hiện tại")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Số thực lớn hơn 0")
    private double price;

    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cart_ID", nullable = false)
    private Category category;

    public @Pattern(regexp = "Pro\\d{3}-(0[1-9]|1[0-2])", message = "Theo cấu trúc ProXXX-MM") String getCode() {
        return code;
    }

    public void setCode(@Pattern(regexp = "Pro\\d{3}-(0[1-9]|1[0-2])", message = "Theo cấu trúc ProXXX-MM") String code) {
        this.code = code;
    }

    public @Size(max = 20, message = "Tối đa 20 ký tự") String getName() {
        return name;
    }

    public void setName(@Size(max = 20, message = "Tối đa 20 ký tự") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Không được rỗng") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Không được rỗng") String description) {
        this.description = description;
    }

    public @Past(message = "Ngày đăng ký phải trước ngày hiện tại") LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(@Past(message = "Ngày đăng ký phải trước ngày hiện tại") LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    @DecimalMin(value = "0.0", inclusive = false, message = "Số thực lớn hơn 0")
    public double getPrice() {
        return price;
    }

    public void setPrice(@DecimalMin(value = "0.0", inclusive = false, message = "Số thực lớn hơn 0") double price) {
        this.price = price;
    }

    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0") int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public Product() {
    }

    public Product(String code, String name, String description, LocalDate registerDate, double price, int quantity, Category category) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.registerDate = registerDate;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", registerDate=" + registerDate +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
}
