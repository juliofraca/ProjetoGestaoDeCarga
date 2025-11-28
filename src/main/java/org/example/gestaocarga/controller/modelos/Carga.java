package org.example.gestaocarga.controller.modelos;

public class Carga {

    private int id;
    private String origem;
    private String destino;
    private String dataEntrega;
    private String status;
    private int gestorId;

    public Carga(String origem, String destino, String dataEntrega, int gestorId) {
        this.origem = origem;
        this.destino = destino;
        this.dataEntrega = dataEntrega;
        this.status = "pendente";
        this.gestorId = gestorId;
    }

    public Carga(int id, String origem, String destino, String dataEntrega, String status, int gestorId) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.gestorId = gestorId;
    }

    public int getId() { return id; }
    public String getOrigem() { return origem; }
    public String getDestino() { return destino; }
    public String getDataEntrega() { return dataEntrega; }
    public String getStatus() { return status; }
    public int getGestorId() { return gestorId; }

    public void setId(int id) { this.id = id; }
    public void setOrigem(String origem) { this.origem = origem; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setDataEntrega(String dataEntrega) { this.dataEntrega = dataEntrega; }
    public void setStatus(String status) { this.status = status; }
    public void setGestorId(int gestorId) { this.gestorId = gestorId; }
}