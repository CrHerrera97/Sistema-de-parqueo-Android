package com.example.cristianherrera.parkingsystem;

public class Campos {

    private int placa;
    private String vehiculo;
    private String horaEntrada;
    private String horaSalida;

    public Campos(int placa, String vehiculo, String horaEntrada, String horaSalida) {
        this.placa = placa;
        this.vehiculo = vehiculo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public Campos(){

    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }
}
