package com.example.lab7.view;

import com.example.lab7.controller.CalculatorPriceController;
import com.example.lab7.pojo.Product;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Route(value = "Lab7")
public class ProductView extends VerticalLayout {
    private HorizontalLayout horizontalLayout;
    private ComboBox productList;
    private TextField productNameField;
    private NumberField productCostField, productProfitField, productPriceField;
    private Button addBtn, updateBtn, deleteBtn, clearBtn;
    private CalculatorPriceController calculatorPriceController = new CalculatorPriceController();
    private Notification notification;
    private List<Product> listItem;

    public ProductView() {
        notification = new Notification("", 5000);
        productList = new ComboBox("Product List");
        productNameField = new TextField("Product Name");
        productCostField = new NumberField("Product Cost");
        productProfitField = new NumberField("Product Profit");
        productPriceField = new NumberField("Product Price");
        addBtn = new Button("Add");
        updateBtn = new Button("Update");
        deleteBtn = new Button("Delete");
        clearBtn = new Button("Clear");
        horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(addBtn, updateBtn, deleteBtn, clearBtn);
        clearField();

        addBtn.addClickListener(clickEvent -> {
            Product product = new Product(null, productNameField.getValue(), productCostField.getValue(), productProfitField.getValue(), productPriceField.getValue());
            boolean res = WebClient.create().post().uri("http://localhost:8080/serviceAddProduct").contentType(MediaType.APPLICATION_JSON).bodyValue(product).retrieve().bodyToMono(boolean.class).block();
            setItem();
            clearField();
        });
        updateBtn.addClickListener(clickEvent -> {
            Product old = WebClient.create().get().uri("http://localhost:8080/serviceGetProductName/{name}", productList.getValue()).retrieve().bodyToMono(Product.class).block();
            Product product = new Product(old.get_id(), productNameField.getValue(), productCostField.getValue(), productProfitField.getValue(), productPriceField.getValue());
            List res = WebClient.create().post().uri("http://localhost:8080/serviceUpdateProduct").contentType(MediaType.APPLICATION_JSON).bodyValue(product).retrieve().bodyToMono(List.class).block();
            setItem();
            clearField();
        });
        deleteBtn.addClickListener(clickEvent -> {
            Product old = WebClient.create().get().uri("http://localhost:8080/serviceGetProductName/{name}", productList.getValue()).retrieve().bodyToMono(Product.class).block();
            boolean res = WebClient.create().post().uri("http://localhost:8080/serviceDeleteProduct/{id}", old.get_id()).retrieve().bodyToMono(boolean.class).block();
            setItem();
            clearField();
        });
        clearBtn.addClickListener(clickEvent -> {
            clearField();
        });

        productCostField.addKeyPressListener(Key.ENTER, e -> {
            double res = WebClient.create().get().uri("http://localhost:8080/getPrice/{cost}/{profit}", productCostField.getValue(), productProfitField.getValue()).retrieve().bodyToMono(double.class).block();
            productPriceField.setValue(res);
        });
        productProfitField.addKeyPressListener(Key.ENTER, e -> {
            double res = WebClient.create().get().uri("http://localhost:8080/getPrice/{cost}/{profit}", productCostField.getValue(), productProfitField.getValue()).retrieve().bodyToMono(double.class).block();
            productPriceField.setValue(res);
        });
        productList.addFocusListener(focusEvent -> {
            setItem();
        });
        productList.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                setField();
            }
        });
        add(productList, productNameField, productCostField, productProfitField, productPriceField, horizontalLayout);

    }

    public void setItem() {
        listItem = WebClient.create().get().uri("http://localhost:8080/serviceGetAllProduct").retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {
                }).block();
        List<String> name = listItem.stream().map(Product::getProductName).collect(Collectors.toList());
        productList.setItems(name);
    }

    public void setField() {
        Product sel = WebClient.create().get().uri("http://localhost:8080/serviceGetProductName/{name}", productList.getValue()).retrieve().bodyToMono(Product.class).block();
        productNameField.setValue(sel.getProductName());
        productCostField.setValue(sel.getProductCost());
        productProfitField.setValue(sel.getProductProfit());
        productPriceField.setValue(sel.getProductPrice());
    }

    public void clearField() {
        productList.setItems("");
        productNameField.setValue("");
        productCostField.setValue(0.0);
        productProfitField.setValue(0.0);
        productPriceField.setValue(0.0);
    }
}
