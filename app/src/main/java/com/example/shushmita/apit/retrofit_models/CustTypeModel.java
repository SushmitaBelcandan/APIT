package com.example.shushmita.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustTypeModel {

    @SerializedName("status")
    public String status;

    @SerializedName("result")
    public String result;

    @SerializedName("response")
    public List<TypeListDatum> response = null;

    public class TypeListDatum {

        @SerializedName("customer_type_id")
        public int customer_type_id;

        @SerializedName("customertype")
        public String customertype;

        public int getCustomer_type_id() {
            return customer_type_id;
        }

        public void setCustomer_type_id(int customer_type_id) {
            this.customer_type_id = customer_type_id;
        }

        public String getCustomertype() {
            return customertype;
        }

        public void setCustomertype(String customertype) {
            this.customertype = customertype;
        }
    }
}
