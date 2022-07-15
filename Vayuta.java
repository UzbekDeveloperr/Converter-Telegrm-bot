package org.example;

public class Vayuta {
    private long id;
    private String Code;
    private String Ccy;
    private String CcyNm_RU;
    private String CcyNm_UZ;
    private String CCcyNm_UZCode;
    private String CoCcyNm_ENde;
    private String Nominal;
    private String Rate;
    private String Diff;
    private String Date;

    public Vayuta() {
    }

    public Vayuta(long id, String code, String ccy, String ccyNm_RU, String ccyNm_UZ, String CCcyNm_UZCode, String coCcyNm_ENde, String nominal, String rate, String diff, String date) {
        this.id = id;
        Code = code;
        Ccy = ccy;
        CcyNm_RU = ccyNm_RU;
        CcyNm_UZ = ccyNm_UZ;
        this.CCcyNm_UZCode = CCcyNm_UZCode;
        CoCcyNm_ENde = coCcyNm_ENde;
        Nominal = nominal;
        Rate = rate;
        Diff = diff;
        Date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getCcyNm_RU() {
        return CcyNm_RU;
    }

    public void setCcyNm_RU(String ccyNm_RU) {
        CcyNm_RU = ccyNm_RU;
    }

    public String getCcyNm_UZ() {
        return CcyNm_UZ;
    }

    public void setCcyNm_UZ(String ccyNm_UZ) {
        CcyNm_UZ = ccyNm_UZ;
    }

    public String getCCcyNm_UZCode() {
        return CCcyNm_UZCode;
    }

    public void setCCcyNm_UZCode(String CCcyNm_UZCode) {
        this.CCcyNm_UZCode = CCcyNm_UZCode;
    }

    public String getCoCcyNm_ENde() {
        return CoCcyNm_ENde;
    }

    public void setCoCcyNm_ENde(String coCcyNm_ENde) {
        CoCcyNm_ENde = coCcyNm_ENde;
    }

    public String getNominal() {
        return Nominal;
    }

    public void setNominal(String nominal) {
        Nominal = nominal;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getDiff() {
        return Diff;
    }

    public void setDiff(String diff) {
        Diff = diff;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return
                " id=" + id +
                "\n Code=" + Code +
                "\n Ccy=" + Ccy +
                "\n CcyNm_RU=" + CcyNm_RU +
                "\n CcyNm_UZ=" + CcyNm_UZ +
                "\n CCcyNm_UZCode=" + CCcyNm_UZCode +
                "\n CoCcyNm_ENde=" + CoCcyNm_ENde +
                "\n Nominal=" + Nominal +
                "\n Rate=" + Rate +
                "\n Diff=" + Diff +
                "\n Date=" + Date;
    }
}
