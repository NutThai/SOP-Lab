package com.example.lab7.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "Lab7")
public class ProductView extends VerticalLayout {
    private HorizontalLayout horizontalLayout;
    private ComboBox productList;
    private TextField productNameField;
    private NumberField productCostField, productProfitField, productPriceField;
    private Button addBtn, updateBtn, deleteBtn, clearBtn;

    public ProductView() {
        productList = new ComboBox("Product List");
        productNameField = new TextField("Product Name");
        productCostField = new NumberField("Product Cost");
        productProfitField = new NumberField("Product Profit");
        productPriceField = new NumberField("Product Price");
        addBtn = new Button();
        updateBtn = new Button();
        deleteBtn = new Button();
        clearBtn = new Button();
        horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(addBtn, updateBtn, deleteBtn, clearBtn);
        add(productList, productNameField, productCostField, productProfitField, productPriceField, horizontalLayout);
    }
}
