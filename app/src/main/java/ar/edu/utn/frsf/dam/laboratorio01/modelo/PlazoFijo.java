package ar.edu.utn.frsf.dam.laboratorio01.modelo;


import android.util.Log;

import java.util.Arrays;

public class PlazoFijo {
    private Integer dias;
    private int monto;
    private Boolean avisarVencimiento;
    private Boolean renovarAutomaticamente;
    private Moneda moneda;
    private String[] tasas;

    @Override
    public String toString() {
        return "PlazoFijo{" +
                "dias=" + dias +
                ", monto=" + monto +
                ", avisarVencimiento=" + avisarVencimiento +
                ", renovarAutomaticamente=" + renovarAutomaticamente +
                ", moneda=" + moneda +
                ", tasas=" + Arrays.toString(tasas) +
                ", cliente=" + cliente +
                '}';
    }

    private Cliente cliente;

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Boolean getAvisarVencimiento() {
        return avisarVencimiento;
    }

    public void setAvisarVencimiento(Boolean avisarVencimiento) {
        this.avisarVencimiento = avisarVencimiento;
    }

    public Boolean getRenovarAutomaticamente() {
        return renovarAutomaticamente;
    }

    public void setRenovarAutomaticamente(Boolean renovarAutomaticamente) {
        this.renovarAutomaticamente = renovarAutomaticamente;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public String[] getTasas() {
        return tasas;
    }

    public void setTasas(String[] tasas) {
        this.tasas = tasas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PlazoFijo(String[] tasas){
        Log.d("APP_01", tasas+"");
        Log.d("APP_01", Arrays.toString(tasas));
        this.tasas = tasas;
        this.monto = 0;
        this.dias = 0;
        this.avisarVencimiento=false;
        this.renovarAutomaticamente=false;
        this.moneda = Moneda.PESO;
    }

    public Double calcularTaza(int dias, int monto) {
        if(dias < 30 && monto <= 5000)
            return Double.valueOf(tasas[0]);
        else if(dias >= 30 && monto <= 5000)
            return Double.valueOf(tasas[1]);
        else if(dias < 30 && monto >= 5000 && monto <= 99999)
            return Double.valueOf(tasas[2]);
        else if(dias >= 30 && monto >= 5000 && monto <= 99999)
            return Double.valueOf(tasas[3]);
        else if(dias < 30 && monto > 99999)
            return Double.valueOf(tasas[4]);
        else
            return Double.valueOf(tasas[5]);
    }

    public Double intereses(int dias, int monto){
        Double interes = calcularTaza(dias,monto);
        Double valor = Math.pow((1+(interes/100)),(dias/360));
        return 23.45;
    }}
