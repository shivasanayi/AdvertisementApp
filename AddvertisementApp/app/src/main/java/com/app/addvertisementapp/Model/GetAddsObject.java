package com.app.addvertisementapp.Model;

public class GetAddsObject {



    private String Id;
    private String UserId;
    private String AddText;
    private String City;

    private String CellPhoneNumber;

    public GetAddsObject(String Id, String UserId, String Text, String City, String CellPhoneNumber) {
        this.Id=Id;
        this.UserId=UserId;
      this.AddText=Text;

        this.City=City;
        this.setCellPhoneNumber(CellPhoneNumber);
    }

    public GetAddsObject() {
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }



    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }



    public String getCellPhoneNumber() {
        return CellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        CellPhoneNumber = cellPhoneNumber;
    }

    public String getAddText() {
        return AddText;
    }

    public void setAddText(String addText) {
        AddText = addText;
    }
}
