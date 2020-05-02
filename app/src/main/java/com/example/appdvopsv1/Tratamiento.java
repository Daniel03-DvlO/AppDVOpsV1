package com.example.appdvopsv1;

public class Tratamiento {
    int id;
    String nombreCorto, fechaInicio, horaInicio, nombreMedicamento;
    int dias, vecesDia, cantidadActual;


    public Tratamiento(){ }

    public Tratamiento(int id, String nombreCorto, String fechaInicio, String horaInicio, int dias, String nombreMedicamento,int vecesDia, int cantidadActual) {
        this.id = id;
        this.nombreCorto = nombreCorto;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.dias = dias;
        this.nombreMedicamento = nombreMedicamento;
        this.vecesDia = vecesDia;
        this.cantidadActual = cantidadActual;
    }

    public int getId() {
        return id;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public int getDias(){
        return dias;
    }

    public String getNombreMedicamento() { return nombreMedicamento; }

    public int getVecesDias(){
        return vecesDia;
    }

    public int getCantidadActual() { return cantidadActual; }
}
